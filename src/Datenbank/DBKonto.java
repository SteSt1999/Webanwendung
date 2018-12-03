package Datenbank;

import Logik.Verwaltung.Kunde;

import java.sql.*;

public class DBKonto {
    private static final String sqlKontostandAendern = "UPDATE KONTO SET KONTOSTAND = ? WHERE KUNDEN_ID = ?;";
    private static final String sqlKontostandLesen = "SELECT KONTOSTAND FROM KONTO WHERE KUNDEN_ID = ?;";
    private static final String sqlErstelleKonto = "INSERT INTO KONTO VALUES(?, ?);";

    public static void kontostandAnpassen(final Kunde kunde, final long betrag) {

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(kunde.getBank().getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlKontostandAendern);
            preparedSQL.setString(2,kunde.getBenutzername());
            preparedSQL.setString(1, betrag+"");
             preparedSQL.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static long kontostandLesen(final Kunde kunde) {
        ResultSet resultSet = null;

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(kunde.getBank().getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlKontostandLesen);

            preparedSQL.setString(1, kunde.getBenutzername());
            resultSet = preparedSQL.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }

        try {
            if (resultSet.next()) {
                return resultSet.getInt("KONTOSTAND");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Das Konto \"" + kunde.getBenutzername() + "\" existiert nicht.");
    }

    public static void erstelleKonto(final Kunde kunde) {

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(kunde.getBank().getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlErstelleKonto);
            preparedSQL.setString(1,kunde.getBenutzername());
            preparedSQL.setString(2, "0");
            preparedSQL.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}