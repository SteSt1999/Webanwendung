package Logik.Verwaltung;

import static Datenbank.DBKontostand.kontostandAnpassen;

public class Konto {
    private long kontostand;

    public Konto(long kontostand) {
        setKontostand(kontostand);
    }

    public long getKontostand() {
        return kontostand;
    }

    public void setKontostand(long kontostand) {
        this.kontostand = kontostand;
    }

    public void abheben(Kunde kunde, long betrag) {
        this.kontostand -= betrag;
        kontostandAnpassen(kunde, kontostand);
    }

    public void einzahlen(Kunde kunde, long betrag) {
        this.kontostand += betrag;
        kontostandAnpassen(kunde, kontostand);
    }

    public static void ueberweisen(Kunde sender, Kunde empfaenger, long betrag) {
        sender.getKonto().abheben(sender, betrag);
        empfaenger.getKonto().einzahlen(empfaenger, betrag);
    }
}