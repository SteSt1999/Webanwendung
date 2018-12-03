package Datenbank;

import Logik.Umwandlung;
import Logik.Verwaltung.*;

import java.sql.*;

public class DBLog {
    private static final String sqlTransactionHinzufuegen = "INSERT INTO TRANSAKTIONEN VALUES (?, ?, ?, ?, ?, ?);";
    private static final String sqlGetTransactionsKunde = "SELECT ZUGANGSWEG, TRANSAKTIONS_ID, BETRAG, EMPFAENGER_ID, EMPFAENGERBANK_ID FROM TRANSAKTIONEN WHERE KUNDEN_ID = ?;";
    private static final String sqlGetTransactionsATM = "SELECT KUNDEN_ID, TRANSAKTIONS_ID, BETRAG, EMPFAENGER_ID, EMPFAENGERBANK_ID FROM TRANSAKTIONEN WHERE ZUGANGSWEG = ?;";
    private static final String sqlGetAllTransactions = "SELECT * FROM TRANSAKTIONEN;";

    public static void logHinzufuegen(final Transaction transaction) {

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(transaction.getSender().getBank().getBankID()), DBHelper.getUser(), DBHelper.getPassword());

            PreparedStatement preparedSQL = conn.prepareStatement(sqlTransactionHinzufuegen);
            preparedSQL.setString(1, transaction.getSender().getBenutzername());
            preparedSQL.setString(2, transaction.getZugangsweg().getdbBezeichnung() + "");
            preparedSQL.setString(3, transaction.getTransaktionsID() + "");
            preparedSQL.setString(4, transaction.getBetrag() + "");
            if (transaction.getEmpfaenger() != null) {
                preparedSQL.setString(5,  transaction.getEmpfaenger().getBenutzername());
                preparedSQL.setString(6,transaction.getEmpfaenger().getBank().getBankID());
            } else {
                preparedSQL.setString(5,  null);
                preparedSQL.setString(6,  null);
            }

            preparedSQL.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    public static String getKundenLog(final Kunde kunde) {
        ResultSet resultSet = null;
        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(kunde.getBank().getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlGetTransactionsKunde);
            preparedSQL.setString(1, kunde.getBenutzername());


            resultSet = preparedSQL.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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

        public static String getZugangswegLog (final ATM atm){
            ResultSet resultSet = null;
            try {
                Class.forName(DBHelper.getDriver());
                Connection conn = DriverManager.getConnection(DBHelper.getUrl(atm.getBank().getBankID()), DBHelper.getUser(), DBHelper.getPassword());
                PreparedStatement preparedSQL = conn.prepareStatement(sqlGetTransactionsATM);
                preparedSQL.setString(1, atm.getId());


                resultSet = preparedSQL.executeQuery();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
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

        public static String getBankLog(final Bank bank) {
            ResultSet resultSet = null;
            try {
                Class.forName(DBHelper.getDriver());
                Connection conn = DriverManager.getConnection(DBHelper.getUrl(bank.getBankID()), DBHelper.getUser(), DBHelper.getPassword());
                PreparedStatement preparedSQL = conn.prepareStatement(sqlGetAllTransactions);



                resultSet = preparedSQL.executeQuery();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
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

        private static String userTransaktionToString ( final String zugangsweg, final int transaktionsID,
        final long betrag, final String empfaengerID, final String empfaengerBank){
            return getTextTransaktionsDaten(transaktionsID, empfaengerID, empfaengerBank, betrag) + getTextZugangsweg(zugangsweg) + "<br>";
        }

        private static String atmTtransaktionToString ( final String kundenID, final int transaktionsID,
        final long betrag, final String empfaengerID, final String empfaengerBank){
            return getTextKundenDaten(kundenID) + getTextTransaktionsDaten(transaktionsID, empfaengerID, empfaengerBank, betrag) + "<br>";
        }

        private static String bankTransaktionToString ( final String kundenID, final String zugangsweg,
        final int transaktionsID, final long betrag, final String empfaengerID, final String empfaengerBank){
            return getTextKundenDaten(kundenID) + getTextTransaktionsDaten(transaktionsID, empfaengerID, empfaengerBank, betrag)
                    + getTextZugangsweg(zugangsweg) + "<br>";
        }

        private static String getTextKundenDaten ( final String kundenID){
            return kundenID + ":    ";
        }

        private static String getTextTransaktionsDaten ( final int transaktionsID, final String empfaengerID,
        final String empfaengerBank, final long betrag){
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

        private static String getTextZugangsweg ( final String zugangsweg){
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