package Datenbank;

import Logik.Verwaltung.Bank;
import Servlet.MainServlet;

import java.sql.*;

import static Datenbank.DBHelper.replaceFirst;

public class DBUser {

    private static final String sqlCheckPasswortKunde = "SELECT KENNWORT FROM KUNDEN WHERE ID = ?;";
    private static final String sqlCheckPasswortMitarbeiter = "SELECT KENNWORT FROM MITARBEITER WHERE ID = ?;";
    private static final String sqlExistiertKunde = "SELECT ID FROM KUNDEN WHERE ID = ?;";
    private static final String sqlExistiertMitarbeiter = "SELECT ID FROM MITARBEITER WHERE ID = ?;";
    private static final String sqlErstelleKunden = "INSERT INTO KUNDEN VALUES(?, ?, ?, ?);";

    public static boolean checkPasswortKunde(final String kundenID, final String passwort, final Bank bank) {
        return checkPasswort(sqlCheckPasswortKunde, kundenID, passwort, bank);
    }

    public static boolean checkPasswortMitarbeiter(final String mitarbeiterID, final String passwort, final Bank bank) {
        return checkPasswort(sqlCheckPasswortMitarbeiter, mitarbeiterID, passwort, bank);
    }

    private static boolean checkPasswort(String sqlAnfrage, final String id, final String passwort, final Bank bank) {
        ResultSet resultSet = null;

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(bank.getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlAnfrage);
            preparedSQL.setString(1, id);
            resultSet = preparedSQL.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }

        try {
            if (resultSet.next() && passwort.equals(resultSet.getString("KENNWORT"))) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean existiertKunde(final String kundenID, final Bank bank) {
        ResultSet resultSet = null;

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(bank.getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlExistiertKunde);
            preparedSQL.setString(1, kundenID);
            resultSet = preparedSQL.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        try {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean existiertMitarbeiter(final String mitarbeiterID, final Bank bank) {
        ResultSet resultSet = null;

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(bank.getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlExistiertMitarbeiter);
            preparedSQL.setString(1, mitarbeiterID);
            resultSet = preparedSQL.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        try {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void erstelleKunden(final String vorname, final String nachname, final String kundenID, final String passwort, final Bank bank) {

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(bank.getBankID()), DBHelper.getUser(), DBHelper.getPassword());

            PreparedStatement preparedSQL = conn.prepareStatement(sqlErstelleKunden);
            preparedSQL.setString(1, kundenID);
            preparedSQL.setString(2, passwort);
            preparedSQL.setString(3, nachname);
            preparedSQL.setString(4, vorname);

            preparedSQL.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }



    }
}