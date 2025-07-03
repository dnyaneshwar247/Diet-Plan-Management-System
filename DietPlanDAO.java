import java.sql.*;
public class DietPlanDAO {
    public void showPlan(String goal, String healthConditions) throws SQLException {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT meal_time, food_items FROM diet_plans WHERE goal=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, goal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String items = rs.getString("food_items");
            if (healthConditions != null && !healthConditions.isEmpty()) {
                if (healthConditions.toLowerCase().contains("diabetes")) {
                    items = items.replaceAll("(?i)sugar|sweet", "(restricted)");
                }
            }
            System.out.println(rs.getString("meal_time") + ": " + items);
        }
    }
}
