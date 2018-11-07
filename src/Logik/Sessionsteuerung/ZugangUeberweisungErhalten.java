package Logik.Sessionsteuerung;

public class ZugangUeberweisungErhalten implements Zugangsweg {
    private final String DB_BEZEICHNUNG = "0";

    public String getdbBezeichnung() {
        return DB_BEZEICHNUNG;
    }
}