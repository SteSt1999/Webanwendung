package Logik.Sessionsteuerung;

import Datenbank.DBUser;
import Logik.Verwaltung.Kunde;

public class SessionKunde extends Session {
    private Kunde kunde;

    public SessionKunde(String kundenID, String passwort, Zugangsweg zugangsweg) {
        if (!DBUser.checkPasswortKunde(kundenID, passwort)) {
            throw new IllegalArgumentException();
        }
        setUser(kundenID);
        setZugangsweg(zugangsweg);
    }

    public Kunde getUser() {
        return kunde;
    }

    public void setUser(String kundenID) {
        this.kunde = new Kunde(kundenID);
    }

    public long getKontostand() {
        return kunde.getKontostand();
    }

    public String getKontoLog() {
        return kunde.getKontoLog();
    }
}