package Logik.Verwaltung;

public class Kunde extends User {
    //Konto und Kunde bei welcher Bank
    private Konto konto;

    public Kunde(Konto konto, String benutzername) {
        super(benutzername);
        setKonto(konto);

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
}