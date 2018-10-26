package Logik.Sessionsteuerung;

import Logik.Verwaltung.*;

public class ZugangATM implements Zugangsweg {
    private ATM atm;

    public ZugangATM(String atmID) {
        atm = new ATM(atmID);
    }

    @Override
    public String getdbBezeichnung() {
        return String.valueOf(atm.getId());
    }
}