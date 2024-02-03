package com.edstem.taxibookingandbillingsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.edstem.taxibookingandbillingsystem.contract.request.BookingRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingResponse;
import com.edstem.taxibookingandbillingsystem.model.Booking;
import com.edstem.taxibookingandbillingsystem.repository.BookingRepository;
import com.edstem.taxibookingandbillingsystem.repository.UserRepository;
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
        BookingRequest bookingRequest = new BookingRequest("location1", "location2");
        Booking savedBooking = modelMapper.map(bookingRequest, Booking.class);
        BookingResponse expectedResponse = modelMapper.map(savedBooking, BookingResponse.class);

        when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);

        BookingResponse actualResponse = bookingService.bookingTaxi(1L, 10.0, bookingRequest);
        assertEquals(expectedResponse, actualResponse);
    }
}
