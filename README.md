# 🏢 Employee Management System (EMS) Portal

A Java-based web application built using **Spring Boot MVC** with **JSP frontend** and **PostgreSQL** database, designed for managing employee data efficiently. This project supports basic CRUD operations and follows a clean MVC layered architecture.

---

## 🔧 Tech Stack

- **Backend:** Java, Spring Boot, Spring MVC  
- **Frontend:** JSP, HTML, CSS  
- **Database:** PostgreSQL  
- **Build Tool:** Maven  

---

## ✨ Features

- ➕ Add new employees  
- 🔄 Update existing employee records  
- ❌ Delete employee entries  
- 📄 View all employees  
- ✅ PostgreSQL integration with Hibernate  
- 📁 Layered architecture using Controller, Service, Repository  
- 📱 Simple, responsive JSP-based UI  

---

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/mukteswarAI/employee-management
cd employee-management

### 2. Set Up PostgreSQL Database

- Create a PostgreSQL database named `ems_db`.
- Update the following in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ems_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

### 3. Build and Run the App

Run the following command in the terminal:

### 3. Build and Run the App

Run the following command in the terminal:

```bash
mvn spring-boot:run
```

Once the app is running, open your browser and go to:

```
http://localhost:8080
```

---

## 📁 Project Structure

```
src/
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
├── util/
└── resources/
```

---

## 📌 Future Enhancements

- 🔒 Authentication and authorization using Spring Security  
- 📊 Dashboard & analytics  
- 📁 File upload for employee documents  
- 🧾 Grievance handling module  
- ✅ Unit and integration testing
