package Logik.Sessionsteuerung;

import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;
import java.sql.ResultSet;
import static Datenbank.DBLog.*;

public class Mitarbeiter_Zugang implements Zugangsweg {


    public void doMitarbeiterEinzahlen(Session session, Kunde kunde, long betrag) {
        //einzahlen
        kunde.getKonto().einzahlen(kunde, betrag);

        Transaction transaction = new Transaction(kunde, null, betrag, session.getZugangsweg(), 4);

        logHinzufügen(transaction);

    }


    public void doMitarbeiterAbheben(Session session, Kunde kunde, long betrag) {
        //abheben
        kunde.getKonto().abheben(kunde, betrag);

        Transaction transaction = new Transaction(kunde, null, betrag, session.getZugangsweg(), 3);

        logHinzufügen(transaction);
    }


    public void doAusgabeATMLog(int id) {
        ResultSet resultSet = getATMLog(id);
    }

    public void doAusgabeUserLog(Kunde user) {
        ResultSet resultSet = getUserLog(user.getBenutzername());
    }
    public void doAusgabeBankLog(){
        ResultSet resultSet = getBankLog();
    }
}
