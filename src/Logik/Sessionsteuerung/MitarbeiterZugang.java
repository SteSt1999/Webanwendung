package Logik.Sessionsteuerung;

import Logik.Verwaltung.Kunde;

import static Datenbank.DBLog.*;

public class MitarbeiterZugang implements Zugangsweg {
    final private String dbBezeichnung = "1";

    public static String doAusgabeATMLog(String atmID) {
        return getZugangswegLog(atmID);
    }

    public static String doAusgabeUserLog(Kunde user) {
        return getUserLog(user.getBenutzername());
    }

    public static String doAusgabeBankLog() {
        return getBankLog();
    }

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }
}