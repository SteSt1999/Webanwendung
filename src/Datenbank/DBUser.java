package Datenbank;

import Servlet.MainServlet;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;

public class DBUser {
    final private static String sqlCheckPasswortKunde = "SELECT KENNWORT FROM KUNDEN WHERE ID = \"(?)\";";
    final private static String sqlCheckPasswortMitarbeiter = "SELECT KENNWORT FROM MITARBEITER WHERE ID = \"(?)\";";
    final private static String sqlExistiertKunde = "SELECT ID FROM KUNDEN WHERE ID = \"(?)\";";
    final private static String sqlExistiertMitarbeiter = "SELECT ID FROM MITARBEITER WHERE ID = \"(?)\";";

    public static boolean checkPasswortKunde(String kundenID, String passwort) {
        return checkPasswort(sqlCheckPasswortKunde, kundenID, passwort);
    }

    public static boolean checkPasswortMitarbeiter(String mitarbeiterID, String passwort) {
        return checkPasswort(sqlCheckPasswortMitarbeiter, mitarbeiterID, passwort);
    }

    private static boolean checkPasswort(String sqlAnfrage, String id, String passwort) {
        sqlAnfrage = replaceFirst(sqlAnfrage, id);
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage, MainServlet.getBank().getBankID());
        try {
            if (resultSet.next() && passwort.equals(resultSet.getString("KENNWORT"))) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean existiertKunde(String kundenID, String bankID) {
        String sqlAnfrage = sqlExistiertKunde;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        return DBHelper.existiert(sqlAnfrage, bankID);
    }

    public static boolean existiertMitarbeiter(String mitarbeiterID, String bankID) {
        String sqlAnfrage = sqlExistiertMitarbeiter;
        sqlAnfrage = replaceFirst(sqlAnfrage, mitarbeiterID);
        return DBHelper.existiert(sqlAnfrage, bankID);
    }
}