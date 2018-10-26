package Datenbank;

import static Datenbank.DBHelper.replaceFirst;

public class DBATM {
    final private static String sqlExistiertATM = "SELECT * FROM ATM WHERE ATM_ID = \"(?)\";";

    public static boolean existiertATM(String atmID) {
        String sqlAnfrage = sqlExistiertATM;
        sqlAnfrage = replaceFirst(sqlAnfrage, atmID);
        return DBHelper.existiert(sqlAnfrage);
    }
}