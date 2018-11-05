package Datenbank;

import static Datenbank.DBHelper.replaceFirst;

public class DBBank {
    private static final String sqlExistiertBank = "SELECT BANK_ID FROM BANKEN WHERE BANK_ID = \"(?)\";";

    public static boolean existiertBank(String bankID) {
        String sqlAnfrage = sqlExistiertBank;
        sqlAnfrage = replaceFirst(sqlAnfrage, bankID);
        return DBHelper.existiert(sqlAnfrage, "banken");
    }
}