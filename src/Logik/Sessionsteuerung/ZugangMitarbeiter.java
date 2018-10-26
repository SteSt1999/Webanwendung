package Logik.Sessionsteuerung;

public class ZugangMitarbeiter implements Zugangsweg {
    final private String dbBezeichnung = "1";

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }
}