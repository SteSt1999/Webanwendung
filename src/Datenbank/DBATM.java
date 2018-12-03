package Datenbank;

import Logik.Verwaltung.Bank;
import Servlet.MainServlet;

import java.sql.*;

import static Datenbank.DBHelper.replaceFirst;

public class DBATM {
    private static final String sqlExistiertATM = "SELECT * FROM ATM WHERE ATM_ID = ?;";

    public static boolean existiertATM(final String atmID, Bank bank) {
        ResultSet resultSet = null;

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl(bank.getBankID()), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlExistiertATM);
            preparedSQL.setString(1, atmID);
            resultSet = preparedSQL.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        try {
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
