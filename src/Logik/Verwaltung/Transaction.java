package Logik.Verwaltung;

import java.util.Random;

public class Transaction {

    private int tan;
    private User sender;
    private Kunde empfänger;

    private String betrag;


    public Transaction(User sender, Kunde empfänger, String Betrag) {
        this.sender = sender;
        this.empfänger = empfänger;
        this.betrag = betrag;

        this.tan = new Random().nextInt();

    }


}
