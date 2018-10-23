package Datenbank;

import Logik.Verwaltung.User;

import java.sql.*;

public class Datenbank {
    final private static String user = "root";
    final static private String password = Passwort.passwort;
    final private static String driver = "com.mysql.cj.jdbc.Driver";
    final static private String url = "jdbc:mysql://localhost:3306/hostbank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    final private static String sqlCheckPasswortKunde = "SELECT * FROM KUNDEN WHERE ID = \"(?)\";";
    final private static String sqlCheckPasswortMitarbeiter = "SELECT * FROM MITARBEITER WHERE ID = \"(?)\";";

    public static boolean checkPasswortKunde(String kundenID, String passwort) {
        String sqlAnfrage = sqlCheckPasswortKunde;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        return checkPasswort(sqlAnfrage, passwort);
    }

    public static boolean checkPasswortMitarbeiter(String mitarbeiterID, String passwort) {
        String sqlAnfrage = sqlCheckPasswortMitarbeiter;
        sqlAnfrage = replaceFirst(sqlAnfrage, mitarbeiterID);
        return checkPasswort(sqlAnfrage, passwort);
    }

    public static boolean checkPasswort(String sqlAnfrage, String passwort) {
        ResultSet resultSet = sqlGetResultSet(sqlAnfrage);
        try {
            while (resultSet.next()) {
                if (passwort.equals(resultSet.getString("Kennwort"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ResultSet sqlGetResultSet(String sqlAnfrage) {
        ResultSet resultSet = null;
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(sqlAnfrage);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void kontostandAnpassen(User user, long betrag) {
    }



    public static void sqlAusfuehren(String sqlAnfrage) {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlAnfrage);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String replaceFirst(String sqlAnfrage, String eingabe) {
        int n = sqlAnfrage.indexOf("(?)");
        return sqlAnfrage.substring(0, n) + eingabe + sqlAnfrage.substring(n + 3);
    }
}