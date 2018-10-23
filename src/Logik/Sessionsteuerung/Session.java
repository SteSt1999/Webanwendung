package Logik.Sessionsteuerung;

import Logik.Verwaltung.Kunde;
import Logik.Verwaltung.Transaction;
import Logik.Verwaltung.User;

import java.util.Random;


public class Session {
    private int id;
    private Zugangsweg zugangsweg;
    //aktuell eingeloggter User
    private User user;


    public Session(User user, Zugangsweg zugangsweg) {
        setId(new Random().nextInt());
        setUser(user);
        setZugangsweg(zugangsweg);

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setUser(User user) {
        this.user = user;
    }
}
