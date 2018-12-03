package Logik.Sessionsteuerung;

import Logik.Verwaltung.ATM;
import Logik.Verwaltung.Bank;
import Logik.Verwaltung.Kunde;

import static Datenbank.DBLog.getBankLog;
import static Datenbank.DBLog.getKundenLog;
import static Datenbank.DBLog.getZugangswegLog;

public class Log {
    public static String ausgabeATMLog(final ATM atm) {
        return getZugangswegLog(atm);
    }

    public static String ausgabeKundenLog(final Kunde kunde) {
        return getKundenLog(kunde);
    }

    public static String ausgabeBankLog(final Bank bank) {
        return getBankLog(bank);
    }
}