package Datenbank;

import Logik.Verwaltung.Kunde;
import Servlet.MainServlet;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;

public class DBKonto {
    private static final String sqlKontostandAendern = "UPDATE KONTO SET KONTOSTAND = \"(?)\" WHERE KUNDEN_ID = \"(?)\";";
    private static final String sqlKontostandLesen = "SELECT KONTOSTAND FROM KONTO WHERE KUNDEN_ID = \"(?)\";";
    private static final String sqlErstelleKonto = "INSERT INTO KONTO VALUES(\"(?)\", \"0\");";

    public static void kontostandAnpassen(final Kunde kunde, final long betrag) {
        String sqlAnfrage = sqlKontostandAendern;
        sqlAnfrage = replaceFirst(sqlAnfrage, betrag + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, kunde.getBenutzername());
        DBHelper.sqlAusfuehren(sqlAnfrage, kunde.getBank().getBankID());
    }

    public static long kontostandLesen(final String kundenID, final String bankID) {
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

    public static void erstelleKonto(final String kundenID) {
        String sqlAnfrage = sqlErstelleKonto;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        DBHelper.sqlAusfuehren(sqlAnfrage, MainServlet.getBank().getBankID());
    }
}