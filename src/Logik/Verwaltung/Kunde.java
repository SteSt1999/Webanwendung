package Logik.Verwaltung;

import Datenbank.DBKonto;
import Datenbank.DBUser;
import Logik.Umwandlung;

public class Kunde {
    final Bank bank;
    String benutzername;
    private Konto konto;

    public Kunde(String benutzername, Bank bank) {
        this.bank = bank;
        setBenutzername(benutzername);
        setKonto(new Konto(DBKonto.kontostandLesen(this)));
    }

    public Kunde(String benutzername, String passwort, Bank bank) {
        this.bank = bank;
        this.benutzername = benutzername;
        setKonto(new Konto(DBKonto.kontostandLesen(this)));
        if(!DBUser.checkPasswortKunde(benutzername, passwort, bank)) {
            throw new IllegalArgumentException("Kun");
        }
    }

    public void setBenutzername(String benutzername) {
        if (!DBUser.existiertKunde(benutzername, bank)) {
            throw new IllegalArgumentException();
        }
        this.benutzername = benutzername;
    }

    public Bank getBank() {
        return bank;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setKonto(Konto konto) {
        this.konto = konto;
    }

    public Konto getKonto() {
        return konto;
    }

    public long getKontostand() {
        return konto.getKontostand();
    }

    //TODO Kunde wird erstellt, aber es wird eine Fehlermeldung angezeigt
    public static void erstelleKunden(final String vorname, final String nachname, final String passwort, final String passwortWiederholung, Bank bank) {
        if (!passwort.equals(passwortWiederholung) || vorname.length() == 0 || nachname.length() == 0 || passwort.length() < 5) {
            throw new IllegalArgumentException();
        }
        String kundenID = Umwandlung.namenZuKundenID(vorname, nachname, bank);
        DBUser.erstelleKunden(vorname, nachname, kundenID, passwort, bank);
        Kunde kunde = new Kunde(kundenID, bank);
        DBKonto.erstelleKonto(kunde);
    }
}