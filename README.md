
# Student Management System

A Java Console-based Student Management System built using JDBC and MySQL. This project demonstrates CRUD operations, admin authentication, soft delete, restore functionality, filtering, and secure database handling using environment variables.

## 🚀 Features

### 👨‍💼 Admin Features

* Admin Login (Database-based authentication)
* Add Student
* Update Student
* Soft Delete Student
* Restore Deleted Student
* View Deleted Students

### 👨‍🎓 Student / General Features

* View Active Students
* Search Student by ID
* Filter Students by Course
* Filter Students by Marks Range

## 🛠️ Tech Stack

* **Language:** Java (JDK 21)
* **Database:** MySQL 8+
* **Connectivity:** JDBC
* **IDE:** IntelliJ IDEA
* **Version Control:** Git & GitHub

## 📁 Project Structure

```text
StudentManagementSystem/
│
├── src/
│   ├── model/
│   │   └── Student.java
│   ├── service/
│   │   ├── AdminService.java
│   │   └── StudentService.java
│   ├── util/
│   │   └── DBConnection.java
│   └── Main.java
│
├── .gitignore
└── README.md
```

## 🗄️ Database Schema

### Create Database

```sql
CREATE DATABASE studentdb;
USE studentdb;
```

### Admin Table

```sql
CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50)
);
```

### Student Table

```sql
CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    course VARCHAR(100),
    marks DOUBLE,
    is_active BOOLEAN DEFAULT TRUE
);
```

## 🔐 Environment Variables Setup

For security reasons, database credentials are not hardcoded.

Set the following environment variables before running the project:

```env
DB_USER=root
DB_PASSWORD=your_mysql_password
```

### On Windows (PowerShell)

```powershell
setx DB_USER root
setx DB_PASSWORD your_mysql_password
```

After setting environment variables, restart IntelliJ IDEA.

## ▶️ How to Run the Project

1. Clone the repository:

```bash
git clone https://github.com/VirajKotawadekar/Student_ManagementSystem.git
```

2. Open the project in IntelliJ IDEA.

3. Ensure:

   * MySQL Server is running
   * `studentdb` database exists
   * Environment variables are set

4. Run:

```text
Main.java
```

The console-based menu will appear.

## 🔑 Sample Admin Credentials

**Username:** admin
**Password:** admin123

## 🧠 Key Concepts Demonstrated

* JDBC connectivity with MySQL
* PreparedStatement usage
* Environment variable based configuration
* Soft delete using boolean flag
* Restore functionality
* Separation of concerns (model / service / util)
* Console-based menu-driven application
* Git & GitHub workflow

## 🚀 Future Enhancements

* Password hashing (BCrypt)
* Role-based access control
* Pagination for large datasets
* Spring Boot REST API version
* Frontend integration (Web / JavaFX)

## 👤 Author

**VIRAJ RAJARAM KOTAWADEKAR**
Diploma in Computer Engineering

📧 Email: [virajkotawadekar@gmail.com](mailto:virajkotawadekar@gmail.com)
🔗 GitHub: https://github.com/VirajKotawadekar

**Java | JDBC | MySQL | Backend Development**

## 📜 License

This project is created for academic and learning purposes only. Feel free to use the code for learning and practice.
