package Logik.Verwaltung;

import Servlet.MainServlet;

public abstract class User {
    //Login-Daten
    String benutzername;
    Bank bank;

    public User(String benutzername) {
        setBenutzername(benutzername);
        bank = new Bank(MainServlet.getBankID());
    }

    public User(String benutzername, String bankID) {
        setBenutzername(benutzername);
        bank = new Bank(bankID);
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public Bank getBank() {
        return bank;
    }
}