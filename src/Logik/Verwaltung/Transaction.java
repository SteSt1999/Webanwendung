package Logik.Verwaltung;

import Logik.Sessionsteuerung.Zugangsweg;

import java.util.Random;

public class Transaction {

    private Zugangsweg zugangsweg;
    private User sender;
    private User empfänger;

    // 1 = überweisung,2 = überweisung erhalten, 3 = abheben, 4 = einzahlen
    private int transaktionsID;


    private long betrag;


    public Transaction(User sender, User empfänger, long betrag, Zugangsweg zugangsweg, int überweisungsid)  {
        this.sender = sender;
        this.empfänger = empfänger;
        this.betrag = betrag;
        this.zugangsweg = zugangsweg;
        this.transaktionsID = überweisungsid;



    }


}
