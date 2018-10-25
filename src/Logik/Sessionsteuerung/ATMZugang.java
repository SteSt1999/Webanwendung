package Logik.Sessionsteuerung;

import Datenbank.DBKontostand;
import Datenbank.DBUser;
import Logik.Umwandlung;
import Logik.Verwaltung.*;
import Servlet.MainServlet;

import static Datenbank.DBLog.logHinzufuegen;

public class ATMZugang implements Zugangsweg {
    private ATM atm;

    // bekommt beim Login eine id nummer mit Übergeben
    public ATMZugang(int id) {
        this.atm = new ATM(id);
    }

    @Override
    public String getdbBezeichnung() {
        return String.valueOf(atm.getId());
    }

    public static void doATMUeberweisen(Session session, String empfaenger, String empfaengerBankID, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);
        empfaengerBankID = empfaengerBankID.trim().length() == 0 ? MainServlet.getBankID() : empfaengerBankID;
        Bank empfaengerBank = new Bank(empfaengerBankID);

        if (!DBUser.existiertKunde(empfaenger, empfaengerBank.getBankID())) {
            throw new IllegalArgumentException();
        }

        Kunde empfaengerUser = new Kunde(new Konto(DBKontostand.kontostandLesen(empfaenger, empfaengerBankID)), empfaenger, empfaengerBankID);
        Kunde senderUser = (Kunde) session.getUser();

        empfaengerUser.getKonto().abhebenNeu(empfaengerUser, betrag);
        senderUser.getKonto().einzahlenNeu(senderUser, -betrag);

        Transaction transactionUeberweisung = new Transaction(senderUser, empfaengerUser, -betrag, session.getZugangsweg(), 1);
        Transaction transactionUeberweisungErhalten = new Transaction(empfaengerUser, senderUser, betrag, session.getZugangsweg(), 2);

        logHinzufuegen(transactionUeberweisung);
        logHinzufuegen(transactionUeberweisungErhalten);
    }

    public static void doATMabheben(Session session, String eingabeBetrag) {
        long betrag = -1 * Umwandlung.stringToLong(eingabeBetrag);

        ((Kunde) session.getUser()).getKonto().abheben(session.getUser(), betrag);
        Transaction transaction = new Transaction(session.getUser(), null, betrag, session.getZugangsweg(), 3);

        logHinzufuegen(transaction);
    }

    public static void doATMEinzahlen(Session session, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);

        ((Kunde) session.getUser()).getKonto().einzahlen(session.getUser(), betrag);
        Transaction transaction = new Transaction(session.getUser(), null, betrag, session.getZugangsweg(), 4);

        logHinzufuegen(transaction);
    }
}
