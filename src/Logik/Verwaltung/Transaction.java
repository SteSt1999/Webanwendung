package Logik.Verwaltung;

import Logik.Sessionsteuerung.Zugangsweg;

public class Transaction {
    private final Zugangsweg zugangsweg;
    private final User sender;
    private final User empfaenger;

    // 1 = überweisung,2 = überweisung erhalten, 3 = abheben, 4 = einzahlen
    private final int transaktionsID;
    private final long betrag;

    public Transaction(final User sender, final User empfaenger, final long betrag, final Zugangsweg zugangsweg, final int transaktionsID) {
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