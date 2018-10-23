package Logik.Verwaltung;


import java.util.List;

import static Datenbank.Datenbank.kontostandAnpassen;

public class Konto {

    private long kontostand;



    public long getKontostand() {
        return kontostand;
    }

    public void abheben(long betrag) {
        this.kontostand -= betrag;
        kontostandAnpassen(kontostand);
    }

   public void einzahlen(long betrag) {
         this.kontostand += betrag ;
        kontostandAnpassen(kontostand);
    }
    public void überweisen(long betrag) {

    }



}
