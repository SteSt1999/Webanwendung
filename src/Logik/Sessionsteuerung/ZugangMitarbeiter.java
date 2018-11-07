package Logik.Sessionsteuerung;

public class ZugangMitarbeiter implements Zugangsweg {
    private final String DB_BEZEICHNUNG = "1";

    public String getdbBezeichnung() {
        return DB_BEZEICHNUNG;
    }
}