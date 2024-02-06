# Service

### bookingTaxi
* taxi_id showing null while add booking
### cancelBookingById
* taxi_id and user_id showing null while cancel booking

## General Issues
- validations,exceptions are not working

## Security Concerns
- Remove secret-key from JwtService.Consider using environment variables or a secure configuration management system.

## Pending Tasks
- Implement unit-testing for `jwt-service`
- Implement appropriate logging and error reporting.
- Implement logic to assign the nearest available taxi while booking.

#### There is currently no integration with external services like payment gateways.