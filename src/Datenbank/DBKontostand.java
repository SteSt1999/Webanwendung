package Datenbank;

import Logik.Verwaltung.Mitarbeiter;
import Logik.Verwaltung.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Datenbank.DBHelper.replaceFirst;

public class DBKontostand {
    final private static String sqlKontostandAendern = "UPDATE KONTO SET KONTOSTAND = \"(?)\" WHERE KUNDEN_ID = \"(?)\";";
    final private static String sqlKontostandLesen = "SELECT KONTOSTAND FROM KONTO WHERE KUNDEN_ID = \"(?)\";";

    public static void kontostandAnpassen(User user, long betrag) {
        String sqlAnfrage = sqlKontostandAendern;
        sqlAnfrage = replaceFirst(sqlAnfrage, betrag + "");
        sqlAnfrage = replaceFirst(sqlAnfrage, user.getBenutzername());
        DBHelper.sqlAusfuehren(sqlAnfrage);
    }

    public static long kontostandLesen(User kunde) {
        String sqlAnfrage = sqlKontostandLesen;
        sqlAnfrage = replaceFirst(sqlAnfrage, kunde.getBenutzername());
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAnfrage);
        try {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Das Konto \"" + kunde.getBenutzername() + "\2 existiert nicht.");
    }
}