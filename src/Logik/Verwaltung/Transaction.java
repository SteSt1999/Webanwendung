package Logik.Verwaltung;

import Logik.Sessionsteuerung.Zugangsweg;

public class Transaction {

    private Zugangsweg zugangsweg;
    private User sender;
    private User empfaenger;

    // 1 = überweisung,2 = überweisung erhalten, 3 = abheben, 4 = einzahlen
    private int transaktionsID;
    private long betrag;

    public Transaction(User sender, User empfaenger, long betrag, Zugangsweg zugangsweg, int transaktionsID) {
        this.sender = sender;
        this.empfaenger = empfaenger;
        this.betrag = betrag;
        this.zugangsweg = zugangsweg;
        this.transaktionsID = transaktionsID;
    }

    public Zugangsweg getZugangsweg() {
        return zugangsweg;
    }

    public User getSender() {
        return sender;
    }

    public User getEmpfaenger() {
        return empfaenger;
    }

    public int getTransaktionsID() {
        return transaktionsID;
    }

    public long getBetrag() {
        return betrag;
    }
}
