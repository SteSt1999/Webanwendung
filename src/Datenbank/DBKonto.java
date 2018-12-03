package Datenbank;

import Logik.Verwaltung.Kunde;
import Servlet.MainServlet;

import java.sql.*;

import static Datenbank.DBHelper.replaceFirst;

public class DBKonto {
    private static final String sqlKontostandAendern = "UPDATE KONTO SET KONTOSTAND = ? WHERE KUNDEN_ID = ?;";
    private static final String sqlKontostandLesen = "SELECT KONTOSTAND FROM KONTO WHERE KUNDEN_ID = ?;";
    private static final String sqlErstelleKonto = "INSERT INTO KONTO VALUES(?, ?);";

    public static void kontostandAnpassen(final Kunde kunde, final long betrag) {

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(MainServlet.getBank().getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlKontostandAendern);
            preparedSQL.setString(2,kunde.getBenutzername());
            preparedSQL.setString(1, betrag+"");
             preparedSQL.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static long kontostandLesen(final String kundenID, final String bankID) {
        ResultSet resultSet = null;

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(MainServlet.getBank().getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlKontostandLesen);

            preparedSQL.setString(1, kundenID);
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
        throw new IllegalArgumentException("Das Konto \"" + kundenID + "\" existiert nicht.");
    }

    public static void erstelleKonto(final String kundenID) {

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(MainServlet.getBank().getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlErstelleKonto);
            preparedSQL.setString(1,kundenID);
            preparedSQL.setString(2, "0");
            preparedSQL.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}