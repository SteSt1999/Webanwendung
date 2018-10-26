package Logik.Sessionsteuerung;

import Logik.Verwaltung.*;

public class ZugangATM implements Zugangsweg {
    private ATM atm;

    public ZugangATM(int id) {
        atm = new ATM(id);
    }

    @Override
    public String getdbBezeichnung() {
        return String.valueOf(atm.getId());
    }
}