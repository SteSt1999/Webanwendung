package Logik.Verwaltung;

import Datenbank.DBATM;

public class ATM {
    private String atmID;

    public ATM(String atmID) {
        setId(atmID);
    }

    public String getId() {
        return atmID;
    }

    public void setId(String atmID) {
        if (!DBATM.existiertATM(atmID)) {
            throw new IllegalArgumentException("Der Automat existiert nicht!");
        }
        this.atmID = atmID;
    }
}