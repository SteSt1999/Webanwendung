package Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;

public class DBKontostand {
    final private static String sqlKontostandAendern = "UPDATE KONTO SET KONTOSTAND = \"(?)\" WHERE KUNDEN_ID = \"(?)\";";
    final private static String sqlKontostandLesen = "SELECT KONTOSTAND FROM KONTO WHERE KUNDEN_ID = \"(?)\";";

    public static void kontostandAnpassen(String kundenID, long betrag) {
        String sqlAnfrage = sqlKontostandAendern;
        sqlAnfrage = replaceFirst(sqlAnfrage, betrag + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        DBHelper.sqlAusfuehren(sqlAnfrage);
    }

    public static long kontostandLesen(String kundenID) {
        String sqlAnfrage = sqlKontostandLesen;
        sqlAnfrage = replaceFirst(sqlAnfrage, kundenID);
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage);
        try {
            if (resultSet.next()) {
                return resultSet.getInt("KONTOSTAND");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Das Konto \"" + kundenID + "\" existiert nicht.");
    }
}