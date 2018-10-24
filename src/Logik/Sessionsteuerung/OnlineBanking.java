package Logik.Sessionsteuerung;

import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;
import Logik.Verwaltung.User;

import static Datenbank.DBLog.logHinzufuegen;

public class OnlineBanking implements Zugangsweg {
    int dbBezeichnung = 2;


    public void doOnlineBankingÜberweisung(Session session, User kunde, long betrag){
        //überweisen
        ((Kunde) session.getUser()).getKonto().überweisen(session.getUser(), kunde, betrag);

        Transaction transactionUeberweisung = new Transaction(session.getUser(),kunde, betrag, session.getZugangsweg(), 1);
        Transaction transactionUeberweisungErhalten = new Transaction(kunde,session.getUser(), betrag, session.getZugangsweg(), 2);

        logHinzufuegen(transactionUeberweisung);
        logHinzufuegen(transactionUeberweisungErhalten);

    }


}
