package com.edstem.taxibookingandbillingsystem.service;

import com.edstem.taxibookingandbillingsystem.constant.Status;
import com.edstem.taxibookingandbillingsystem.contract.request.BookingRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingResponse;
import com.edstem.taxibookingandbillingsystem.exception.BookingNotFoundException;
import com.edstem.taxibookingandbillingsystem.model.Booking;
import com.edstem.taxibookingandbillingsystem.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;


    public BookingResponse bookingTaxi(BookingRequest request) {
        Booking booking = Booking.builder()
                .pickupLocation(request.getPickupLocation())
                .dropoffLocation(request.getDropoffLocation())
                .bookingTime(LocalDateTime.parse(LocalDateTime.now().toString()))
                .status(Status.CONFIRMED)
                .build();

            booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingResponse.class);
    }


    public BookingResponse getBookingDetails(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
        return modelMapper.map(booking, BookingResponse.class);

    }

    public String cancelBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).
                orElseThrow(() -> new BookingNotFoundException(id));

        Booking updatedBooking = Booking.builder()
                .status(Status.valueOf("CANCELLED"))
                        .build();

        bookingRepository.save(updatedBooking);
        return "Booking with ID: " + id + " has been cancelled successfully.";
    }
}
