import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        DietPlanDAO dietDAO = new DietPlanDAO();

        System.out.println("1. Register\n2. Login");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            User user = new User();
            System.out.print("Username: "); user.setUsername(sc.nextLine());
            System.out.print("Password: "); user.setPassword(sc.nextLine());
            System.out.print("Age: "); user.setAge(sc.nextInt());
            System.out.print("Weight: "); user.setWeight(sc.nextDouble());
            sc.nextLine();
            System.out.print("Goal (weight_loss/muscle_gain/maintenance): "); user.setGoal(sc.nextLine());
            System.out.print("Any health conditions (e.g., diabetes, heart issues): "); user.setHealthConditions(sc.nextLine());
            if (userDAO.register(user)) {
                System.out.println("Registration successful!");
            }
        } else if (choice == 2) {
            System.out.print("Username: "); String username = sc.nextLine();
            System.out.print("Password: "); String password = sc.nextLine();
            if (userDAO.login(username, password)) {
                System.out.println("Login successful!");
                User user = userDAO.getUserByUsername(username);
                System.out.println("\n--- AI Health Diagnosis ---");
                if (user.getHealthConditions() != null && !user.getHealthConditions().isEmpty()) {
                    System.out.println("Detected health issues: " + user.getHealthConditions());
                } else {
                    System.out.println("No major health issues detected.");
                }
                System.out.println("\nRecommended Diet Plan:");
                dietDAO.showPlan(user.getGoal(), user.getHealthConditions());
            } else {
                System.out.println("Invalid credentials!");
            }
        }
    }
}
