# 📚 E-Store Book Management System
<img width="1365" height="677" alt="Screenshot 2025-10-08 172452" src="https://github.com/user-attachments/assets/ee29dcb3-20f8-4a15-80e2-5b97f31d89f0" />
<img width="1365" height="688" alt="Screenshot 2025-10-08 172508" src="https://github.com/user-attachments/assets/07ab3f80-0a36-4ff1-bf33-6857f9d7ec21" />
<img width="1303" height="681" alt="Screenshot 2025-10-08 172548" src="https://github.com/user-attachments/assets/f2a1367b-ce9c-483a-99b6-848f6ad4f641" />
<img width="1366" height="768" alt="Screenshot (115)" src="https://github.com/user-attachments/assets/90560a03-4c7b-4ff8-b81f-5518b457cdcb" />
<img width="1365" height="589" alt="Screenshot 2025-10-29 124405" src="https://github.com/user-attachments/assets/89f6bd60-91ad-40b8-ba6a-29893bbc8876" />
<img width="1366" height="768" alt="Screenshot (136)" src="https://github.com/user-attachments/assets/f6cd7812-9e4d-46c1-912f-26a1133e53fd" />
<img width="1352" height="246" alt="Screenshot 2025-10-31 211041" src="https://github.com/user-attachments/assets/6b7665a4-4074-490c-b8b5-4664c39cb47b" />

# 📚 E-Store Book Management System  

An **E-commerce Book Store Management System** built using **Spring Boot**, **Spring Data JPA**, **Hibernate**, and **Swagger**.  
This project is currently in progress 🚀 and aims to provide a complete platform for user registration, login, book management, and file (image) upload functionality.

---

## ✨ Features  

- 🔐 **User Registration & Login** (with validation)  
- 👤 **Get User Details by ID**  
- 📂 **Upload Single & Multiple Images** (stored as BLOB in DB)  
- 🗄️ **Database Integration** with MySQL / Oracle  
- 📝 **API Documentation** using Swagger / OpenAPI 3  
- 📊 **Entity Management** using Spring Data JPA  
- ⏱️ **Automatic Timestamps** using Hibernate annotations  

---

## 🛠️ Tech Stack  

| Category | Technology |
|-----------|-------------|
| **Backend** | Java 17+, Spring Boot, Spring Data JPA, Hibernate |
| **Database** | MySQL / Oracle |
| **API Docs** | Swagger (springdoc-openapi) |
| **Build Tool** | Maven |
| **Others** | Lombok, Validation, Multipart File Upload |

---

## 🔄 Recent Updates  

### 🆕 **Added Feature: User Registration with Multi-File Upload**  
Users can now register along with uploading multiple files (e.g., profile pictures, documents).  
Data is stored in the `register` table, and uploaded files in the `fileimages` table.  

#### 📌 **New API Endpoint**

| Method | Endpoint | Description |
|---------|-----------|-------------|
| **POST** | `/userregistersuploadmulti` | Register user and upload multiple files |

**Request Example (Form-Data in Postman):**  
```json
jsonData → {
  "firstName": "Abhay",
  "lastName": "Rayate",
  "email": "abhay@example.com",
  "password": "12345",
  "contactno": 9876543210
}
```

---

## ⚡ New Features Added  

### 🔹 **Caching Enabled**
> 🧠 **Purpose:** Improve performance by reducing repetitive database calls.  
> ✅ Added `@EnableCaching` in the main class.  
> ⚙️ Used `@Cacheable("getallusersList")` on the service method to cache user data for faster retrieval.

---

### 🔹 **Get All Users API**
> 🌐 **Service Method:** `getallUser()` added in `UserRegisterService` and implemented in the service class.  
> 📡 **Endpoint:** `/getallusers`  
> 📋 **Description:** Returns a list of all registered users.  
> ⚡ Uses Spring Cache to improve response time for repeated requests.  
> 🧩 Tested using Postman, Insomnia, Hoppscotch, and Swagger.

---

## 🗃️ Database Tables  

