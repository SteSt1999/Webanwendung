package Datenbank;

import Logik.Verwaltung.Transaction;

import java.sql.*;

public class Datenbank {
    final private static String user = "root";
    final static private String password = Passwort.passwort;
    final private static String driver = "com.mysql.cj.jdbc.Driver";
    final static private String url = "jdbc:mysql://localhost:3306/hostbank?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    final private static String sqlCheckPasswortKunde = "SELECT * FROM KUNDEN WHERE ID = \"(?)\";";


    public static boolean checkPasswortKunde(String kundenID, String passwort) {
        String sqlAnfrage = sqlCheckPasswortKunde.replace("(?)", kundenID);
        System.out.println(sqlAnfrage);
        ResultSet resultSet = sql(sqlAnfrage);
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

    public static void logHinzufügen(Transaction transaction) {
    }

    public static void kontostandAnpassen(long betrag) {
    }

    ;

    public static ResultSet sql(String sqlAnfrage) {
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
}