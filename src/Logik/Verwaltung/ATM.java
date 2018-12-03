package Logik.Verwaltung;

import Datenbank.DBATM;

public class ATM {
    private String atmID;
    private final Bank bank;

    public ATM(String atmID, Bank bank) {
        this.bank = bank;
        setId(atmID);
    }

    public String getId() {
        return atmID;
    }

    public void setId(String atmID) {
        if (!DBATM.existiertATM(atmID, bank)) {
            throw new IllegalArgumentException("Der Automat existiert nicht!");
        }
        this.atmID = atmID;
    }

    public Bank getBank() {
        return bank;
    }
}