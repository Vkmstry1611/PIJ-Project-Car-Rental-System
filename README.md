# Car Rental System

This Java-based Car Rental System uses JDBC to interact with a MySQL database. The project allows users to view, rent, and return cars, and store customer and rental details.

---

## Features

1. **View Available Cars**  
2. **Rent a Car** (with validation for age)  
3. **View Rented Cars**  
4. **Return a Car**  
5. **Exit**

---

## Tech Stack

- Java  
- JDBC  
- MySQL  

---

## Database Schema

Make sure to use the `carrentalsystem` database and run the following SQL queries:

```sql
CREATE TABLE cars (
    car_id INT PRIMARY KEY AUTO_INCREMENT,
    car_name VARCHAR(50) NOT NULL,
    daily_rate DECIMAL(10,2) NOT NULL,
    seating_capacity INT NOT NULL,
    fuel_type ENUM('Petrol', 'Diesel', 'Electric') NOT NULL,
    transmission ENUM('Manual', 'Automatic') NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE customers (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    contact_number VARCHAR(15) NOT NULL,
    driving_license VARCHAR(20) UNIQUE NOT NULL,
    age INT NOT NULL
);

CREATE TABLE rentals (
    rental_id INT PRIMARY KEY AUTO_INCREMENT,
    car_id INT NOT NULL,
    customer_id INT NOT NULL,
    rental_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (car_id) REFERENCES cars(car_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);


# File Descriptions

## 1. `Main.java`
- **Main entry point**
- Creates instance of `CarRentalSystem` and invokes `showMenu()`.

## 2. `CarRentalSystem.java`
Core logic of the system. Contains:
- `showMenu()` – Displays main menu and handles user input.
- `displayAvailableCars()` – Lists all cars with `available = true`.
- `rentCar()` – Takes user input, validates age, inserts into `customers` and `rentals`, sets car to unavailable.
- `viewRentedCars()` – Lists all ongoing rentals with car and rental info.
- `returnCar()` – Marks car as returned by updating `return_date` and making car available again. Calculates total cost based on rental period.
- `calculateDaysRented()` – Helper function to compute number of days between `rental_date` and return date.
- `calculateTotalCost()` – Computes total cost using `daily_rate * days`.

## 3. `DBConnection.java`
- Establishes and returns MySQL connection using JDBC.
- Modify DB credentials here as needed.

## 4. `CarNotAvailableException.java`
- Custom exception thrown when trying to rent a car that’s already rented.

## 5. `CustomerNotFoundException.java`
- Custom exception thrown when attempting to return a car using a non-existent customer.


---

**Authors**

--Vidish Mistry (23070126146)

--Daniel John Jacob (23070126027)

--Shrey Ardeshana (23070126019)