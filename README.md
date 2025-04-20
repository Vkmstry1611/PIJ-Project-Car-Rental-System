Car Rental System

A Java application that simulates a car rental service using JDBC and MySQL. This system includes options to rent, return, and view cars, along with maintaining customer and rental records in a relational database. Built with modularity, OOP principles, and custom exceptions.

Table of Contents

Features

Project Structure

Class & Exception Descriptions

Database Schema

Usage Instructions

Cost Calculation

Exception Handling

How to Run

Authors

Features

View available cars

Rent a car with customer validation

View rented cars

Return a car and calculate cost

Uses MySQL for persistent storage

Uses custom exceptions and object-oriented design

Project Structure

CarRentalSystem/
├── Main.java                     # Entry point, CLI menu
├── CarRentalSystem.java         # Main logic controller
├── DBConnection.java            # JDBC connection setup
├── Car.java                     # Car model
├── Customer.java                # Customer model
├── Rental.java                  # Rental model
├── CarNotAvailableException.java  # Exception for unavailable cars
├── CustomerNotFoundException.java # Exception for missing customers
├── ViewCustomerDetails.java     # View customer & car rented info

Class & Exception Descriptions

Main Classes

Main.java: Entry point and handles CLI menu.

CarRentalSystem.java: Contains main business logic: view, rent, return, and list rented cars.

DBConnection.java: Sets up MySQL database connection.

Car.java: Encapsulates car details.

Customer.java: Encapsulates customer info.

Rental.java: Holds rental transaction data.

Custom Exceptions

CarNotAvailableException: Raised if selected car is already rented.

CustomerNotFoundException: Raised if the customer is not found during return.

Database Schema

Ensure MySQL is running and the carrentalsystem database is created.

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

Usage Instructions

View Available Cars

Displays car_id, name, rate, fuel type, seating, and transmission.

Rent a Car

Input: car_id, customer name, contact, license, and age.

Validates age (must be ≥ 18).

Adds customer to DB, marks car as unavailable, and stores rental.

View Rented Cars

Displays ongoing rentals with car name, rental date.

Return Car

Input: car_id, customer name.

Calculates cost based on number of days.

Marks car as available and sets return_date.

Exit

Exits the CLI.

Cost Calculation

Total Cost = Daily Rate × Number of Days
Number of Days = Difference between rental_date and return_date

Exception Handling

Invalid age throws error.

CarNotAvailableException thrown for unavailable car.

CustomerNotFoundException thrown for invalid return.

How to Run

Add JDBC driver to IntelliJ dependencies.

Compile:

javac *.java

Run:

java Main

Authors

Daniel John Jacob

PRN: 23070126027

Batch: AIML A2

Shrey Ardeshana

PRN: 23070126019

Batch: AIML A1

Vidish Mistry

PRN: 23070126146

Batch: AIML B3