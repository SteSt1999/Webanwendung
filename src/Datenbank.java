import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Datenbank {
    final private String userName = "root";
    final private String password = "admin";
    final private String driver = "com.mysql.cj.jdbc.Driver";

    final private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public void connect()
    {
        try {
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,userName,password);

            Statement statement = conn.createStatement();
            String queryString = "SELECT * FROM tabelle";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Datenbank db = new Datenbank();
        db.connect();
    }
}