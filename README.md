# Cinema Room REST Service

## Assignment
Create a simple Spring REST service that will help you manage a small movie theater. </br>
It should handle HTTP requests in controllers, create services, and respond with JSON objects.

### What did I learn
1. What is a common structure for a Spring project; 
2. How to create endpoints inside a controller (& how `@RestController` is different from `@Controller`)
   - how to handle exceptions thrown inside a controller;
3. How elements of an app connect using Dependency Injection (DI);
4. How multiple people can interact with the web-app and not cause data loss (multi-threading and synchronization);
5. What are DTOs and the common concepts applied when using them

### Technologies used
**Language**: Java 17 </br>
**Frameworks**: SpringBoot </br>
**Tools**: Postman

## Endpoints

#### Get Available Seats
- **URL:** `/seats`
- **Method:** `GET`
- **Response:**
  - **Status:** `200 OK`
  - **Body:**
    ```json
    {
      "numberOfRows": 9,
      "numberOfColumns": 9,
      "availableSeats": [
        {
          "row": 1,
          "column": 1,
          "price": 10
        }
        // ...
      ]
    }
    ```

#### Purchase Ticket
- **URL:** `/purchase`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "row": 1,
    "column": 1
  }
  ```
- **Response:**
  - **Status:** `200 OK`
  - **Body:**
    ```json
    {
      "token": "some-token",
      "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
      }
    }
    ```
  - ***Edge Cases:***
    - Seat already purchased: Return a 400 Bad Request with an appropriate error message.
    - Invalid seat coordinates (e.g., row or column out of bounds): Return a 400 Bad Request with an appropriate error message.
    - Missing request body: Return a 400 Bad Request with an appropriate error message.

#### Return Ticket
- **URL:** `/return`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "token": "some-token"
  }
  ```
- **Response:**
  - **Status:** `200 OK`
  - **Body:**
    ```json
    {
      "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
      }
    }
    ```
  - ***Edge Cases:***
    - Invalid or expired token: Return a 404 Not Found with an appropriate error message.
    - Missing request body: Return a 400 Bad Request with an appropriate error message.

#### Get Cinema Stats
- **URL:** `/stats`
- **Method:** `POST`
- **Request Body:**
  ```json
  {
    "password": "super_secret"
  }
  ```
- **Response:**
  - **Status:** `200 OK`
  - **Body:**
    ```json
    {
      "totalIncome": 100,
      "numberOfAvailableSeats": 80,
      "purchasedTickets": 20
    }
    ```
  - ***Edge Cases:***
    - Incorrect password: Return a 401 Unauthorized with an appropriate error message.
    - Missing password: Return a 400 Bad Request with an appropriate error message.


## Potential future enhancements
Some of the possible features that would be great to implement in a project of this type
1. User management - different roles and access settings for different people;
2. Store tickets (and users) in a database instead of a runtime;
3. Buy multiple seats in one order - create multiple tickets inside one order;
4. Scale it to handle multiple rooms in a cinema.
   