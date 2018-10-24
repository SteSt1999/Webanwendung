package Datenbank;

import java.sql.ResultSet;

import static Datenbank.DBHelper.replaceFirst;

public class DBATM {
    final private static String sqlExistiertATM = "SELECT * FROM ATM WHERE ATM_ID = \"(?)\";";
    final private static String sqlAlleATM = "SELECT ATM_ID FROM ATM;";

    public static boolean existiertATM (String ATMID) {
        String sqlAnfrage = sqlExistiertATM;
        sqlAnfrage = replaceFirst(sqlAnfrage, ATMID);
        return DBHelper.existiert(sqlAnfrage);
    }

    public static ResultSet getAllATMID () {
        return DBHelper.sqlGetResultSet(sqlAlleATM);
    }
}