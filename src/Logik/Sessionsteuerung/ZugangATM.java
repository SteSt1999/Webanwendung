package Logik.Sessionsteuerung;

import Logik.Verwaltung.*;

public class ZugangATM implements Zugangsweg {
    private final ATM atm;

    public ZugangATM(String atmID, Bank bank) {
        atm = new ATM(atmID, bank);
    }

    @Override
    public String getdbBezeichnung() {
        return String.valueOf(atm.getId());
    }
}