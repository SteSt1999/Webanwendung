package Logik.Verwaltung;

public abstract class User {
    //Login-Daten
    String benutzername;

    // private int bankid;

    public User(String benutzername) {
        setBenutzername(benutzername);
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;

    }
}