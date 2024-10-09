# Restaurant Management System

## Overview
This **Restaurant Management System** is a comprehensive application designed to streamline restaurant operations such as table reservations, customer order management, and menu handling. Built with **Spring Boot**, the system leverages role-based access control to manage different user types (customers, waiters, chefs, and admins). It provides a robust backend for restaurant management while supporting a clear structure for frontend integration.

The project is currently being enhanced with **JUnit** for unit testing to ensure code quality and functionality.

## Key Features
- **Menu Management**:
  - Create and manage menu items with categories.
  - View ingredients for each menu item.
  - Customers can customize orders by excluding specific ingredients.

- **Table Reservation System**:
  - Make, update, or cancel reservations.
  - Validate reservation time windows (e.g., only allow bookings between 2 hours to 30 days in advance).
  - Ensure table capacity matches the number of guests.

- **Customer Orders**:
  - Add items to the cart and convert them to an order at checkout.
  - Track order status through different stages (e.g., pending, confirmed, delivered).

- **Role-Based Access Control**:
  - Separate roles for customers, waiters, chefs, and administrators.
  - Admins can manage reservations, orders, and user roles.

- **Order and Cart Management**:
  - Link items in the cart to create orders at checkout.
  - Handle item quantities, pricing, and total amounts automatically.

- **JUnit Testing**:
  - Ongoing implementation of **JUnit** for unit testing to ensure code quality and functionality.

## Table of Contents
1. [Technologies Used](#technologies-used)
2. [Project Setup](#project-setup)
3. [API Endpoints](#api-endpoints)
4. [Entities and Relationships](#entities-and-relationships)
5. [Testing](#testing)
6. [Future Improvements](#future-improvements)

## Technologies Used
- **Spring Boot** - Backend framework
- **Hibernate / JPA** - ORM for database handling
- **MySQL** - Database
- **JUnit 5** - Unit testing
- **Spring Security** - Role-based access control
- **Maven** - Dependency management
- **Jackson** - JSON handling
- **Lombok** - Reduces boilerplate code for Java entities

## Project Setup

### Prerequisites
- Java 17+
- Maven
- MySQL (or other supported databases)
- Postman (for testing the API)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/username/restaurant-management-system.git
   cd restaurant-management-system
