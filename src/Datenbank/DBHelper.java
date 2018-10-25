package Datenbank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

class DBHelper {
    private static String user;
    private static String password;
    final private static String driver = "com.mysql.cj.jdbc.Driver";
    final private static String url = "jdbc:mysql://localhost:3306/hostbank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

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

    public static boolean existiert(String sqlAnfrage) {
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage);
        try {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String replaceFirst(String sqlAnfrage, String eingabe) {
        int n = sqlAnfrage.indexOf("(?)");
        return sqlAnfrage.substring(0, n) + eingabe + sqlAnfrage.substring(n + 3);
    }

    public static String replaceFirstWithNulll(String sqlAnfrage) {
        int n = sqlAnfrage.indexOf("\"(?)\"");
        return sqlAnfrage.substring(0, n) + "NULL" + sqlAnfrage.substring(n + 5);
    }
}