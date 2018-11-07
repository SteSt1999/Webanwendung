package Logik.Verwaltung;

import Datenbank.DBKonto;
import Datenbank.DBUser;
import Logik.Umwandlung;

public class Kunde extends User {
    private Konto konto;

    public Kunde(String benutzername) {
        super(benutzername);
        setKonto(new Konto(DBKonto.kontostandLesen(benutzername, bank.getBankID())));
    }

    public Kunde(String benutzername, String bankID) {
        super(benutzername, bankID);
        setKonto(new Konto(DBKonto.kontostandLesen(benutzername, bank.getBankID())));
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

    public static void erstelleKunden(final String vorname, final String nachname, final String passwort, final String passwortWiederholung) {
        if (!passwort.equals(passwortWiederholung) || vorname.length() == 0 || nachname.length() == 0 || passwort.length() < 5) {
            throw new IllegalArgumentException();
        }
        String kundenID = Umwandlung.namenZuKundenID(vorname, nachname);
        DBUser.erstelleKunden(vorname, nachname, kundenID, passwort);
        DBKonto.erstelleKonto(kundenID);
    }
}