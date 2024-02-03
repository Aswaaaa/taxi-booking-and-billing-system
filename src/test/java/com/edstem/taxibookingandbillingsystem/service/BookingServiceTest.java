package com.edstem.taxibookingandbillingsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.edstem.taxibookingandbillingsystem.constant.Status;
import com.edstem.taxibookingandbillingsystem.contract.request.BookingRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingDetailsResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingResponse;
import com.edstem.taxibookingandbillingsystem.model.Booking;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import com.edstem.taxibookingandbillingsystem.model.User;
import com.edstem.taxibookingandbillingsystem.repository.BookingRepository;
import com.edstem.taxibookingandbillingsystem.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class BookingServiceTest {
    @Mock private BookingRepository bookingRepository;
    @Mock private UserRepository userRepository;
    @Mock private ModelMapper modelMapper;
    @InjectMocks private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testBookingTaxi() {

        Long userId = 1L;
        double distance = 10.0;
        BookingRequest request = new BookingRequest("PickupLocation", "DropoffLocation");
        Taxi taxi = new Taxi(1L, "driverName", "ABC23", "location");
        User user = new User(1L, "name", "email@email.com", "password", 100.0);
        Booking booking =
                new Booking(
                        1L,
                        user,
                        taxi,
                        "PickupLocation",
                        "DropoffLocation",
                        120.0,
                        LocalDateTime.now(),
                        Status.CONFIRMED);
        BookingResponse expectedResponse =
                new BookingResponse(
                        1L, "PickupLocation", "DropoffLocation", 120.0, Status.CONFIRMED);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(booking, BookingResponse.class)).thenReturn(expectedResponse);

        BookingResponse actualResponse = bookingService.bookingTaxi(userId, distance, request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testGetBookingDetails() {
        // Arrange
        Long bookingId = 1L;
        Taxi taxi = new Taxi(1L, "driverName", "ABC23", "location");
        User user = new User(1L, "name", "email@email.com", "password", 100.0);
        Booking booking =
                new Booking(
                        1L,
                        user,
                        taxi,
                        "PickupLocation",
                        "DropoffLocation",
                        120.0,
                        LocalDateTime.now(),
                        Status.CONFIRMED);
        BookingDetailsResponse expectedResponse =
                new BookingDetailsResponse(
                        1L, "PickupLocation", LocalDateTime.now().toString(), 120.0);

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
        when(modelMapper.map(booking, BookingDetailsResponse.class)).thenReturn(expectedResponse);

        // Act
        BookingDetailsResponse actualResponse = bookingService.getBookingDetails(bookingId);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testCancelBookingById() {
        Long bookingId = 1L;
        Long userId = 1L;
        Long taxiId = 1L;
        User user = new User(userId, "username", "email", "password", 100.0);
        Taxi taxi = new Taxi(taxiId, "name", "AB234", "location");
        Booking booking =
                Booking.builder()
                        .userId(user)
                        .taxiId(taxi)
                        .pickupLocation("location1")
                        .dropoffLocation("location2")
                        .fare(12.00)
                        .bookingTime(LocalDateTime.now())
                        .status(Status.CONFIRMED)
                        .build();

        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));

        String actualResponse = bookingService.cancelBookingById(bookingId);

        assertEquals(
                "Booking with ID: " + bookingId + " has been cancelled successfully.",
                actualResponse);
    }
}
