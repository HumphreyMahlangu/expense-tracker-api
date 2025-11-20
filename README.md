# Java Spring Boot Expense Tracker API
This is a REST API for a simple expense tracker, built with **Java** and **Spring Boot**. It allows users to perform all **CRUD**  operations for their expenses.

This project was built to practice and demonstrate backend development skills, including REST API design, database modeling, and Spring Boot.
---
##  Features
* **Create** a new expense
* **Get** a list of all expenses
* **Get** a single expense by its unique ID
* **Update** an existing expense
* **Delete** an expense
---
##  Tech Stack
* **Java 21**
* **Spring Boot** – Core application framework
* **Spring Data JPA** – For database communication
* **MySQL** – Database for persistence
* **Maven** – For dependency and build management
---
## How to Run Locally
### 1. Prerequisites
Make sure you have the following installed:
* **Java 21** (or newer)
* **Apache Maven**
* **MySQL Server**
---
### 2. Clone the Repository
```bash
git clone https://github.com/YourUsername/expense-tracker-api.git
cd expense-tracker-api
```
### 3. Set Up the Database
Log in to your local MySQL server and create a new database:
```sql
CREATE DATABASE expense_tracker;
USE expense_tracker;

CREATE TABLE categories (
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);
```
### 4. Configure the Application
Navigate to src/main/resources/application.properties and update your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
```
### 5. Run the Application
From the project’s root folder, run:
```bash
mvn spring-boot:run
```
The application will start on:
http://localhost:8080

##  Security & Authentication

This API is secured using **JSON Web Tokens (JWT)**.

* **Public Endpoints:** You can access `/api/auth/**` without logging in.
* **Protected Endpoints:** All other endpoints (`/api/expenses/**`, `/api/categories/**`) require a valid JWT.

### How to Authenticate

1.  **Register a User:**
    * `POST /api/auth/register`
    * Body: `{"username": "user1", "password": "password123"}`
2.  **Login:**
    * `POST /api/auth/login`
    * Body: `{"username": "user1", "password": "password123"}`
    * **Response:** `{"token": "eyJhbGci..."}`
3.  **Access Protected Data:**
    * Copy the token string.
    * Add it to the **Authorization** header of your request:
        * **Key:** `Authorization`
        * **Value:** `Bearer eyJhbGci...`

---

## 🧪 API Endpoints

### Auth Endpoints (`/api/auth`)

**POST**`/api/auth/register` | Register a new user account. <br>
**POST** `/api/auth/login` | Login and receive a JWT. 

### Expense Endpoints (Requires Token)

**GET**`/api/expenses` | Get all expenses.<br>
**POST**`/api/expenses` | Create a new expense.<br>
**GET**`/api/expenses/total` | Get total sum of expenses.

## Catergory Endpoints (Usage)
You can use Postman or cURL to test the API.<br>
**POST** /api/categories | Create a new category <br>
**GET** /api/categories | Retrieve all categories <br>
**DELETE** /api/categories/{id} | Delete a category by ID
### Example: Create a Category (POST)
URL:
```bash
POST http://localhost:8080/api/categories
```
Body (JSON):      
```json
{
    "name": "Food"
}
```         



## Expense Endpoints (Usage)
You can use Postman or cURL to test the API.<br>
**POST** /api/expenses | Create a new expense <br>
**GET** /api/expenses | Retrieve all expenses <br>
**GET** /api/expenses/{id} | Retrieve a single expense by ID <br>
**PUT**   /api/expenses/{id} | Update an existing expense <br>
**DELETE** /api/expenses/{id} | Delete an expense by ID
### Example: Create an Expense (POST)
URL:
```bash
POST http://localhost:8080/api/expenses
```
Body (JSON):
```json
{
    "description": "Groceries",
    "amount": 250.75,
    "date": "2025-11-08",
    "categoryId":{
        "id": 1   
    }
}
```
