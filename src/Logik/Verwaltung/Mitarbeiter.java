package Logik.Verwaltung;

import Datenbank.DBUser;

public class Mitarbeiter extends User {
    public Mitarbeiter(String benutzername) {
        super(benutzername);
    }

    public void setBenutzername(String benutzername) {
        if(!DBUser.existiertMitarbeiter(benutzername)) {
            throw new IllegalArgumentException();
        }
        this.benutzername = benutzername;
    }
}