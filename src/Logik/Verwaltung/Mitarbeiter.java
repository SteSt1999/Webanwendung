package Logik.Verwaltung;

import Datenbank.DBUser;

public class Mitarbeiter extends User {
    public Mitarbeiter(String benutzername, Bank bank) {
        super(benutzername, bank);
    }

    public Mitarbeiter(String benutzername, String passwort, Bank bank) {
        super(benutzername, passwort, bank);
        if(!DBUser.checkPasswortMitarbeiter(benutzername, passwort, bank)) {
            throw new IllegalArgumentException("Kun");
        }
    }

    public void setBenutzername(String benutzername) {
        if (!DBUser.existiertMitarbeiter(benutzername, bank)) {
            throw new IllegalArgumentException();
        }
        this.benutzername = benutzername;
    }
}