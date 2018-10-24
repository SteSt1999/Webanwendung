package Datenbank;

import Logik.Verwaltung.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;
import static Datenbank.DBHelper.replaceFirstWithNulll;

public class DBLog {
    final private static String sqlTransactionHinzufuegen = "INSERT INTO TRANSAKTIONEN VALUES (\"(?)\", \"(?)\", \"(?)\", \"(?)\", \"(?)\");";
    final private static String sqlGetTransactionsKunde = "SELECT ZUGANGSWEG, TRANSAKTIONS_ID, BETRAG, EMPFÄNGER_ID FROM TRANSAKTIONEN WHERE KUNDEN_ID = \"(?)\";";
    final private static String sqlGetTransactionsATM = "SELECT KUNDEN_ID, TRANSAKTIONS_ID, BETRAG, EMPFÄNGER_ID FROM TRANSAKTIONEN WHERE ZUGANGSWEG = \"(?)\";";
    final private static String sqlGetAllTransactions = "SELECT * FROM TRANSAKTIONEN;";

    public static void logHinzufuegen(Transaction transaction) {
        String sqlAnfrage = sqlTransactionHinzufuegen;
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getSender().getBenutzername());
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getZugangsweg().getdbBezeichnung() + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getTransaktionsID() + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getBetrag() + "");
        if(transaction.getEmpfänger() != null) {
            sqlAnfrage = replaceFirst(sqlAnfrage, transaction.getEmpfänger().getBenutzername());
        } else {
            sqlAnfrage = replaceFirstWithNulll(sqlAnfrage);
        }
        DBHelper.sqlAusfuehren(sqlAnfrage);
    }

    public static String getUserLog(String kundenID) {
        String sqlAnfrage = sqlGetTransactionsKunde;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage);
        StringBuilder sb = new StringBuilder();
        try {
            while (resultSet.next()) {
                sb.append(userTransaktionToString(
                        resultSet.getString("ZUGANGSWEG"),
                        resultSet.getInt("TRANSAKTIONS_ID"),
                        resultSet.getLong("Betrag"),
                        resultSet.getString("EMPFÄNGER_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getZugangswegLog(String ATMID) {
        String sqlAnfrage = sqlGetTransactionsATM;
        sqlAnfrage = replaceFirst(sqlAnfrage, ATMID);
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage);
        StringBuilder sb = new StringBuilder();
        try {
            while (resultSet.next()) {
                sb.append(atmTtransaktionToString(
                        resultSet.getString("KUNDEN_ID"),
                        resultSet.getInt("TRANSAKTIONS_ID"),
                        resultSet.getLong("Betrag"),
                        resultSet.getString("EMPFÄNGER_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getBankLog() {
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlGetAllTransactions);
        StringBuilder sb = new StringBuilder();
        try {
            while (resultSet.next()) {
                sb.append(bankTransaktionToString(
                        resultSet.getString("KUNDEN_ID"),
                        resultSet.getString("ZUGANGSWEG"),
                        resultSet.getInt("TRANSAKTIONS_ID"),
                        resultSet.getLong("Betrag"),
                        resultSet.getString("EMPFÄNGER_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static String userTransaktionToString(String zugangsweg, int transaktionsID, long betrag, String empfaengerID) {
        return getTextTransaktionsDaten(transaktionsID, empfaengerID, betrag) + getTextZugangsweg(zugangsweg) + "\n";
    }

    private static String atmTtransaktionToString(String kundenID, int transaktionsID, long betrag, String empfaengerID) {
        return getTextKundenDaten(kundenID) + getTextTransaktionsDaten(transaktionsID, empfaengerID, betrag) + "\n";
    }

    private static String bankTransaktionToString(String kundenID, String zugangsweg, int transaktionsID, long betrag, String empfaengerID) {
        return getTextKundenDaten(kundenID) + getTextTransaktionsDaten(transaktionsID, empfaengerID, betrag)
                + getTextZugangsweg(zugangsweg) + "\n";
    }

    private static String getTextKundenDaten(String kundenID) {
        return kundenID + ":    ";
    }

    private static String getTextTransaktionsDaten(int transaktionsID, String empfaengerID, long betrag) {
        String geld = betrag / 100. + "€      ";
        if (transaktionsID == 1) {
            return geld + "Geld überwiesen an " + empfaengerID;
        } else if (transaktionsID == 2) {
            return geld + "Geld erhalten von " + empfaengerID;
        } else if (transaktionsID == 3) {
            return geld + "Geld abgehoben";
        } else if (transaktionsID == 4) {
            return geld + "Geld eingezahlt";
        }
        return geld + "Unbekannte Transaktion ";
    }

    private static String getTextZugangsweg(String zugangsweg) {
        if (zugangsweg.equals("0")) {
            return "";
        } else if (zugangsweg.equals("1")) {
            return " von einem Mitarbeiter der Bank";
        } else if (zugangsweg.equals("2")) {
            return " über Online-Banking";
        } else {
            return " an ATM " + zugangsweg;
        }
    }
}