import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class CarRentalSystem {
    private Connection conn;

    public CarRentalSystem() throws SQLException {
        conn = DBConnection.getConnection();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nCar Rental System Menu:");
            System.out.println("1. View Available Cars");
            System.out.println("2. Rent a Car");
            System.out.println("3. View Rented Cars");
            System.out.println("4. Return a Car");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> viewAvailableCars();
                    case 2 -> rentCar(scanner);
                    case 3 -> viewRentedCars();
                    case 4 -> returnCar(scanner);
                    case 5 -> {
                        System.out.println("Exiting system...");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void viewAvailableCars() throws SQLException {
        String sql = "SELECT * FROM cars WHERE available = TRUE";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            System.out.printf("%-8s %-15s %-10s %-8s %-10s %-10s\n",
                    "Car ID", "Name", "Price/Day", "Seats", "Fuel", "Transmission");
            while (rs.next()) {
                System.out.printf("%-8d %-15s Rs.%-8.2f %-8d %-10s %-10s\n",
                        rs.getInt("car_id"),
                        rs.getString("car_name"),
                        rs.getDouble("daily_rate"),
                        rs.getInt("seating_capacity"),
                        rs.getString("fuel_type"),
                        rs.getString("transmission"));
            }
        }
    }

    private void rentCar(Scanner scanner) throws SQLException, UnderAgeException {
        System.out.print("Enter full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter driving license number: ");
        String license = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        if (age < 18) throw new UnderAgeException("Customer must be at least 18 years old");

        int customerId;
        String customerSql = "INSERT INTO customers (full_name, contact_number, driving_license, age) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(customerSql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, contact);
            stmt.setString(3, license);
            stmt.setInt(4, age);
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) customerId = keys.getInt(1);
                else throw new SQLException("Failed to create customer");
            }
        }

        System.out.print("Enter Car ID to rent: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        LocalDate today = LocalDate.now();
        System.out.print("Enter return date (yyyy-mm-dd): ");
        LocalDate returnDate = LocalDate.parse(scanner.nextLine());

        String rentSql = "INSERT INTO rentals (car_id, customer_id, rental_date, return_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(rentSql)) {
            stmt.setInt(1, carId);
            stmt.setInt(2, customerId);
            stmt.setDate(3, Date.valueOf(today));
            stmt.setDate(4, Date.valueOf(returnDate));
            stmt.executeUpdate();
        }

        String updateCarSql = "UPDATE cars SET available = FALSE WHERE car_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateCarSql)) {
            stmt.setInt(1, carId);
            stmt.executeUpdate();
        }

        System.out.println("Car rented successfully!");
    }
}
