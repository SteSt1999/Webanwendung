package Logik.Sessionsteuerung;

import Logik.Verwaltung.ATM;
import Logik.Verwaltung.Kunde;

import static Datenbank.DBLog.getBankLog;
import static Datenbank.DBLog.getKundenLog;
import static Datenbank.DBLog.getZugangswegLog;

public class Log {
    public static String ausgabeATMLog(ATM atm) {
        return getZugangswegLog(atm.getId() + "");
    }

    public static String ausgabeKundenLog(Kunde kunde) {
        return getKundenLog(kunde.getBenutzername());
    }

    public static String ausgabeBankLog() {
        return getBankLog();
    }
}