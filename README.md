**# Library Management API**

## **Overview**
The **Library Management API** is a RESTful service built with **Spring Boot**, designed to manage books, users, and loans in a library system. It allows users to register, borrow books, return them, and view book details.

## **Features**
- 📚 **Manage Books:** Create, list, search, and delete books.
- 👤 **Manage Users:** Register users, update profiles, and delete accounts.
- 🔄 **Loan System:** Borrow and return books.
- 🚀 **Swagger UI:** API documentation and testing interface.
- ⚡ **Exception Handling:** Custom error messages for better debugging.

## **Technologies Used**
- **Java 21**
- **Spring Boot 3.2.2**
- **Spring Data JPA**
- **Hibernate**
- **Swagger (Springdoc OpenAPI)**
- **Lombok**
- **H2 Database** (For in-memory testing)

## **Installation & Setup**
### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/yourusername/library-management.git
cd library-management
```

### **2️⃣ Build the Project**
```bash
mvn clean install
```

### **3️⃣ Run the Application**
```bash
mvn spring-boot:run
```
The API will be available at: `http://localhost:8080`

### **4️⃣ Access Swagger UI**
To explore and test the API endpoints, open:
```
http://localhost:8080/swagger-ui/index.html
```

## **API Endpoints**
### **Books** 📚
| Method | Endpoint         | Description              |
|--------|-----------------|--------------------------|
| `POST` | `/books`        | Add a new book          |
| `GET`  | `/books`        | Get all books           |
| `GET`  | `/books/{title}` | Get a book by title    |
| `DELETE` | `/books/{title}` | Delete a book by title |

### **Users** 👤
| Method | Endpoint       | Description              |
|--------|---------------|--------------------------|
| `POST` | `/users`      | Register a new user     |
| `GET`  | `/users/{id}` | Get user details        |
| `PUT`  | `/users/{id}` | Update user information |
| `DELETE` | `/users/{id}` | Delete a user account  |

### **Loans** 🔄
| Method | Endpoint        | Description               |
|--------|----------------|---------------------------|
| `POST` | `/loans/borrow` | Borrow a book            |
| `POST` | `/loans/return` | Return a borrowed book   |

## **Contributing**
Feel free to fork this project and submit pull requests. Contributions are welcome! 🚀

