# 📚 E-Store Book Management System
<img width="1365" height="677" alt="Screenshot 2025-10-08 172452" src="https://github.com/user-attachments/assets/ee29dcb3-20f8-4a15-80e2-5b97f31d89f0" />
<img width="1365" height="688" alt="Screenshot 2025-10-08 172508" src="https://github.com/user-attachments/assets/07ab3f80-0a36-4ff1-bf33-6857f9d7ec21" />
<img width="1303" height="681" alt="Screenshot 2025-10-08 172548" src="https://github.com/user-attachments/assets/f2a1367b-ce9c-483a-99b6-848f6ad4f641" />

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


## ⚡ New Features Added

### 🔹 Caching Enabled
> 🧠 **Purpose:** Improve performance by reducing repetitive database calls.  
> ✅ Added `@EnableCaching` in the main class.  
> ⚙️ Used `@Cacheable("getallusersList")` on the service method to cache user data for faster retrieval.

### 🔹 Get All Users API
> 🌐 **Service Method:** `getallUser()` added in `UserRegisterService` and implemented in the service class.  
> 📡 **Endpoint:** `/getallusers`  
> 📋 **Description:** Returns a list of all registered users.  
> ⚡ Uses Spring Cache to improve response time for repeated requests.

---

## 🛠️ Database Tables

| Table Name   | Description                                   |
|---------------|-----------------------------------------------|
| 🧍‍♂️ **register**   | Stores user details                        |
| 🗂️ **fileimages**  | Stores file metadata (name, type, BLOB data) |



