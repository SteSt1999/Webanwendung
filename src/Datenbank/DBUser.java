package Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;

public class DBUser {
    final private static String sqlCheckPasswortKunde = "SELECT KENNWORT FROM KUNDEN WHERE ID = \"(?)\";";
    final private static String sqlCheckPasswortMitarbeiter = "SELECT KENNWORT FROM MITARBEITER WHERE ID = \"(?)\";";
    final private static String sqlExistiertKunde = "SELECT ID FROM KUNDEN WHERE ID = \"(?)\";";

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

    private static boolean checkPasswort(String sqlAnfrage, String passwort) {
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage);
        try {
            if (resultSet.next() && passwort.equals(resultSet.getString("KENNWORT"))) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean existiertKunde(String kundenID) {
        String sqlAnfrage = sqlExistiertKunde;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        return DBHelper.existiert(sqlAnfrage);
    }

    public static boolean existiertKunde(String kundenID, String bankID) {
        String sqlAnfrage = sqlExistiertKunde;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        return DBHelper.existiert(sqlAnfrage, bankID);
    }
}