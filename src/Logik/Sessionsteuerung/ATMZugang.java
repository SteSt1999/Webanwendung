package Logik.Sessionsteuerung;

import Logik.Verwaltung.*;

public class ATMZugang implements Zugangsweg {
    private ATM atm;

    // bekommt beim Login eine id nummer mit Übergeben
    public ATMZugang(int id) {
        this.atm = new ATM(id);
    }

    @Override
    public String getdbBezeichnung() {
        return String.valueOf(atm.getId());
    }
}