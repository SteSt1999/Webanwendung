package Datenbank;

import Logik.Umwandlung;
import Logik.Verwaltung.*;
import Servlet.MainServlet;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;
import static Datenbank.DBHelper.replaceFirstWithNull;

public class DBLog {
    private static final String sqlTransactionHinzufuegen = "INSERT INTO TRANSAKTIONEN VALUES (\"(?)\", \"(?)\", \"(?)\", \"(?)\", \"(?)\", \"(?)\");";
    private static final String sqlGetTransactionsKunde = "SELECT ZUGANGSWEG, TRANSAKTIONS_ID, BETRAG, EMPFAENGER_ID, EMPFAENGERBANK_ID FROM TRANSAKTIONEN WHERE KUNDEN_ID = \"(?)\";";
    private static final String sqlGetTransactionsATM = "SELECT KUNDEN_ID, TRANSAKTIONS_ID, BETRAG, EMPFAENGER_ID, EMPFAENGERBANK_ID FROM TRANSAKTIONEN WHERE ZUGANGSWEG = \"(?)\";";
    private static final String sqlGetAllTransactions = "SELECT * FROM TRANSAKTIONEN;";

    public static void logHinzufuegen(final Transaction transaction) {
        String sqlAnfrage = sqlTransactionHinzufuegen;
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getSender().getBenutzername());
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getZugangsweg().getdbBezeichnung() + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getTransaktionsID() + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getBetrag() + "");
        if (transaction.getEmpfaenger() == null) {
            sqlAnfrage = replaceFirstWithNull(sqlAnfrage);
            sqlAnfrage = replaceFirstWithNull(sqlAnfrage);
        } else {
            sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getEmpfaenger().getBenutzername());
            sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getEmpfaenger().getBank().getBankID());
        }
        DBHelper.sqlAusfuehren(sqlAnfrage, transaction.getSender().getBank().getBankID());
    }

    public static String getKundenLog(final String kundenID) {
        String sqlAnfrage = sqlGetTransactionsKunde;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage, MainServlet.getBank().getBankID());
        StringBuilder sb = new StringBuilder();
        try {
            while (resultSet.next()) {
                sb.append(userTransaktionToString(
                        resultSet.getString("ZUGANGSWEG"),
                        resultSet.getInt("TRANSAKTIONS_ID"),
                        resultSet.getLong("Betrag"),
                        resultSet.getString("EMPFAENGER_ID"),
                        resultSet.getString("EMPFAENGERBANK_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getZugangswegLog(final String ATMID) {
        String sqlAnfrage = sqlGetTransactionsATM;
        sqlAnfrage = replaceFirst(sqlAnfrage, ATMID);
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage, MainServlet.getBank().getBankID());
        StringBuilder sb = new StringBuilder();
        try {
            while (resultSet.next()) {
                sb.append(atmTtransaktionToString(
                        resultSet.getString("KUNDEN_ID"),
                        resultSet.getInt("TRANSAKTIONS_ID"),
                        resultSet.getLong("Betrag"),
                        resultSet.getString("EMPFAENGER_ID"),
                        resultSet.getString("EMPFAENGERBANK_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getBankLog() {
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlGetAllTransactions, MainServlet.getBank().getBankID());
        StringBuilder sb = new StringBuilder();
        try {
            while (resultSet.next()) {
                sb.append(bankTransaktionToString(
                        resultSet.getString("KUNDEN_ID"),
                        resultSet.getString("ZUGANGSWEG"),
                        resultSet.getInt("TRANSAKTIONS_ID"),
                        resultSet.getLong("Betrag"),
                        resultSet.getString("EMPFAENGER_ID"),
                        resultSet.getString("EMPFAENGERBANK_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static String userTransaktionToString(final String zugangsweg, final int transaktionsID, final long betrag, final String empfaengerID, final String empfaengerBank) {
        return getTextTransaktionsDaten(transaktionsID, empfaengerID, empfaengerBank, betrag) + getTextZugangsweg(zugangsweg) + "<br>";
    }

    private static String atmTtransaktionToString(final String kundenID, final int transaktionsID, final long betrag, final String empfaengerID, final String empfaengerBank) {
        return getTextKundenDaten(kundenID) + getTextTransaktionsDaten(transaktionsID, empfaengerID, empfaengerBank, betrag) + "<br>";
    }

    private static String bankTransaktionToString(final String kundenID, final String zugangsweg, final int transaktionsID, final long betrag, final String empfaengerID, final String empfaengerBank) {
        return getTextKundenDaten(kundenID) + getTextTransaktionsDaten(transaktionsID, empfaengerID, empfaengerBank, betrag)
                + getTextZugangsweg(zugangsweg) + "<br>";
    }

    private static String getTextKundenDaten(final String kundenID) {
        return kundenID + ":    ";
    }

    private static String getTextTransaktionsDaten(final int transaktionsID, final String empfaengerID, final String empfaengerBank, final long betrag) {
        String geld = Umwandlung.centToEuroString(betrag) + "      ";
        if (transaktionsID == 1) {
            return geld + " überwiesen an " + empfaengerID + " von der Bank " + empfaengerBank;
        } else if (transaktionsID == 2) {
            return geld + " erhalten von " + empfaengerID + " von der Bank " + empfaengerBank;
        } else if (transaktionsID == 3) {
            return geld + " abgehoben";
        } else if (transaktionsID == 4) {
            return geld + " eingezahlt";
        }
        return geld + "Unbekannte Transaktion ";
    }

    private static String getTextZugangsweg(final String zugangsweg) {
        switch (zugangsweg) {
            case "0":
                return "";
            case "1":
                return " von einem Mitarbeiter der Bank";
            case "2":
                return " über Online-Banking";
            default:
                return " an ATM " + zugangsweg;
        }
    }
}