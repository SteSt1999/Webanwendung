package Logik.Sessionsteuerung;

public class OnlineBankingZugang implements Zugangsweg {
    final private String dbBezeichnung = "2";

    public OnlineBankingZugang() {
    }

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }
}