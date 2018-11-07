package Logik.Sessionsteuerung;

public class ZugangOnlineBanking implements Zugangsweg {
    private final String DB_BEZEICHNUNG = "2";

    public String getdbBezeichnung() {
        return DB_BEZEICHNUNG;
    }
}