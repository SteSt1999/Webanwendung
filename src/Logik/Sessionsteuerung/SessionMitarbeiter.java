package Logik.Sessionsteuerung;

import Datenbank.DBUser;
import Logik.Verwaltung.Mitarbeiter;

public class SessionMitarbeiter extends Session {
    private Mitarbeiter mitarbeiter;

    public SessionMitarbeiter(String mitarbeiterID, String passwort, Zugangsweg zugangsweg) {
        if (!DBUser.checkPasswortMitarbeiter(mitarbeiterID, passwort)) {
            throw new IllegalArgumentException();
        }
        setUser(mitarbeiterID);
        setZugangsweg(zugangsweg);
    }

    public Mitarbeiter getUser() {
        return mitarbeiter;
    }

    public void setUser(String mitarbeiterID) {
        this.mitarbeiter = new Mitarbeiter(mitarbeiterID);
    }
}