import java.sql.*;
public class UserDAO {
    public boolean register(User user) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "INSERT INTO users (username, password, age, weight, goal, health_conditions) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setInt(3, user.getAge());
        ps.setDouble(4, user.getWeight());
        ps.setString(5, user.getGoal());
        ps.setString(6, user.getHealthConditions());
        return ps.executeUpdate() > 0;
    }

    public boolean login(String username, String password) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public User getUserByUsername(String username) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM users WHERE username=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setAge(rs.getInt("age"));
            user.setWeight(rs.getDouble("weight"));
            user.setGoal(rs.getString("goal"));
            user.setHealthConditions(rs.getString("health_conditions"));
            return user;
        }
        return null;
    }
}
