# 📚 E-Store Book Management System

An **E-commerce Book Store Management System** built using **Spring Boot, Spring Data JPA, Hibernate, and Swagger**.  
This project is currently **in progress** 🚀 and aims to provide a complete platform for user registration, login, book management, and file (image) upload functionality.

---

## ✨ Features
- 🔐 User Registration & Login (with validation)
- 👤 Get User Details by ID
- 📂 Upload Single & Multiple Images (stored as BLOB in DB)
- 🗄️ Database integration with MySQL/Oracle
- 📝 API Documentation using **Swagger/OpenAPI 3**
- 📊 Entity management using **Spring Data JPA**
- ⏱️ Automatic Timestamps with Hibernate annotations

---

## 🛠️ Tech Stack
- **Backend:** Java 17+, Spring Boot, Spring Data JPA, Hibernate
- **Database:** MySQL / Oracle
- **API Docs:** Swagger (springdoc-openapi)
- **Build Tool:** Maven
- **Other:** Lombok, Validation, Multipart File Upload

  ---

## 🔄 Recent Updates

### 🆕 Added Feature: User Registration with Multi-File Upload
- Now users can register along with uploading multiple files (e.g., documents, images).
- Data is stored in `register` table and uploaded files in `fileimages` table.

---

## 📌 New API Endpoint

### User + File Upload API
| Method | Endpoint                   | Description                                |
|--------|----------------------------|--------------------------------------------|
| POST   | `/userregistersuploadmulti`| Register user and upload multiple files     |

**Request Example (Form-Data in Postman):**
- `jsonData` → User details in JSON format
```json
{
  "firstName": "Abhay",
  "lastName": "Rayate",
  "email": "abhay@example.com",
  "password": "12345",
  "contactno": 9876543210
}
🛠️ Database Tables

register → Stores user details

fileimages → Stores file metadata (name, type, BLOB data)

