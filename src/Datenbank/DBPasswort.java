package Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;

public class DBPasswort {
    final private static String sqlCheckPasswortKunde = "SELECT KENNWORT FROM KUNDEN WHERE ID = \"(?)\";";
    final private static String sqlCheckPasswortMitarbeiter = "SELECT KENNWORT FROM MITARBEITER WHERE ID = \"(?)\";";

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
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage);
        try {
            if (resultSet.next() && passwort.equals(resultSet.getString(1))) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}