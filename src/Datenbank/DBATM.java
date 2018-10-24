package Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static Datenbank.DBHelper.replaceFirst;

public class DBATM {
    final private static String sqlExistiertATM = "SELECT * FROM ATM WHERE ATM_ID = \"(?)\";";
    final private static String sqlAlleATM = "SELECT ATM_ID FROM ATM;";

    public static boolean existiertATM(String atmID) {
        String sqlAnfrage = sqlExistiertATM;
        sqlAnfrage = replaceFirst(sqlAnfrage, atmID);
        return DBHelper.existiert(sqlAnfrage);
    }

    public static ArrayList<String> getAllATMID() {
        ResultSet resultSet = DBHelper.sqlGetResultSet(sqlAlleATM);
        ArrayList<String> ATMIDs = new ArrayList<>();
        try {
            while (resultSet.next()) {
                ATMIDs.add(resultSet.getString("ATM_ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ATMIDs;
    }
}