| Table Name | Description |
|-------------|--------------|
| 🧍‍♂️ **register** | Stores user details |
| 🗂️ **fileimages** | Stores file metadata (name, type, BLOB data) |

---

## 🚀 Summary  

Caching successfully implemented in the **Customer Module** to enhance API response speed and reduce database load.  
All APIs verified through multiple testing tools ensuring reliability and consistency.

---
### ⚙️ Environment Profiles

- Added `spring.profiles.active=perf` for environment-specific configurations.
- Helps manage different setups (e.g., dev, test, prod) easily within the same project.


# E-Store Book Management System

An E-commerce Book Store Management System built using **Spring Boot, Spring Data JPA, Hibernate, and Swagger**.
This project is currently in progress 🚀 and aims to provide a complete platform for **user registration, login, book management, and file (image) upload functionality**.

---

## ✨ Features

### User Module

* 🔐 **User Registration & Login** (with validation)
* 👤 **Get User Details by ID**
* 📂 **Upload Single & Multiple Images** (stored as BLOB in DB)
* 🗄️ Database Integration with MySQL / Oracle
* 📝 API Documentation using Swagger / OpenAPI 3
* 📊 Entity Management using Spring Data JPA
* ⏱️ Automatic Timestamps using Hibernate annotations

### Book Module (New)

* 📚 Add New Books to the store
* 📖 Fetch All Books
* 🔍 Fetch Book by ID
* ⏱️ Automatic creation and update timestamps
* ⚡ Caching Enabled for faster book data retrieval using `@Cacheable`

---

## 🛠️ Tech Stack

| Category   | Technology                                              |
| ---------- | ------------------------------------------------------- |
| Backend    | Java 17+, Spring Boot, Spring Data JPA, Hibernate       |
| Database   | MySQL / Oracle                                          |
| API Docs   | Swagger (springdoc-openapi)                             |
| Build Tool | Maven                                                   |
| Others     | Lombok, Validation, Multipart File Upload, Spring Cache |

---

## 🔄 Recent Updates

### User Module

* 🆕 **Feature:** User Registration with Multi-File Upload

  * Users can register along with uploading multiple files (e.g., profile pictures, documents)
  * Data stored in `register` table, files in `fileimages` table
* 📌 **API Endpoint:**

| Method | Endpoint                    | Description                             |
| ------ | --------------------------- | --------------------------------------- |
| POST   | `/userregistersuploadmulti` | Register user and upload multiple files |

**Request Example (Form-Data in Postman):**

```json
jsonData → {
  "firstName": "Abhay",
  "lastName": "Rayate",
  "email": "abhay@example.com",
  "password": "12345",
  "contactno": 9876543210
}
```

* ⚡ **Caching Enabled:**

  * `@EnableCaching` in main class
  * `@Cacheable("getallusersList")` for faster user data retrieval
* 📡 **Get All Users API:** `/getallusers`

  * Returns all registered users
  * Uses Spring Cache for performance improvement

### 📘 v3 Update — Book Management Module (2025)

#### 🔹 Overview

A new **Book Management Module** has been added to the E-Store Book Management System.
This module handles all book-related operations such as **creating**, **retrieving**, and **fetching by ID**, using **Spring Boot, JPA, Hibernate, and Caching**.

#### 🧩 Features Implemented

* ➕ Add New Book — Create and save a new book record with validation.
* 📚 Fetch All Books — Retrieve all available books with caching for optimized performance.
* 🔍 Fetch Book by ID — Retrieve a single book record by its unique ID.
* ⚙️ Global Exception Handling — Custom exception (`BookIdNotFoundException`) for missing IDs.
* 💾 Auto Timestamp — Automatically stores `createdDate` and `updatedDate` using Hibernate annotations.
* 🚀 Integrated Caching — Reduces database load on repeated requests using `@Cacheable`.

#### 🧠 Tech Stack Used

