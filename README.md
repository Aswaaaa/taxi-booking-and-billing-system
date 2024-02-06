# Taxi Booking System

This project is a Taxi booking and billing system built using Maven and Spring Boot in Java. It provides a set of
RESTful APIs to manage tasks.

## Features

The system supports the following features operations:

### User Endpoints

* POST /user/register: Register a user.
* POST /user/login: Login a user.
* PUT /user/updateBalance/{id}: Update account balance of a user.

### Booking Endpoints

* POST /api/bookingTaxi: Book a taxi.
* GET /api/bookingDetails/{id}: Get the booking details by ID.
* PUT /api/cancelBooking/{id}: Cancel a booking by ID.

### Taxi Endpoints

* POST /api/addingTaxi: Adding a taxi with relevant information.
* PUT /api/updatingTaxiLocation/{id}: Updating the current location of a taxi.
* GET /api/findTaxi: Find a taxi with the pickup location.

##

Prerequisites
Before you start, ensure you have met the following requirements:

* Java Development Kit (JDK) 17
* Maven
* PostgreSQL

Other dependencies

## Setup

To set up the project locally, follow the following:

* Clone the repository to your local machine.
* Navigate to the project directory.
* Run `mvn clean install` to build the project and install dependencies.
* Start the application by running `mvn spring-boot:run`.
* The server should now be running locally! You can access the APIs at `localhost` on the specified port.


## Testing

To test the APIs, you can use any API testing tool Postman. Make sure your server is running, and then send HTTP
requests to the API endpoints.
