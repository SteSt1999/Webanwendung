package Datenbank;

import java.sql.*;

public class DBHelper {
    final private static String user = "root";
    final static private String password = Passwort.dbPasswort;
    final private static String driver = "com.mysql.cj.jdbc.Driver";
    final static private String url = "jdbc:mysql://localhost:3306/hostbank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

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