package Logik.Sessionsteuerung;

import Logik.Verwaltung.*;

import static Datenbank.DBLog.logHinzufügen;

public class ATM_Zugang implements Zugangsweg {
    private ATM atm;


    // bekommt beim Login eine id nummer mit Übergeben
    public ATM_Zugang(int id) {
       this.atm = new ATM(id);
    }



    public void doATMabheben(Session session, long betrag) {
        //abheben
         ((Kunde) session.getUser()).getKonto().abheben(session.getUser(), betrag);

        Transaction transaction = new Transaction(session.getUser(),  (session.getUser()), betrag, session.getZugangsweg(), 3);

        logHinzufügen(transaction);

    }

    public void doATMüberweisen(Session session, User kunde, long betrag) {
        //überweisen
        ((Kunde) session.getUser()).getKonto().überweisen(session.getUser(), kunde, betrag);

        Transaction transactionUeberweisung = new Transaction(session.getUser(),kunde, betrag, session.getZugangsweg(), 1);
        Transaction transactionUeberweisungErhalten = new Transaction(kunde,session.getUser(), betrag, session.getZugangsweg(), 2);

        logHinzufügen(transactionUeberweisung);
        logHinzufügen(transactionUeberweisungErhalten);




    }
    public void doATMeinzahlen(Session session, long betrag){
        //einzahlen
        ((Kunde) session.getUser()).getKonto().einzahlen(session.getUser(),betrag);

        Transaction transaction = new Transaction(session.getUser(),  (session.getUser()), betrag, session.getZugangsweg(), 4);

        logHinzufügen(transaction);
    }
}
