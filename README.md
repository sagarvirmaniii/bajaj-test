# Chitkara Qualifier BFHL API

Production-ready Spring Boot 3 REST API solution for the Chitkara Qualifier 1 BFHL Challenge.

## Live Deployment

| Resource           | URL                                                       |
| ------------------ | --------------------------------------------------------- |
| Base URL           | https://bajaj-api-986b.onrender.com                       |
| BFHL API (POST)    | https://bajaj-api-986b.onrender.com/bfhl                  |
| Health Check (GET) | https://bajaj-api-986b.onrender.com/actuator/health       |
| Swagger UI         | https://bajaj-api-986b.onrender.com/swagger-ui/index.html |
| OpenAPI Docs       | https://bajaj-api-986b.onrender.com/v3/api-docs           |

## Candidate Information

| Field             | Value                                                                   |
| ----------------- | ----------------------------------------------------------------------- |
| Name              | Sagar Virmani                                                           |
| Email             | [sagar1384.be23@chitkara.edu.in](mailto:sagar1384.be23@chitkara.edu.in) |
| Roll Number       | 2310991384                                                              |
| GitHub Repository | https://github.com/sagarvirmaniii/bajaj-test                            |

## Features

* Spring Boot 3 REST API
* POST `/bfhl` Endpoint
* GET `/bfhl` Health Endpoint
* Request & Response DTOs
* Service Layer with Interface
* Global Exception Handling
* Swagger / OpenAPI Documentation
* JUnit 5 Test Cases
* JaCoCo Code Coverage
* Docker Support
* Render Deployment Ready
* Spring Boot Actuator Health Monitoring

---

## Sample Request

```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

## Sample Response

```json
{
  "is_success": true,
  "user_id": "sagar_virmani_24062026",
  "email": "sagar1384.be23@chitkara.edu.in",
  "roll_number": "2310991384",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

## Quick Test

```bash
curl -X POST https://bajaj-api-986b.onrender.com/bfhl \
-H "Content-Type: application/json" \
-d '{
  "data": ["s","1","334","4","P","$"]
}'
```

---

## Tech Stack

* Java 21
* Spring Boot 3
* Maven
* JUnit 5
* Lombok
* OpenAPI Swagger UI
* Spring Boot Actuator
* Docker
* Render
