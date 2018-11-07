package Datenbank;

import Servlet.MainServlet;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;

public class DBUser {
    private static final String sqlCheckPasswortKunde = "SELECT KENNWORT FROM KUNDEN WHERE ID = \"(?)\";";
    private static final String sqlCheckPasswortMitarbeiter = "SELECT KENNWORT FROM MITARBEITER WHERE ID = \"(?)\";";
    private static final String sqlExistiertKunde = "SELECT ID FROM KUNDEN WHERE ID = \"(?)\";";
    private static final String sqlExistiertMitarbeiter = "SELECT ID FROM MITARBEITER WHERE ID = \"(?)\";";
    private static final String sqlErstelleKunden = "INSERT INTO KUNDEN VALUES(\"(?)\", \"(?)\", \"(?)\", \"(?)\");";

    public static boolean checkPasswortKunde(final String kundenID, final String passwort) {
        return checkPasswort(sqlCheckPasswortKunde, kundenID, passwort);
    }

    public static boolean checkPasswortMitarbeiter(final String mitarbeiterID, final String passwort) {
        return checkPasswort(sqlCheckPasswortMitarbeiter, mitarbeiterID, passwort);
    }

    private static boolean checkPasswort(String sqlAnfrage, final String id, final String passwort) {
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

    public static boolean existiertKunde(final String kundenID, final String bankID) {
        String sqlAnfrage = sqlExistiertKunde;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        return DBHelper.existiert(sqlAnfrage, bankID);
    }

    public static boolean existiertMitarbeiter(final String mitarbeiterID, final String bankID) {
        String sqlAnfrage = sqlExistiertMitarbeiter;
        sqlAnfrage = replaceFirst(sqlAnfrage, mitarbeiterID);
        return DBHelper.existiert(sqlAnfrage, bankID);
    }

    public static void erstelleKunden(final String vorname, final String nachname, final String kundenID, final String passwort) {
        String sqlAnfrage = sqlErstelleKunden;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        sqlAnfrage = replaceFirst(sqlAnfrage, passwort);
        sqlAnfrage = replaceFirst(sqlAnfrage, nachname);
        sqlAnfrage = replaceFirst(sqlAnfrage, vorname);
        DBHelper.sqlAusfuehren(sqlAnfrage, MainServlet.getBank().getBankID());
    }
}