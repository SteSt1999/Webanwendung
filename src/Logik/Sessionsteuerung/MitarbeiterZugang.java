package Logik.Sessionsteuerung;

import Logik.Umwandlung;
import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;

import static Datenbank.DBLog.*;

public class MitarbeiterZugang implements Zugangsweg {
    final private String dbBezeichnung = "1";

    public static void doMitarbeiterEinzahlen(Session session, Kunde kunde, String eingabeBetrag) {
        long betrag =  Umwandlung.stringToLong(eingabeBetrag);
        kunde.getKonto().einzahlen(kunde, betrag);

        Transaction transaction = new Transaction(kunde, null, betrag, session.getZugangsweg(), 4);
        logHinzufuegen(transaction);
    }

    public static void doMitarbeiterAbheben(Session session, Kunde kunde, String eingabeBetrag) {
        long betrag = Umwandlung.stringToLong(eingabeBetrag);
        kunde.getKonto().abheben(kunde, betrag);

        Transaction transaction = new Transaction(kunde, null, betrag, session.getZugangsweg(), 3);
        logHinzufuegen(transaction);
    }

    public static String doAusgabeATMLog(String atmID) {
        return getZugangswegLog(atmID);
    }

    public static String doAusgabeUserLog(Kunde user) {
        return getUserLog(user.getBenutzername());
    }

    public static String doAusgabeBankLog() {
        return getBankLog();
    }

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }
}