| Layer              | Technology                            |
| ------------------ | ------------------------------------- |
| Controller         | Spring Boot REST Controller           |
| Service Layer      | Spring Service + @Cacheable           |
| Repository         | Spring Data JPA                       |
| Entity             | JPA Entity with Hibernate annotations |
| Database           | MySQL                                 |
| Exception Handling | Custom Runtime Exception              |
| Documentation      | Swagger / OpenAPI 3                   |

#### 🧾 API Endpoints

| Method | Endpoint            | Description                                           |
| ------ | ------------------- | ----------------------------------------------------- |
| POST   | `/savebooks`        | Creates a new book entry with name, title, and author |
| GET    | `/getAllBooks`      | Fetches all books from the database                   |
| GET    | `/getCustBook/{id}` | Fetches a specific book by its ID                     |

**Request Example (POST /savebooks)**

```json
{
  "name": "Spring Boot in Action",
  "title": "Learn Spring Boot",
  "author": "Craig Walls"
}
```

**Response Example (Success)**

```json
{
  "statusCode": 201,
  "status": "SUCCESS",
  "message": "Book has been successfully saved",
  "data": {
    "id": 1,
    "name": "Spring Boot in Action",
    "title": "Learn Spring Boot",
    "author": "Craig Walls",
    "createdDate": "2025-10-08T11:45:22",
    "updatedDate": "2025-10-08T11:45:22"
  }
}
```

#### 🧱 Database Table — books

| Column      | Type          | Description                           |
| ----------- | ------------- | ------------------------------------- |
| id          | Long (PK)     | Auto-generated unique ID              |
| name        | String        | Book name                             |
| title       | String        | Book title                            |
| author      | String        | Book author                           |
| createdDate | LocalDateTime | Automatically generated on creation   |
| updatedDate | LocalDateTime | Automatically updated on modification |

#### ⚙️ Caching

```java
@Cacheable(value = "getAllBooks")
public List<BooksModule> custmergetAllBooks() { ... }

@Cacheable(cacheNames = "booksmodule", key = "#id")
public BooksModule getByCustmerBookid(Long id) { ... }
```

#### 🧩 Exception Handling

```java
public class BookIdNotFoundException extends RuntimeException {
    public BookIdNotFoundException(String msg) {
        super(msg);
    }
}
```

#### ✅ Swagger Integration

```java
@Operation(summary = "Create a New Book Entry", description = "Registers a new book in the online bookstore.")
@ApiResponses({
  @ApiResponse(responseCode = "201", description = "Book saved successfully"),
  @ApiResponse(responseCode = "400", description = "Invalid input"),
  @ApiResponse(responseCode = "500", description = "Internal server error")
})
```

* Swagger UI will show all Book APIs under the **“Book Controller”** tag.

#### 🧰 Summary

* Book module integrated with **JPA, Hibernate, Caching, and Swagger**.
* Improved performance and reliability through caching.
* Exception handling ensures clean and descriptive error responses.
* Ready for production and scalable integration with other modules like Customer or Order.

---# E-Store Book Management System

An E-commerce Book Store Management System built using **Spring Boot, Spring Data JPA, Hibernate, and Swagger**.
This project is currently in progress 🚀 and aims to provide a complete platform for **user registration, login, book management, and file (image) upload functionality**.

---

## ✨ Features

### User Module

* 🔐 **User Registration & Login** (with validation)
* 👤 **Get User Details by ID**
* 📂 **Upload Single & Multiple Images** (stored as BLOB in DB)
* 🗄️ **Database Integration** with MySQL / Oracle
* 📝 **API Documentation** using Swagger / OpenAPI 3
* 📊 **Entity Management** using Spring Data JPA
* ⏱️ **Automatic Timestamps** using Hibernate annotations

### Book Module

* 📚 **Add New Books** to the store
* 📖 **Fetch All Books**
* 🔍 **Fetch Book by ID**
* ⏱️ Automatic creation and update timestamps
* ⚡ **Caching Enabled** for faster book data retrieval using `@Cacheable`

### Excel Upload Module

* 📄 **Upload Excel File** containing book details
* 🔹 Stores data from Excel into `BooksExcelFile` table
* ⏱️ Automatic creation and update timestamps

