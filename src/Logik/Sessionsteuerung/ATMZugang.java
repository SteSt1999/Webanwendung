package Logik.Sessionsteuerung;

import Logik.Verwaltung.*;

public class ATMZugang implements Zugangsweg {
    private ATM atm;

    public ATMZugang(int id) {
        atm = new ATM(id);
    }

    @Override
    public String getdbBezeichnung() {
        return String.valueOf(atm.getId());
    }
}