# Library Manager
This project provides a RESTful API designed for library management. It facilitates the registration of both borrowers and books, and handles the processes of borrowing and returning books. Authentication is managed through stateless JSON Web Tokens (JWT) for security.

## Tech Stack
- Server port: 8081
- Database port: 3306

| Technologies    | Versions |
| -------- | ------- |
| Java  | 17    |
| Spring Boot | 3.3.2     |
| Spring Data JPA    | 3.3.2    |
| JWT    | 0.10.8    |
| JUnit    | 5.9.3    |
| MySQL  | 8.0    |
| Maven | 3.9.8     |
| Docker    | 27.0.3    |
| Swagger    | 2.0.2    |

## Setup
- Install the Docker.
- Clone the project.
- Start the docker container "docker-compose up -d --build"
- Create the admin user to access the system
- Login to the system and get the token.
- Access the APIs passing the token.

## API Document
- Document: https://docs.google.com/document/d/1XETQ19rvhauHYtTSdincVlBBLiGcIMmeVS-ji8Vhuzc/edit?usp=sharing
- swagger: /swagger-ui/index.html

## Database
![ER drawio](https://github.com/user-attachments/assets/26b5b39d-d6de-48ff-9242-b173861d5394)

