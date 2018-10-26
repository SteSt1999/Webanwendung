package Logik.Sessionsteuerung;

import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.User;

public class Session {
    private Zugangsweg zugangsweg;
    //aktuell eingeloggter User
    private User user;


    public Session(User user, Zugangsweg zugangsweg) {
        setUser(user);
        setZugangsweg(zugangsweg);
    }

    public Zugangsweg getZugangsweg() {
        return zugangsweg;
    }

    public void setZugangsweg(Zugangsweg zugangsweg) {
        this.zugangsweg = zugangsweg;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }

    public long getKontostand() {
        if (!(user instanceof Kunde)) {
            throw new IllegalArgumentException("Mitarbeiter besitzen kein Konto!");
        }
        return ((Kunde) user).getKontostand();
    }

    public String getKontoLog() {
        if (!(user instanceof Kunde)) {
            throw new IllegalArgumentException("Mitarbeiter besitzen kein Konto!");
        }
        return ((Kunde) user).getKontoLog();
    }
}