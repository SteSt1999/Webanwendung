package Logik.Verwaltung;

public abstract class User {
    String benutzername;
    final Bank bank;

    public User(String benutzername, Bank bank) {
        this.bank = bank;
        setBenutzername(benutzername);
    }

    public User(String benutzername, String passwort, Bank bank) {
        this.bank = bank;
        this.benutzername = benutzername;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public abstract void setBenutzername(String benutzername);

    public Bank getBank() {
        return bank;
    }
}