package Logik.Verwaltung;

import static Datenbank.DBKontostand.kontostandAnpassen;

public class Konto {

    private long kontostand;


    public Konto(long kontostand){
        setKontostand(kontostand);


    }

    public long getKontostand() {
        return kontostand;
    }
    public void setKontostand(long kontostand) {
        this.kontostand = kontostand;
    }

    public void abheben(User user,long betrag) {
        this.kontostand -= betrag;
        kontostandAnpassen(user.getBenutzername(),kontostand);
    }

   public void einzahlen(User user,long betrag) {
         this.kontostand += betrag ;
        kontostandAnpassen(user.getBenutzername(),kontostand);
    }
    public void überweisen(User sender, User empfänger, long betrag) {
        ((Kunde) empfänger).getKonto().einzahlen(empfänger, betrag);
        this.kontostand -= betrag;

        kontostandAnpassen(sender.getBenutzername(),kontostand);
    }



}
