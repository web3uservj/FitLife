![Screenshot (16)](https://github.com/user-attachments/assets/18761a76-092e-46de-b926-8de2723b1516)

#  Gym Management System - Java MVC

A robust, modular Gym Management System developed using Java following the **Model-View-Controller (MVC)** architectural pattern. This system supports multiple user roles including administrators, trainers, and members, with full separation of concerns across the data, logic, and presentation layers.

---

## Project Architecture: MVC Pattern

src/                                                                                                                                                               
└── com/                                                                                                                                                           
├── model/ # Data entities (Member, Trainer, Plan, etc.)                                                                                                           
├── dao/ # DAO classes for database operations                                                                                                                     
├── service/ # Business logic (controller layer)                                                                                                                   
├── ui/ # User Interface components                                                                                                                                
└── util/ # Utility/helper classes                                                                                                                                 


- **Model:** Represents the core data structures like `Member`, `Trainer`, `Package`, `Schedule`, and `Billing`.
- **View:** Built using Java Swing or Console UI (`MainUI.java`) for user interaction.
- **Controller:** Service classes such as `MemberService`, `TrainerService` contain application logic and handle user actions.

---

##  Features

###  Admin Module
- Add, update, view, or remove **members** and **trainers**
- Manage **plans**, **packages**, and **billing**
- View and organize **session schedules**

###  Trainer Module
- Manage personal profile
- View assigned members and schedules
- Indicate availability

###  Member Module
- Register and manage user profile
- Book sessions with trainers
- Subscribe to gym packages
- View and pay billing history

---

##  Database Schema

The system connects to a **MySQL** database with tables for:

- `members`
- `trainers`
- `plans`
- `packages`
- `schedules`
- `billings`

Ensure your MySQL database is configured as per the schema requirements. Update DB credentials in DAO classes accordingly.

---

##  Dependencies

- Java SE 11 or higher
- MySQL Connector/J (JDBC driver)
- Java Time API (`java.time.LocalDate`, `LocalTime`, etc.)

---

##  Installation & Running the App

### 1. Clone the Repository
```bash
git clone https://github.com/web3uservj/FitLife.git
cd FitLife
```
### 2. Configure MySQL Database
```bash
Create the necessary tables using your preferred tool (MySQL Workbench, CLI, etc.)

Update database connection details in com/dao classes.
```

### 3. Compile and Run
```bash
javac com/ui/MainUI.java
java com.ui.MainUI
```
### Usage Examples
```bash
Admin Login
=== Main Menu ===
1. Admin Login
2. User Login
3. User Registration
4. Trainer Login
Enter your choice: 1

Admin Username: admin  
Admin Password: 12345

Member Registration
Enter details (username:name:contact:email:address:password): 
john_doe:John Doe:555-1234:john@example.com:123 Main St:password123

Session Booking
Enter Trainer ID: 5  
Enter Session Date (yyyy-MM-dd): 2023-12-15  
Enter Session Time (HH:mm): 14:30  
Enter Duration in minutes: 60  
```
### MVC Flow Example
```bash
View: MainUI displays menu and accepts user input

Controller: MemberService processes the input and performs logic

Model: MemberDAO persists data to MySQL

View: Displays confirmation or error back to user
```
### Best Practices Followed
```bash
Clean separation of concerns using MVC

DAO Pattern for modular database interaction

Service Layer abstraction for business rules

Exception handling and input validation

Consistent and clear naming conventions

Easily extendable and maintainable structure
```
### License
```bash
This project is licensed under the MIT License.
```
### Author
```bash
Vijay M
Java Developer | Web3 Enthusiast | Software Developer
Feel free to connect or raise issues for improvements or contributions!
```
