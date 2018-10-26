package Logik.Sessionsteuerung;

import Logik.Verwaltung.Kunde;

import static Datenbank.DBLog.getBankLog;
import static Datenbank.DBLog.getUserLog;
import static Datenbank.DBLog.getZugangswegLog;

public class ZugangMitarbeiter implements Zugangsweg {
    final private String dbBezeichnung = "1";

    public String getdbBezeichnung() {
        return dbBezeichnung;
    }

    public static String ausgabeATMLog(String atmID) {
        return getZugangswegLog(atmID);
    }

    public static String ausgabeUserLog(Kunde user) {
        return getUserLog(user.getBenutzername());
    }

    public static String ausgabeBankLog() {
        return getBankLog();
    }
}