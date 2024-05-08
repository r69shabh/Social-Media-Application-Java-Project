# Social Media Application

This repository contains a simplified version of a social media application built with Spring Boot and Thymeleaf.

## Architectural Blueprint

The application follows a standard MVC architecture with the following components:

- `co.newtonschool.socialmedia.SocialMediaApplication`: The main entry point of the application.
- `co.newtonschool.socialmedia.repository.PostRepository`: The repository interface for accessing the Post data.
- `co.newtonschool.socialmedia.service.PostService`: The service interface for handling the business logic related to Posts.
- `co.newtonschool.socialmedia.service.PostServiceImpl`: The implementation of the PostService interface.

## Setup Instructions

1. Clone the repository to your local machine.
2. Ensure you have Java 17 and Maven installed.
3. Navigate to the project directory and run the application using the Maven wrapper script:
   ```shell
   ./mvnw spring-boot:run
5. The application will start and listen on port 8080.

## Additional Features

- Thymeleaf integration for server-side rendering.
- Spring Boot's auto-configuration feature for rapid development.

Please refer to the codebase for more details on the implementation.

## Contributing

Contributions are welcome. Please submit a pull request or open an issue to discuss the changes you want to make.

## License

This project is licensed under the terms of the MIT license.
