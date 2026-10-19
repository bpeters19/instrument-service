# Instrument Service

A small REST API built with Java and Spring Boot that serves instrument (contract) reference data from PostgreSQL.

I built this to get hands-on with the Java backend stack used in trading and exchange systems. Most of my backend work has been in Python with FastAPI, so this project was about learning how Spring Boot structures an application: entities, repositories, services, controllers, and dependency injection.

## Tech Stack

- Java 21
- Spring Boot 4 (Spring Web, Spring Data JPA)
- PostgreSQL
- Maven (via the included wrapper)

## Architecture

Requests flow through three layers:

```
HTTP request → Controller → Service → Repository → PostgreSQL
```

| Layer | Class | Responsibility |
|---|---|---|
| Entity | `Instrument` | Maps the `instruments` table (id, symbol, name, status) |
| Repository | `InstrumentRepository` | Database access through Spring Data JPA. Queries are generated from method names |
| Service | `InstrumentService` | Business logic. Throws a not-found error for unknown IDs |
| Controller | `InstrumentController` | Exposes the REST endpoints and returns JSON |
| Seeder | `DataSeeder` | Inserts sample instruments on startup if the table is empty |

Components are wired together with constructor injection, so no class creates its own dependencies.

## API

| Method | Endpoint | Description | Response |
|---|---|---|---|
| GET | `/instruments` | List all instruments | `200` with a JSON array |
| GET | `/instruments/{id}` | Get one instrument by ID | `200` with JSON, or `404` if not found |

Example response for `GET /instruments/1`:

```json
{
  "id": 1,
  "symbol": "FED-CUT-DEC26",
  "name": "Fed cuts rates at December 2026 meeting",
  "status": "ACTIVE"
}
```

## Setup

**Prerequisites:** Java 21 and a local PostgreSQL server.

1. Create the database:
```sql
   CREATE DATABASE instruments;
```
2. Set your database credentials as environment variables (PowerShell):
```powershell
   $env:DB_USER="postgres"
   $env:DB_PASSWORD="your_password"
```
   Credentials are read from the environment and are never committed.
3. Run the app:
```powershell
   .\mvnw spring-boot:run
```
   On macOS or Linux, use `./mvnw spring-boot:run`.

The table is created automatically, and three sample instruments are seeded on first startup.

## Testing It

With the app running:

```powershell
Invoke-RestMethod http://localhost:8080/instruments
Invoke-RestMethod http://localhost:8080/instruments/1
```

`http://localhost:8080/instruments/999` returns a `404`.

## What's Next

- Automated tests for the service and controller
- Create and update endpoints with input validation
- Return DTOs instead of entities so the API shape is separate from the database schema
