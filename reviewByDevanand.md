## Review By Devanand

### Model Class:
Keep User Model Class small, put most of the additional information in somewhere else

### Class Tests: 
Booking Service:

//Line 28
1. You have used EntityNotFoundException but your test name is testEntityAlreadyExistsException
2. In booking service, InsufficientBalanceException(); is not tested.
3. BookingNotFoundException(id) is not tested for getBookingDetails, cancelBookingById in booking service.
 
Taxi Service: 

1.  EntityNotFoundException("Taxi not found with id " + id)) is not tested updateTaxiLocation in taxi service.
2.  EntityNotFoundException("Taxi not available") not tested for findTaxi in taxi service. 

User Service :
1.  EntityAlreadyExistsException(request.getEmail()) is not tested for register in user service;
2. InvalidLoginException() not tested for login in user service.

Main :

main() is not tested.


     
