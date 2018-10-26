package Logik.Sessionsteuerung;

public class ZugangOnlineBanking implements Zugangsweg {
    final private String dbBezeichnung = "2";

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }
}