---

## 🛠️ Tech Stack

| Category   | Technology                                              |
| ---------- | ------------------------------------------------------- |
| Backend    | Java 17+, Spring Boot, Spring Data JPA, Hibernate       |
| Database   | MySQL / Oracle                                          |
| API Docs   | Swagger (springdoc-openapi)                             |
| Build Tool | Maven                                                   |
| Others     | Lombok, Validation, Multipart File Upload, Spring Cache |

---

## 🔄 Recent Updates

### User Module

* 🆕 **Feature:** User Registration with Multi-File Upload

  * Users can register along with uploading multiple files (e.g., profile pictures, documents)
  * Data stored in `register` table, files in `fileimages` table

**API Endpoint:**

| Method | Endpoint                    | Description                             |
| ------ | --------------------------- | --------------------------------------- |
| POST   | `/userregistersuploadmulti` | Register user and upload multiple files |

---

### Book Module

**API Endpoints:**

| Method | Endpoint            | Description                                           |
| ------ | ------------------- | ----------------------------------------------------- |
| POST   | `/savebooks`        | Creates a new book entry with name, title, and author |
| GET    | `/getAllBooks`      | Fetches all books from the database                   |
| GET    | `/getCustBook/{id}` | Fetches a specific book by its ID                     |

**Request Example (POST /savebooks)**

```json
{
  "name": "Spring Boot in Action",
  "title": "Learn Spring Boot",
  "author": "Craig Walls"
}
```

**Response Example (Success)**

```json
{
  "statusCode": 201,
  "status": "SUCCESS",
  "message": "Book has been successfully saved",
  "data": {
    "id": 1,
    "name": "Spring Boot in Action",
    "title": "Learn Spring Boot",
    "author": "Craig Walls",
    "createdDate": "2025-10-08T11:45:22",
    "updatedDate": "2025-10-08T11:45:22"
  }
}
```

---

### Excel Upload Module

**API Endpoint:**

| Method | Endpoint           | Description                               |
| ------ | ------------------ | ----------------------------------------- |
| POST   | `/uploadExcelFile` | Upload Excel file containing book details |

**Request Example (POST /uploadExcelFile)**

```bash
curl -X POST "http://localhost:8080/uploadExcelFile" -F "file=@C:/path-to-file/books_data.xlsx"
```

**Successful Response**

```json
{
  "statusCode": 200,
  "status": "SUCCESS",
  "message": "Excelfile save successfully"
}
```

**Failure Response**

```json
{
  "statusCode": 400,
  "status": "FAILURE",
  "message": "Excelfile save failed"
}
```

💡 **Usage Notes:**

* Ensure your Excel file is **.xlsx format**.
* Invalid files will return **400 Bad Request**.
* Uploaded data will be stored in `BooksExcel

# 📘 E-Store Book Management System — MongoDB Integration

## 🧩 Overview
This module introduces **MongoDB integration** into the existing **E-Store Book Management System**.  
It enables handling of document-based data (like books or user records) alongside relational data, providing a **multi-database architecture** using **Spring Boot**.

---

## ⚙️ Configuration

### 1️⃣ Add Dependency (pom.xml)
```xml
<!-- MongoDB Driver -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

---

### 2️⃣ Update `application.properties`
```properties
# MongoDB Configuration
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=book_store_mongo

# MySQL (Existing)
spring.datasource.url=jdbc:mysql://localhost:3306/book_store
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

---

## 🗄️ MongoDB Entity Example
```java
package in.abhayit.Entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user_registers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksModuleMongo {

    @Id
    private String id;
    private String name;
    private String title;
    private String author;
}
```

---

## 🧠 Repository Layer
```java
package in.abhayit.Repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import in.abhayit.Entity.mongo.BooksModuleMongo;

