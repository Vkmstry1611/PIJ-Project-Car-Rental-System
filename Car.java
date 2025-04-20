public class Car {
    private int id;
    private String name;
    private double dailyRate;
    private int seats;
    private String fuel;
    private String transmission;
    private boolean available;

    public Car(int id, String name, double rate, int seats, String fuel, String transmission, boolean available) {
        this.id = id;
        this.name = name;
        this.dailyRate = rate;
        this.seats = seats;
        this.fuel = fuel;
        this.transmission = transmission;
        this.available = available;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getDailyRate() { return dailyRate; }
    public int getSeats() { return seats; }
    public String getFuel() { return fuel; }
    public String getTransmission() { return transmission; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
