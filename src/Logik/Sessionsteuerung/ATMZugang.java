package Logik.Sessionsteuerung;

import Datenbank.DBUser;
import Logik.Umwandlung;
import Logik.Verwaltung.*;

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

    public static void doATMUeberweisen(Session session, User kunde, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);
        if (!DBUser.existiertKunde(kunde.getBenutzername())) {
            throw new IllegalArgumentException();
        }

        ((Kunde) session.getUser()).getKonto().ueberweisen(session.getUser(), kunde, betrag);

        Transaction transactionUeberweisung = new Transaction(session.getUser(), kunde, -betrag, session.getZugangsweg(), 1);
        Transaction transactionUeberweisungErhalten = new Transaction(kunde, session.getUser(), betrag, session.getZugangsweg(), 2);

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
