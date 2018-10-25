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

    public void abheben(User user, long betrag) {
        this.kontostand += betrag;
        kontostandAnpassen(user.getBenutzername(), kontostand);
    }

    public void einzahlen(User user, long betrag) {
        this.kontostand += betrag;
        kontostandAnpassen(user.getBenutzername(), kontostand);
    }

    public void abhebenNeu(Kunde kunde, long betrag) {
        this.kontostand += betrag;
        kontostandAnpassen(kunde, kontostand);
    }

    public void einzahlenNeu(Kunde kunde, long betrag) {
        this.kontostand += betrag;
        kontostandAnpassen(kunde, kontostand);
    }

    public void ueberweisen(User sender, User empfaenger, long betrag) {
        ((Kunde) empfaenger).getKonto().einzahlen(empfaenger, betrag);
        this.kontostand -= betrag;
        kontostandAnpassen(sender.getBenutzername(), kontostand);
    }
}