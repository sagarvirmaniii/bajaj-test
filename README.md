# Chitkara Qualifier BFHL API

Production-ready Spring Boot 3 REST API for the Chitkara Qualifier 1 BFHL problem.

## Tech Stack

- Java 21
- Spring Boot 3
- Maven
- JUnit 5
- Lombok
- OpenAPI Swagger UI
- Spring Boot Actuator
- Docker
- Render free tier deployment config

## API

### POST `/bfhl`

Valid requests always return HTTP `200`.

Request:

```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

Response:

```json
{
  "is_success": true,
  "user_id": "sagar_virmani_24062026",
  "email": "sagarvirmni@gmail.com",
  "roll_number": "2310991635",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

Error response shape:

```json
{
  "is_success": false,
  "message": "error message"
}
```

## Build

```bash
mvn clean package
```

## Run

```bash
mvn spring-boot:run
```

The API starts on `http://localhost:8080` by default.

## Test

```bash
mvn test
```

JaCoCo coverage report:

```bash
open target/site/jacoco/index.html
```

## Swagger

Swagger UI:

```text
http://localhost:8080/swagger-ui.html
```

OpenAPI JSON:

```text
http://localhost:8080/v3/api-docs
```

## Actuator

Health endpoint:

```text
http://localhost:8080/actuator/health
```

## Docker

Build the image:

```bash
docker build -t chitkara-qualifier-api .
```

Run the container:

```bash
docker run --rm -p 8080:8080 chitkara-qualifier-api
```

## Render Deployment

1. Push this repository to GitHub.
2. Open Render and create a new Blueprint.
3. Select the GitHub repository.
4. Render will read `render.yaml`.
5. Deploy the `chitkara-qualifier-api` web service.

Render sets the `PORT` environment variable automatically. The app reads it through:

```properties
server.port=${PORT:8080}
```

## Project Structure

```text
src/main/java/com/bhfl
├── Application.java
├── config
│   └── OpenApiConfig.java
├── controller
│   └── BfhlController.java
├── dto
│   ├── BfhlRequest.java
│   └── BfhlResponse.java
├── exception
│   └── GlobalExceptionHandler.java
├── service
│   ├── BfhlService.java
│   └── BfhlServiceImpl.java
└── util
    └── ConcatStringUtil.java
```

## CI

GitHub Actions workflow: `.github/workflows/build.yml`

It runs:

```bash
mvn test
mvn package
```
