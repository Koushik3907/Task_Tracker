# Task Tracker CLI

## Overview
Task Tracker CLI is a simple task management system built using Java and Spring Boot. It allows users to create, update, delete, and retrieve tasks using a REST API. The task data is stored in a JSON file for persistence.

## Features
- Add new tasks
- Update existing tasks
- Delete tasks
- Retrieve all tasks
- Search tasks by status

## Technologies Used
- Java
- Spring Boot
- Jackson (for JSON processing)
- Lombok (for reducing boilerplate code)
- REST API

## Project Structure
```
Task_Tracker/
│── Controllers/
│   └── TaskController.java
│── Entity/
│   └── TaskEntity.java
│── Service/
│   └── TaskService.java
│── tasks.json (Data Storage)
│── README.md
```

## Installation & Setup
### Prerequisites
- Java (JDK 11 or later)
- Maven (optional for dependency management)
- IDE (such as IntelliJ IDEA or VS Code)

### Steps
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/task-tracker-cli.git
   cd task-tracker-cli
   ```
2. Open the project in your preferred IDE.
3. Run the application using the Spring Boot runner.
4. The server will start, and the API will be available at `http://localhost:8080/tasks`.

## API Endpoints

### Get all tasks
```http
GET /tasks
```
Response:
```json
[
  {
    "id": "1",
    "title": "Task 1",
    "description": "Description of task 1",
    "status": "Pending"
  }
]
```

### Add a task
```http
POST /tasks
```
Request Body:
```json
{
  "title": "New Task",
  "description": "Task description"
}
```
Response:
```json
"Task Added Successfully"
```

### Update a task
```http
PUT /tasks/{id}
```
Request Body:
```json
{
  "title": "Updated Task",
  "description": "Updated description",
  "status": "Completed"
}
```
Response:
```json
"Task Updated Successfully"
```

### Delete a task
```http
DELETE /tasks/{id}
```
Response:
```json
"Task Deleted Successfully"
```

### Search tasks by status
```http
GET /tasks/{status}
```
Response:
```json
[
  {
    "id": "1",
    "title": "Task 1",
    "description": "Description of task 1",
    "status": "Completed"
  }
]
```

## License
This project is licensed under the MIT License.

## Author
- **Your Name**