public interface BooksModuleMongoRepository extends MongoRepository<BooksModuleMongo, String> {
}
```

---

## 🚀 API Endpoints

| Method | Endpoint              | Description |
|--------|-----------------------|--------------|
| POST   | `/mongo/saveBook`     | Save a new book document in MongoDB |
| GET    | `/mongo/getAllBooks`  | Retrieve all MongoDB books |
| GET    | `/mongo/getBook/{id}` | Fetch a specific MongoDB book by ID |

---

## 📦 Features
- Dual database support (**MySQL + MongoDB**)
- Independent repositories for relational and document data
- Spring Boot auto-configuration for both databases
- Lightweight and scalable for NoSQL data

---

## 🧪 Testing Options
- **Postman** for API testing  
- **MongoDB Compass** for database verification  
- **Swagger UI** at `/swagger-ui.html` for API docs

---

## 🧾 Example MongoDB Response

### Request (POST /mongo/saveBook)
```json
{
  "name": "Java Made Easy",
  "title": "Spring Boot Guide",
  "author": "Abhay Rayate"
}
```

### Response
```json
{
  "statusCode": 200,
  "status": "SUCCESS",
  "message": "Book saved successfully in MongoDB!"
}
```

---

## ✅ Summary
MongoDB has been successfully integrated into the **E-Store Book Management System**.  
This multi-database architecture combines the strengths of **SQL (MySQL)** and **NoSQL (MongoDB)**, ensuring flexibility, scalability, and performance for modern application needs.


--------
# Cart Module - E-Commerce Book Store

This module is part of the E-Commerce Book Store web application.  
It handles **Cart Management**, allowing users to **add, update, and delete items** from their cart.  
The **total price** is automatically calculated as `quantity × book price`.


## 📦 Entities

### CartModule
| Field         | Type             | Description                                   |
|---------------|-----------------|-----------------------------------------------|
| id            | Long            | Primary key, auto-generated                   |
| customer      | Customer        | Many-to-One relationship with Customer       |
| booksModule   | BooksModule     | Many-to-One relationship with BooksModule    |
| quantity      | int             | Number of books in the cart                   |
| totalPrice    | double          | Automatically calculated: `quantity × book price` |
| createdDate   | LocalDateTime   | When the cart item was created                |
| updatedDate   | LocalDateTime   | When the cart item was last updated           |



---

## ⚡ Features / APIs

### 1. Add Item to Cart
- **Endpoint:** `POST /addcart/add`  
- **Description:** Adds a book to customer's cart or updates quantity if already exists.  
- **Request Parameters:**
  - `customerId` (Long, required)  
  - `bookId` (Long, required)  
  - `quantity` (int, required)  
- **Responses:**
  - `201` → Cart item added successfully  
  - `400` → Failed to add cart item  
  - `500` → Internal server error  

---

### 2. Update Item in Cart
- **Endpoint:** `PUT /addcart/update/{id}`  
- **Description:** Updates an existing cart item by ID. You can change **quantity, book, or customer**.  
- **Request Body:** JSON of `CartModule`  
- **Responses:**
  - `200` → Cart updated successfully  
  - `404` → Cart not found  
  - `500` → Internal server error  

**Example Request Body:**
```json
{
  "quantity": 3,
  "booksModule": { "id": 5 },
  "customer": { "id": 1 }
}
```

---

### 3. Delete Item from Cart
- **Endpoint:** `DELETE /addcart/delete/{id}`  
- **Description:** Deletes a cart item by ID.  
- **Responses:**
  - `200` → Cart deleted successfully  
  - `500` → Internal server error  

---

## 📌 Repository Layer
- **Interface:** `CartModuleRepo` extends `JpaRepository<CartModule, Long>`  
- **Custom Method:**  
```java
CartModule findByCustomerAndBooksModule(Customer customer, BooksModule booksModule);
```
Prevents duplicate cart entries for the same book and customer.

---

## ⚙ Service Layer
- **Interface:** `CartModuleService`
    - `addToCart(Long customerId, Long bookId, int quantity)`  
    - `updateCart(Long id, CartModule updatedCart)`  
    - `deleteToCart(Long id)`  

- **Implementation:** `CartModuleServiceImpl`  
    - Checks if customer and book exist  
    - Updates quantity if cart item already exists  
    - Calculates total price automatically  
    - Handles add, update, delete operations

---

## 📌 Controller Layer
- **Controller:** `CartModuleController`  
- **Swagger Annotations** for documentation  
- Endpoints:
    - `POST /addcart/add` → Add item  
    - `PUT /addcart/update/{id}` → Update item  
    - `DELETE /addcart/delete/{id}` → Delete item  

---

## 📖 Swagger UI
- **URL:** `http://localhost:8080/swagger-ui.html`  
- Test all Cart APIs directly from the UI  

