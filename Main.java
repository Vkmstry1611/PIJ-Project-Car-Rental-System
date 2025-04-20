
public class Main {
    public static void main(String[] args) {
        try {
            CarRentalSystem system = new CarRentalSystem();
            system.showMenu();
        } catch (Exception e) {
            System.out.println("System error: " + e.getMessage());
        }
    }
}