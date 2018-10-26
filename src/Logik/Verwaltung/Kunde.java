package Logik.Verwaltung;

import Datenbank.DBKontostand;
import Datenbank.DBLog;
import Datenbank.DBUser;

public class Kunde extends User {
    private Konto konto;

    public Kunde(String benutzername) {
        super(benutzername);
        setKonto(new Konto(DBKontostand.kontostandLesen(benutzername, bank.getBankID())));
    }

    public Kunde(String benutzername, String bankID) {
        super(benutzername, bankID);
        setKonto(new Konto(DBKontostand.kontostandLesen(benutzername, bank.getBankID())));
    }

    public void setBenutzername(String benutzername) {
        if (!DBUser.existiertKunde(benutzername, bank.getBankID())) {
            throw new IllegalArgumentException();
        }
        this.benutzername = benutzername;
    }

    public Konto getKonto() {
        return konto;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public long getKontostand() {
        return konto.getKontostand();
    }

    public String getKontoLog() {
        return DBLog.getUserLog(benutzername);
    }
}