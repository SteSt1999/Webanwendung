package Logik.Sessionsteuerung;

import Logik.Verwaltung.User;

abstract class Session {
    private Zugangsweg zugangsweg;

    public Zugangsweg getZugangsweg() {
        return zugangsweg;
    }

    public void setZugangsweg(Zugangsweg zugangsweg) {
        this.zugangsweg = zugangsweg;
    }

    abstract User getUser();

    abstract void setUser(String id);
}