# Car Rental System

A Java application that simulates a car rental service using JDBC and MySQL. This system includes options to rent, return, and view cars, along with maintaining customer and rental records in a relational database. Built with modularity, OOP principles, and custom exceptions.

##Table of Contents

![image](https://github.com/user-attachments/assets/0ac79ec1-3c0a-49ff-a3c7-cd789d02e7d3)

##  Features

- View available cars
- Rent a car with customer validation
- View ongoing rentals
- Return a car and calculate rental cost
- Persistent storage using MySQL
- Clean modular design with OOP concepts
- Custom exceptions for business logic

Project Structure

![image](https://github.com/user-attachments/assets/6487431a-5ba7-4a15-8c90-58ce630d01fb)

## Class & Exception Descriptions

### Main Classes
- **Main.java**: Entry point of the application; manages CLI menu.
- **CarRentalSystem.java**: Core logic for renting, returning, and listing cars.
- **DBConnection.java**: Establishes and handles MySQL DB connection.
- **Car.java**: Represents car entity and its attributes.
- **Customer.java**: Stores customer data and validations.
- **Rental.java**: Manages rental details including dates and relationships.

### Custom Exceptions
- **CarNotAvailableException**: Thrown when a selected car is not available.
- **CustomerNotFoundException**: Thrown when return process can't find the customer.


##  Database Schema

```SQL
CREATE DATABASE carrentalsystem;
USE carrentalsystem;

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

INSERT INTO cars (car_name, daily_rate, seating_capacity, fuel_type, transmission) VALUES
('Toyota Camry', 2500.00, 5, 'Petrol', 'Automatic'),
('Honda Civic', 2200.00, 5, 'Petrol', 'Manual'),
('Tesla Model 3', 3500.00, 5, 'Electric', 'Automatic'),
('Mahindra Thar', 3000.00, 4, 'Diesel', 'Manual'),
('Maruti Suzuki Swift', 1800.00, 5, 'Petrol', 'Manual'),
('Hyundai Creta', 2800.00, 5, 'Diesel', 'Automatic'),
('Kia Seltos', 2700.00, 5, 'Petrol', 'Automatic'),
('Tata Nexon EV', 3200.00, 5, 'Electric', 'Automatic');
```

## Usage Instructions

### View Available Cars
- Lists all available cars with their details.

### Rent a Car
- Input: car_id, customer details (name, contact, license, age)
- Age must be ≥ 18
- Adds customer, marks car as unavailable, stores rental info

### View Rented Cars
- Displays car name, rental date, and customer

### Return Car
- Input: car_id, customer name
- Calculates rental cost
- Marks car as available
- Updates return date

### Exit
- Exits the CLI menu


## Cost Calculation
```
Total Cost = Daily Rate × Number of Days
Number of Days = return_date - rental_date
```

## Exception Handling

- Invalid age throws error.
- CarNotAvailableException thrown for unavailable car.
- CustomerNotFoundException thrown for invalid return.

## How to Run

- Add MySQL JDBC driver (mysql-connector-java) to your project dependencies (e.g., IntelliJ).

### Compile:
```
javac *.java
```
### Run:
```
java Main
```

## Authors

### Daniel John Jacob

PRN: 23070126027

Batch: AIML A2

### Shrey Ardeshana

PRN: 23070126019

Batch: AIML A1

### Vidish Mistry

PRN: 23070126146

Batch: AIML B3
