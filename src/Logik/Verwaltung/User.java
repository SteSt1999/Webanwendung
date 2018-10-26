package Logik.Verwaltung;

import Servlet.MainServlet;

public abstract class User {
    //Login-Daten
    String benutzername;
    Bank bank;

    public User(String benutzername) {
        bank = new Bank(MainServlet.getBank().getBankID());
        setBenutzername(benutzername);
    }

    public User(String benutzername, String bankID) {
        bank = new Bank(bankID);
        setBenutzername(benutzername);
    }

    public String getBenutzername() {
        return benutzername;
    }

    public abstract void setBenutzername(String benutzername);

    public Bank getBank() {
        return bank;
    }
}