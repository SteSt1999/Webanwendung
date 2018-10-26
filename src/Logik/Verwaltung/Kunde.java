package Logik.Verwaltung;

import Datenbank.DBLog;
import Datenbank.DBUser;

public class Kunde extends User {
    //Konto und Kunde bei welcher Bank
    private Konto konto;

    public Kunde(Konto konto, String benutzername) {
        super(benutzername);
        setKonto(konto);
    }

    public Kunde(Konto konto, String benutzername, String bankID) {
        super(benutzername, bankID);
        setKonto(konto);
    }

    public void setBenutzername(String benutzername) {
        if(!DBUser.existiertKunde(benutzername, bank.getBankID())) {
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