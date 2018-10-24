package Logik.Sessionsteuerung;

import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;

import static Datenbank.DBLog.*;

public class Mitarbeiter_Zugang implements Zugangsweg {


    String dbBezeichnung = "1";

    public void doMitarbeiterEinzahlen(Session session, Kunde kunde, long betrag) {
        //einzahlen
        kunde.getKonto().einzahlen(kunde, betrag);

        Transaction transaction = new Transaction(kunde, null, betrag, session.getZugangsweg(), 4);

        logHinzufuegen(transaction);

    }


    public void doMitarbeiterAbheben(Session session, Kunde kunde, long betrag) {
        //abheben
        kunde.getKonto().abheben(kunde, betrag);

        Transaction transaction = new Transaction(kunde, null, betrag, session.getZugangsweg(), 3);

        logHinzufuegen(transaction);
    }

   //@TODO Mitarbeiter Log Ausgaben
    public void doAusgabeATMLog(String atmID) {
       String log = getZugangswegLog(atmID);
    }

    public void doAusgabeUserLog(Kunde user) {
        String log = getUserLog(user.getBenutzername());
    }

    public void doAusgabeBankLog() {
        String log = getBankLog();
    }

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }
}
