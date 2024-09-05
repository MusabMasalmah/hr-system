HR System Backend

Description

The HR System Backend is a Spring Boot application that provides RESTful APIs for managing employees, leave requests, and attendance records. It supports CRUD operations, pagination, and various other features essential for HR management.

Features

- Employee Management: CRUD operations for employee records.
- Leave Requests: CRUD operations for leave requests and tracking.
- Attendance Tracking: Managing and retrieving attendance records, including working hours calculation.

Technologies Used

- Spring Boot: Framework for building the backend service.
- JPA/Hibernate: For ORM and database interaction.
- PostgreSQL: Database for storing records.
- MapStruct: For mapping between entities and DTOs.

Setup and Installation

1. Clone the Repository

   git clone <repository-url>
   cd <repository-directory>

2. Install Dependencies

   Ensure you have Maven installed. Then, run:

   mvn install

3. Configuration

   Configure your application.properties or application.yml with database connection details and other settings.

4. Run the Application

   To start the Spring Boot application, use:

   mvn spring-boot:run

5. Access the API

   The application runs on http://localhost:8080. The available endpoints include:

   - Employee Management: /api/employees
   - Leave Requests: /api/leave-requests
   - Attendance Tracking: /api/attendance

API Endpoints

Employee
- GET /api/employees
- POST /api/employees
- GET /api/employees/{id}
- PUT /api/employees/{id}
- DELETE /api/employees/{id}

Leave Requests
- GET /api/leave-requests
- POST /api/leave-requests
- GET /api/leave-requests/{id}
- PUT /api/leave-requests/{id}
- DELETE /api/leave-requests/{id}

Attendance
- GET /api/attendance
- POST /api/attendance
- GET /api/attendance/{id}
- PUT /api/attendance/{id}
- DELETE /api/attendance/{id}

Testing

Run unit and integration tests with:

   mvn test

Contributing

Feel free to open issues and submit pull requests. Please ensure your code follows the project's coding standards and includes tests.

License

This project is licensed under the MIT License.
