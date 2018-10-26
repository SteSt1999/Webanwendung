package Datenbank;

import Logik.Verwaltung.Kunde;
import Servlet.MainServlet;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;

public class DBKontostand {
    final private static String sqlKontostandAendern = "UPDATE KONTO SET KONTOSTAND = \"(?)\" WHERE KUNDEN_ID = \"(?)\";";
    final private static String sqlKontostandLesen = "SELECT KONTOSTAND FROM KONTO WHERE KUNDEN_ID = \"(?)\";";

    public static void kontostandAnpassen(Kunde kunde, long betrag) {
        String sqlAnfrage = sqlKontostandAendern;
        sqlAnfrage = replaceFirst(sqlAnfrage, betrag + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, kunde.getBenutzername());
        DBHelper.sqlAusfuehren(sqlAnfrage, kunde.getBank().getBankID());
    }

    public static long kontostandLesen(String kundenID) {
        return kontostandLesen(kundenID, MainServlet.getBankID());
    }

    public static long kontostandLesen(String kundenID, String bankID) {
        String sqlAnfrage = sqlKontostandLesen;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage, bankID);
        try {
            if (resultSet.next()) {
                return resultSet.getInt("KONTOSTAND");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Das Konto \"" + kundenID + "\" existiert nicht.");
    }
}