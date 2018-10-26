package Logik.Sessionsteuerung;

import Datenbank.DBKontostand;
import Datenbank.DBUser;
import Logik.Umwandlung;
import Logik.Verwaltung.Bank;
import Logik.Verwaltung.Konto;
import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;
import Servlet.MainServlet;

import static Datenbank.DBLog.logHinzufuegen;

public class GeldBewegung {
    public static void ueberweisen(Session session, String empfaenger, String empfaengerBankID, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);
        empfaengerBankID = empfaengerBankID.trim().length() == 0 ? MainServlet.getBankID() : empfaengerBankID;
        Bank empfaengerBank = new Bank(empfaengerBankID);

        if (!DBUser.existiertKunde(empfaenger, empfaengerBank.getBankID())) {
            throw new IllegalArgumentException();
        }

        Kunde empfaengerUser = new Kunde(new Konto(DBKontostand.kontostandLesen(empfaenger, empfaengerBankID)), empfaenger, empfaengerBankID);
        Kunde senderUser = (Kunde) session.getUser();

        Konto.ueberweisen(senderUser, empfaengerUser, betrag);

        Transaction transactionUeberweisung = new Transaction(senderUser, empfaengerUser, -betrag, session.getZugangsweg(), 1);
        Transaction transactionUeberweisungErhalten = new Transaction(empfaengerUser, senderUser, betrag, new UeberweisungErhalten(), 2);
        logHinzufuegen(transactionUeberweisung);
        logHinzufuegen(transactionUeberweisungErhalten);
    }

    public static void abheben(Session session, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);

        Kunde kunde = (Kunde) session.getUser();
        kunde.getKonto().abheben(kunde, betrag);

        Transaction transaction = new Transaction(session.getUser(), null, -betrag, session.getZugangsweg(), 3);
        logHinzufuegen(transaction);
    }

    public static void einzahlen(Session session, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);

        Kunde kunde = (Kunde) session.getUser();
        kunde.getKonto().einzahlen(kunde, betrag);

        Transaction transaction = new Transaction(session.getUser(), null, betrag, session.getZugangsweg(), 4);
        logHinzufuegen(transaction);
    }
}