package Logik.Verwaltung;



import static Datenbank.Datenbank.kontostandAnpassen;

public class Konto {

    private long kontostand;



    public long getKontostand() {
        return kontostand;
    }

    public void abheben(User user,long betrag) {
        this.kontostand -= betrag;
        kontostandAnpassen(user,kontostand);
    }

   public void einzahlen(User user,long betrag) {
         this.kontostand += betrag ;
        kontostandAnpassen(user,kontostand);
    }
    public void überweisen(User sender, User empfänger, long betrag) {
        ((Kunde) empfänger).getKonto().einzahlen(empfänger, betrag);
        this.kontostand -= betrag;

        kontostandAnpassen(sender,kontostand);
    }



}
