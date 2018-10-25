package Logik.Verwaltung;

import Datenbank.DBBank;

public class Bank {
    private String bankID;

    public Bank(String bankID) {
        setBankID(bankID);
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        if(!DBBank.existiertBank(bankID)) {
            throw new IllegalArgumentException();
        }
        this.bankID = bankID;
    }
}