---

## 🏃 How to Run
1. Clone the repository  
2. Configure database in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```
3. Run the application:
```bash
mvn spring-boot:run
```
4. Test APIs using **Postman** or **Swagger UI**

---

## ✨ Notes
- `totalPrice` is auto-calculated: `quantity × book price`  
- Custom exceptions implemented:
  - `CustomIDNotfoundException` → When customer not found  
  - `BookIdNotFoundException` → When book not found  
- Prevents duplicate cart items for the same book and customer  

---
# 📘 Order Management Module (Spring Boot)

This module adds **Order Placement functionality** to your existing Spring Boot project using **JPA, Hibernate, DTO, Controller, Service, Repository patterns**.

---

## 🚀 Features Included

* Place orders for books by customer ID
* Restrict non-prime users to:

  * Only **1 book per order**
  * Only **1 order per week**
* Prime users can order **multiple books** with no weekly limit
* Validations:

  * Check if customer exists
  * Check if selected books exist
  * Check ordering rules based on prime/non-prime status
* Auto timestamps: `createdDate`, `updatedDate`
* Response with structured `ResponseMessage`

---

## 📁 Project Structure (Added Files)

```
in.abhayit.Entity.OrderModule
in.abhayit.Model.OrderModuleDto
in.abhayit.Controller.OrderController
in.abhayit.Repository.OrderModuleRepo
in.abhayit.Service.OrderService
in.abhayit.ServiceImpl.OrderServiceimpl
```

---

## 🛠️ Entity: OrderModule

Handles order table mapping.

* Auto `id`
* `bookId`
* `customerId`
* `status`
* Creation & Update timestamps

---

## 📦 DTO: OrderModuleDto

Used to accept order request:

* `customerId`
* `List<String> title` (book titles)

---

## 🎮 Controller: OrderController

API endpoint:

```
POST /orderplaced
```

Accepts `OrderModuleDto` and returns `ResponseMessage` with:

* HTTP code
* success/fail status
* message
* data

---

## 🧠 Business Logic: OrderServiceimpl

Performs validations:

1. Customer exists or not
2. If **non-prime**:

   * Only 1 book allowed
   * Only 1 order per week allowed
3. Finds book by title
4. Creates order entries for each valid book

Returns appropriate success/error messages.

---

## 💽 Repository: OrderModuleRepo

Contains two queries:

1. Check any order placed in last 7 days
2. Find book by title

---

## 📲 API Request Example

### **POST** `/orderplaced`

**Request Body:**

```json
{
  "customerId": 7,
  "title": ["Zero to One", "Deep Work"]
}
```

**Success Response:**

```json
{
  "statusCode": 201,
  "status": "success",
  "message": "Order placed successfully",
  "data": "Order Placed successfully. Thank you.!"
}
```

---

## 🔧 Database Requirements

### Table: **orders**

Columns:

* id (PK, auto increment)
* book_id
* customer_id
* status
* created_date (timestamp)
* updated_date (timestamp)

Ensure your database table names match entity annotations.

---

## 📝 Notes

* Ensure `BooksModule` entity exists with a `title` field
* Ensure `UserRegister` entity contains `prime` boolean
* API can be tested using Postman or Swagger

---

## 🎯 Final Output Behavior

* Prime users can place multiple book orders any time
* Non-prime users get limited based on validations
* Clean error handling with proper messages

---

## 🙌 Author

Abhay Rayate – Spring Boot | Java Developer
