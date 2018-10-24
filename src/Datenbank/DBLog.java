package Datenbank;

import Logik.Verwaltung.*;

import java.sql.ResultSet;

import static Datenbank.DBHelper.replaceFirst;

public class DBLog {
    final private static String sqlTransactionHinzufuegen = "INSERT INTO TRANSAKTIONEN VALUES (\"(?)\", \"(?)\", \"(?)\", \"(?)\", \"(?)\");";
    final private static String sqlGetAllTransactions = "SELECT * FROM TRANSAKTIONEN;";
    final private static String sqlGetTransactionsKunde = "SELECT * FROM TRANSAKTIONEN WHERE KUNDEN_ID = \"(?)\";";
    final private static String sqlGetTransactionsATM = "SELECT * FROM TRANSAKTIONEN WHERE ZUGANGSWEG = \"(?)\";";

    public static void logHinzufuegen(Transaction transaction) {
        String sqlAnfrage = sqlTransactionHinzufuegen;
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getSender().getBenutzername());
        //TODO ZugangswegID speichern
        sqlAnfrage = replaceFirst(sqlAnfrage, 2 + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getTransaktionsID() + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getBetrag() + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getEmpfänger().getBenutzername());
        DBHelper.sqlAusfuehren(sqlAnfrage);
    }

    public static ResultSet getUserLog(String kundenID) {
        String sqlAnfrage = sqlGetTransactionsKunde;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID + "");
        return DBHelper.sqlGetResultSet(sqlAnfrage);
    }

    public static ResultSet getATMLog(int ATMID) {
        String sqlAnfrage = sqlGetTransactionsATM;
        sqlAnfrage = replaceFirst(sqlAnfrage, ATMID + "");
        return DBHelper.sqlGetResultSet(sqlAnfrage);
    }

    public static ResultSet getBankLog() {
        return DBHelper.sqlGetResultSet(sqlGetAllTransactions);
    }
}