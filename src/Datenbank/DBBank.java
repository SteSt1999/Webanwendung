package Datenbank;

import Servlet.MainServlet;

import java.sql.*;

import static Datenbank.DBHelper.replaceFirst;

public class DBBank {
    private static final String sqlExistiertBank = "SELECT BANK_ID FROM BANKEN WHERE BANK_ID = ?;";

    public static boolean existiertBank(final String bankID) {

        ResultSet resultSet = null;

        try {
            Class.forName(DBHelper.getDriver());
            Connection conn = DriverManager.getConnection(DBHelper.getUrl("banken"), DBHelper.getUser(), DBHelper.getPassword());
            PreparedStatement preparedSQL = conn.prepareStatement(sqlExistiertBank);
            preparedSQL.setString(1, bankID);
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