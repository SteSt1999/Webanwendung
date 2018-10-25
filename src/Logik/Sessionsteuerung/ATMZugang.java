package Logik.Sessionsteuerung;

import Datenbank.DBKontostand;
import Datenbank.DBUser;
import Logik.Umwandlung;
import Logik.Verwaltung.*;
import Servlet.MainServlet;

import static Datenbank.DBLog.logHinzufuegen;

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
