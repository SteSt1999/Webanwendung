package Logik.Sessionsteuerung;

import Logik.Verwaltung.*;

import static Datenbank.DBLog.logHinzufuegen;

public class ATM_Zugang implements Zugangsweg {
    private ATM atm;


    // bekommt beim Login eine id nummer mit Übergeben
    public ATM_Zugang(int id) {
       this.atm = new ATM(id);
    }

    @Override
    public String getdbBezeichnung() {
        return String.valueOf(atm.getId());
    }

    public static void doATMabheben(Session session, long betrag) {
        //abheben
         ((Kunde) session.getUser()).getKonto().abheben(session.getUser(), betrag);

        Transaction transaction = new Transaction(session.getUser(),  (session.getUser()), betrag, session.getZugangsweg(), 3);

        logHinzufuegen(transaction);

    }

    public static void doATMüberweisen(Session session, User kunde, long betrag) {
        //überweisen
        ((Kunde) session.getUser()).getKonto().überweisen(session.getUser(), kunde, betrag);
        System.out.println();

        Transaction transactionUeberweisung = new Transaction(session.getUser(),kunde, betrag, session.getZugangsweg(), 1);
        Transaction transactionUeberweisungErhalten = new Transaction(kunde,session.getUser(), betrag, session.getZugangsweg(), 2);

        logHinzufuegen(transactionUeberweisung);
        logHinzufuegen(transactionUeberweisungErhalten);




    }
    public static void doATMeinzahlen(Session session, long betrag){
        //einzahlen
        ((Kunde) session.getUser()).getKonto().einzahlen(session.getUser(),betrag);

        Transaction transaction = new Transaction(session.getUser(),  (session.getUser()), betrag, session.getZugangsweg(), 4);

        logHinzufuegen(transaction);
    }
}
