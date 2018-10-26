package Logik.Verwaltung;

import Datenbank.DBATM;

public class ATM {
    private int id;

    public ATM(String id) {
        setId(Integer.parseInt(id));
    }

    public ATM(int id) {
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (!DBATM.existiertATM(id + "")) {
            throw new IllegalArgumentException("Der Automat existiert nicht!");
        }
        this.id = id;
    }
}