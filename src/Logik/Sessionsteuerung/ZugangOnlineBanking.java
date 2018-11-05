package Logik.Sessionsteuerung;

public class ZugangOnlineBanking implements Zugangsweg {
    private final String dbBezeichnung = "2";

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }
}