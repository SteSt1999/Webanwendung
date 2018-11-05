package Datenbank;

import Servlet.MainServlet;

import static Datenbank.DBHelper.replaceFirst;

public class DBATM {
    private static final String sqlExistiertATM = "SELECT * FROM ATM WHERE ATM_ID = \"(?)\";";

    public static boolean existiertATM(String atmID) {
        String sqlAnfrage = sqlExistiertATM;
        sqlAnfrage = replaceFirst(sqlAnfrage, atmID);
        return DBHelper.existiert(sqlAnfrage, MainServlet.getBank().getBankID());
    }
}