package Datenbank;

import Logik.Verwaltung.Transaction;

import java.sql.ResultSet;

import static Datenbank.Datenbank.replaceFirst;

public class DBLog {
    final private static String sqlTransactionHinzufuegen = "INSERT INTO TRANSAKTIONEN (\"(?)\", \"(?)\", \"(?)\", \"(?)\", \"(?)\", \"(?)\");";
    final private static String sqlGetAllTransactions = "SELECT * FROM TRANSAKTIONEN;";
    final private static String sqlGetTransactionsKunde = "SELECT * FROM TRANSAKTIONEN WHERE KUNDEN_ID = \"(?)\";";
    final private static String sqlGetTransactionsATM = "SELECT * FROM TRANSAKTIONEN WHERE ZUGANGSWEG = \"(?)\";";

    /*
    private static void logHinzufügen(String kundenID, int zugangsweg, int transaktionsID, int betrag, String empfaengerBank, String empfaengerID) {
        String sqlAnfrage = sqlTransactionHinzufuegen;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        sqlAnfrage = replaceFirst(sqlAnfrage, zugangsweg + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, transaktionsID + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, betrag + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, empfaengerBank);
        sqlAnfrage = replaceFirst(sqlAnfrage, empfaengerID);
        System.out.println(sqlAnfrage);
        Datenbank.sqlAusfuehren(sqlAnfrage);
    }
    */

    public static void logHinzufügen(Transaction t) {

    }

    public static ResultSet getATMLog(int id) {
        return null;
    }

    public static ResultSet getUserLog(String benutzername) {
        return null;
    }
    public static ResultSet getBankLog() {
        return null;
    }

    /*
    public static void main(String[] args) {
        logHinzufügen("1", 2, 3, 4, "5", "6");
    }
    */
}