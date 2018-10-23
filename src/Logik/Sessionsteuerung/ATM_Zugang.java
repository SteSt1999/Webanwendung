package Logik.Sessionsteuerung;

import Logik.Verwaltung.ATM;
import Logik.Verwaltung.Konto;
import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;

import static Datenbank.Datenbank.logHinzufügen;

public class ATM_Zugang implements Zugangsweg {
    private ATM atm;


    // bekommt beim Login eine id nummer mit Übergeben
    public ATM_Zugang(int id) {
       this.atm = new ATM(id);
    }



    public void doATMabheben(Session session, long betrag) {
        //abheben
         ((Kunde) session.getUser()).getKonto().abheben(betrag);

        Transaction transaction = new Transaction(session.getUser(), (Kunde) (session.getUser()), betrag, session.getZugangsweg(), 3);

        logHinzufügen(transaction);

    }

    public void doATMüberweisen(Session session, Kunde kunde, long betrag) {
        //überweisen
        ((Kunde) session.getUser()).getKonto().überweisen(betrag);

        Transaction transactionUeberweisung = new Transaction(session.getUser(),kunde, betrag, session.getZugangsweg(), 1);
        Transaction transactionUeberweisungErhalten = new Transaction(kunde,session.getUser(), betrag, session.getZugangsweg(), 2);

        logHinzufügen(transactionUeberweisung);
        logHinzufügen(transactionUeberweisungErhalten);




    }
    public void doATMeinzahlen(Session session, long betrag){
        //einzahlen
        ((Kunde) session.getUser()).getKonto().einzahlen(betrag);

        Transaction transaction = new Transaction(session.getUser(), (Kunde) (session.getUser()), betrag, session.getZugangsweg(), 4);

        logHinzufügen(transaction);
    }
}
