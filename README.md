# 🎓 EAMCET College Eligibility System

A Java web application built using **Servlets and JDBC** that allows students to register with their academic details, log in securely, and check which engineering colleges they are eligible for based on their EAMCET rank.

---

## 📌 Project Overview

This project simulates a real-world college admission eligibility portal. After a student registers and logs in, the system fetches their EAMCET rank from the database and compares it against a list of colleges to display eligible and non-eligible colleges dynamically.

---

## ✨ Features

- ✅ Student Registration — stores roll number, name, email, place, 10th %, 12th %, EAMCET rank, caste, and password
- ✅ Login Authentication — validates credentials against the database
- ✅ College Eligibility Check — dynamically fetches colleges from a separate DB and checks eligibility based on rank
- ✅ Dual Database Architecture — student data and college data maintained in separate MySQL databases (`school` and `college`)
- ✅ Servlet-based MVC flow — each concern handled by a dedicated servlet
- ✅ Request Dispatching — login servlet chains into the eligibility servlet on successful login

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Web Layer | Java Servlets (javax.servlet) |
| Database | MySQL |
| DB Connectivity | JDBC (PreparedStatement, ResultSet) |
| Server | Apache Tomcat |
| IDE | Eclipse IDE |

---

## 📂 Project Structure

```
src/
└── com/webserver/servlets/
    ├── RegistrationServlet.java      # Handles student registration (POST)
    ├── LoginServlet.java             # Handles login authentication (POST)
    └── CollegeEligibilityServlet.java # Fetches rank and checks college eligibility
```

---

## 🗄️ Database Schema

### Database 1 — `school`

**Table: `studentdetails`**

| Column | Type |
|---|---|
| roll_no | VARCHAR |
| username | VARCHAR |
| email | VARCHAR |
| place | VARCHAR |
| 10th Percentage | INT |
| 12th_percentage | FLOAT |
| password | VARCHAR |
| eamcet_rank | INT |
| caste | VARCHAR |

---

### Database 2 — `college`

**Table: `collegeeligibility`**

| Column | Type |
|---|---|
| college_name | VARCHAR |
| college_location | VARCHAR |
| eligibilty_percentage | FLOAT |
| eapcet_rank | INT |

---

## ⚙️ How It Works

```
Student fills Registration Form
        ↓
RegistrationServlet → inserts data into school.studentdetails
        ↓
Student fills Login Form
        ↓
LoginServlet → validates username + password from DB
        ↓
On success → dispatches request to CollegeEligibilityServlet
        ↓
Fetches student's eamcet_rank from school DB
        ↓
Compares rank against all colleges in college DB
        ↓
Displays eligible ✅ and non-eligible ❌ colleges dynamically
```

---

## 🚀 How to Run Locally

### Prerequisites
- JDK 8 or above
- Apache Tomcat 9+
- MySQL Server
- Eclipse IDE (Dynamic Web Project)
- MySQL Connector JAR added to `/WEB-INF/lib`

### Steps

1. Clone this repository
```bash
git clone https://github.com/PurneshReddy-D/EAMCET-Eligibility-System.git
```

2. Import into Eclipse as a **Dynamic Web Project**

3. Create the databases in MySQL
```sql
CREATE DATABASE school;
CREATE DATABASE college;
```

4. Create the tables using the schema above

5. Update DB credentials in each servlet's `init()` method
```java
String url = "jdbc:mysql://localhost:3306/school";
String un  = "your_username";
String pwd = "your_password";
```

6. Add MySQL Connector JAR to `WEB-INF/lib`

7. Deploy on Apache Tomcat and access via browser
```
http://localhost:8080/<project-name>/
```

---

## 📸 Servlet URL Mappings

| Servlet | URL Pattern | Method |
|---|---|---|
| RegistrationServlet | `/register` | POST |
| LoginServlet | `/login` | POST |
| CollegeEligibilityServlet | `/eligibility` | POST |

---

## 🧠 Key Concepts Demonstrated

- Java Servlet lifecycle — `init()`, `doPost()`, `destroy()`
- JDBC with `PreparedStatement` for SQL injection prevention
- Dual database connectivity within a single application
- `RequestDispatcher` for chaining servlets (`include`)
- Dynamic HTML response generation via `PrintWriter`
- Separation of concerns across multiple servlets

---



## 👨‍💻 Author

**Purnesh Reddy Dubbala**  
Java Backend Developer  
[LinkedIn](https://www.linkedin.com/in/purnesh-reddy-dubbala-642578312) | [GitHub](https://github.com/PurneshReddy-D)
