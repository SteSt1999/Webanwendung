package Datenbank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

class DBHelper {
    private static String user;
    private static String password;

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }

    public static String getDriver() {
        return driver;
    }

    private static final String driver = "com.mysql.cj.jdbc.Driver";

    static {
        File f = new File("Webanwendung/DBDaten.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            user = br.readLine();
            password = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ResultSet sqlGetResultSet(final String sqlAnfrage, final String dbID) {
        ResultSet resultSet = null;
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(getUrl(dbID), user, password);
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(sqlAnfrage);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    static void sqlAusfuehren(final String sqlAnfrage, final String dbID) {
        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(getUrl(dbID), user, password);
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlAnfrage);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static boolean existiert(final String sqlAnfrage, final String dbID) {
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage, dbID);
        try {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static String replaceFirst(final String sqlAnfrage, final String eingabe) {
        int n = sqlAnfrage.indexOf("(?)");
        return sqlAnfrage.substring(0, n) + eingabe + sqlAnfrage.substring(n + 3);
    }

    static String replaceFirstWithNull(final String sqlAnfrage) {
        int n = sqlAnfrage.indexOf("\"(?)\"");
        return sqlAnfrage.substring(0, n) + "NULL" + sqlAnfrage.substring(n + 5);
    }

     static String getUrl(final String schemaID) {
        return "jdbc:mysql://localhost:3306/"
                + schemaID +
                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }
}