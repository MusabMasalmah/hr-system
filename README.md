# HR System

## Overview

The HR System is a comprehensive application for managing human resources functions within an organization. This system includes functionalities for employee management, leave requests, and attendance tracking. It provides RESTful APIs for seamless integration and operation.

## Video For Testing



https://github.com/user-attachments/assets/0211f797-3181-4051-ac36-5047006b49e5




## Features

- **Employee Management**: Manage employee records with CRUD operations.
- **Leave Requests**: Handle leave requests, including submission, approval, and tracking.
- **Attendance Tracking**: Record and manage attendance, including start and end times, and calculate working hours.

## Technology Stack

- **Backend Framework**: Spring Boot
- **Database**: PostgreSQL
- **Dependency Management**: Maven

## Getting Started

### Prerequisites

- Java 11 or higher
- PostgreSQL
- Maven
- Git

### Setup the Database

1. Create a PostgreSQL database named `hr_system`.
2. Configure the database connection in `src/main/resources/application.properties`.

### Build and Run

1. Clone the repository:

    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```

2. Install dependencies and build the project:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

4. Access the application at `http://localhost:8080`.

## API Endpoints

### Employee Management

- **GET** `/api/employees` - Retrieve all employees
- **POST** `/api/employees` - Add a new employee
- **GET** `/api/employees/{id}` - Retrieve an employee by ID
- **PUT** `/api/employees/{id}` - Update an employee's details
- **DELETE** `/api/employees/{id}` - Delete an employee

### Leave Requests

- **GET** `/api/leave-requests` - Retrieve all leave requests
- **POST** `/api/leave-requests` - Submit a new leave request
- **GET** `/api/leave-requests/{id}` - Retrieve a leave request by ID
- **PUT** `/api/leave-requests/{id}` - Update a leave request
- **DELETE** `/api/leave-requests/{id}` - Delete a leave request

### Attendance Tracking

- **GET** `/api/attendance` - Retrieve all attendance records
- **POST** `/api/attendance` - Record new attendance
- **GET** `/api/attendance/{id}` - Retrieve an attendance record by ID
- **PUT** `/api/attendance/{id}` - Update an attendance record
- **DELETE** `/api/attendance/{id}` - Delete an attendance record

## Annotations Explained

- **@RestController**: Indicates that the class is a RESTful controller, combining `@Controller` and `@ResponseBody`.
- **@RequestMapping**: Maps HTTP requests to handler methods of MVC and REST controllers.
- **@GetMapping**: Shortcut for `@RequestMapping(method = RequestMethod.GET)`.
- **@PostMapping**: Shortcut for `@RequestMapping(method = RequestMethod.POST)`.
- **@PathVariable**: Binds a method parameter to a URI template variable.
- **@RequestBody**: Binds a method parameter to the body of the web request.
- **@Autowired**: Marks a field or method to be autowired by Spring's dependency injection.
- **@Service**: Indicates that an annotated class is a service.
- **@Repository**: Indicates that an annotated class is a repository, an abstraction for data access.
- **@Query**: Declares finder queries directly on repository methods.
- **@Transactional**: Manages transactions declaratively.
- **@OneToOne**, **@OneToMany**, **@ManyToMany**, **@ManyToOne**: Define relationships between entities.

## Contributing

1. Fork the repository.
2. Create a new branch:

    ```bash
    git checkout -b feature-branch
    ```

3. Commit your changes:

    ```bash
    git commit -am 'Add new feature'
    ```

4. Push to the branch:

    ```bash
    git push origin feature-branch
    ```

5. Create a new Pull Request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For questions or issues, please contact [musabsoos10@gmail.com](mailto:musabsoos10@gmail.com).
