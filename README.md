# CollabDB – JDBC-Based Team & Project Management System

## 📌 Overview
CollabDB is a **menu-driven JDBC application** built in Java with MySQL as the backend.  
It enables efficient management of **members, teams, and projects**, ensuring seamless integration and transaction safety.  
The system provides CRUD operations along with advanced features such as assigning projects to teams, dynamic team size updates, and filtered queries (e.g., view members by city).

---

## ✨ Features
- ✅ Add, remove, and update **Members**, **Teams**, and **Projects**  
- ✅ **Associate projects** with teams and manage assignments dynamically  
- ✅ **Transaction management** using JDBC with auto-commit disabled for consistency  
- ✅ **Custom queries**: view members by city, list projects with teams, filter teams by size  
- ✅ Robust **exception handling** for reliable database interactions  

---

## 🛠️ Tech Stack
- **Java** (JDBC API)  
- **MySQL** (Relational Database)  
- **SQL** for schema design & queries  

---

## 🚀 How to Run
1. Install **MySQL** and create a database named `jdbc`.  
2. Import SQL scripts from the `sql/` folder.  
3. Update database credentials in `imt2022122_jdbc.java`.  
4. Compile and run the program:  
   ```bash
   javac src/imt2022122_jdbc.java -d bin
   java -cp bin:lib/* imt2022122_jdbc
