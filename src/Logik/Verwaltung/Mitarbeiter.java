package Logik.Verwaltung;

import Datenbank.DBUser;
import Servlet.MainServlet;

public class Mitarbeiter extends User {
    public Mitarbeiter(String benutzername) {
        super(benutzername);
    }

    public void setBenutzername(String benutzername) {
        if (!DBUser.existiertMitarbeiter(benutzername, MainServlet.getBank().getBankID())) {
            throw new IllegalArgumentException();
        }
        this.benutzername = benutzername;
    }
}