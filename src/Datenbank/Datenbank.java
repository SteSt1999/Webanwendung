package Datenbank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Datenbank {
    final static private String driver = "com.mysql.cj.jdbc.Driver";
    final static private String userName = "root";
    static private String password = null;

    final static private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static {
        FileReader fr = null;
        try {
            fr = new FileReader("Passwort.txt");
            BufferedReader br = new BufferedReader(fr);
            password = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String connect() {
        StringBuilder sb = new StringBuilder();
        try {
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, userName, password);

            Statement statement = conn.createStatement();
            String queryString = "SELECT * FROM tabelle";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                sb.append("<br>").append(rs.getString(2));
                //System.out.println(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        /*Datenbank db = new Datenbank();
        db.connect();*/
        System.out.println(connect());
    }
}