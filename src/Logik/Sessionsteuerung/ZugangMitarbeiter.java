package Logik.Sessionsteuerung;

public class ZugangMitarbeiter implements Zugangsweg {
    private final String dbBezeichnung = "1";

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }
}