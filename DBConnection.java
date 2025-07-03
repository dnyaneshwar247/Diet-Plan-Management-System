import java.sql.*;
public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/diet_system";
        String user = "root";
        String pass = "your_password";
        return DriverManager.getConnection(url, user, pass);
    }
}
