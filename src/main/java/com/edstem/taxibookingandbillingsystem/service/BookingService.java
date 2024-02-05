package com.edstem.taxibookingandbillingsystem.service;

import com.edstem.taxibookingandbillingsystem.constant.Status;
import com.edstem.taxibookingandbillingsystem.contract.request.BookingRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingDetailsResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingResponse;
import com.edstem.taxibookingandbillingsystem.exception.BookingNotFoundException;
import com.edstem.taxibookingandbillingsystem.exception.InsufficientBalanceException;
import com.edstem.taxibookingandbillingsystem.model.Booking;
import com.edstem.taxibookingandbillingsystem.model.User;
import com.edstem.taxibookingandbillingsystem.repository.BookingRepository;
import com.edstem.taxibookingandbillingsystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public BookingResponse bookingTaxi(Long userId, double distance, BookingRequest request) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

        double minimumCharge = 12.00;
        double fare = distance * minimumCharge;

        if (fare > user.getAccountBalance()) {
            throw new InsufficientBalanceException();
        }

        Booking booking =
                Booking.builder()
                        .pickupLocation(request.getPickupLocation())
                        .dropoffLocation(request.getDropoffLocation())
                        .bookingTime(LocalDateTime.now())
                        .fare(fare)
                        .status(Status.CONFIRMED)
                        .userId(user)
                        .build();

        User updatedUser =
                User.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .accountBalance(user.getAccountBalance() - booking.getFare())
                        .build();

        userRepository.save(updatedUser);
        bookingRepository.save(booking);

        return modelMapper.map(booking, BookingResponse.class);
    }

    public BookingDetailsResponse getBookingDetails(Long id) {
        Booking booking =
                bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        return modelMapper.map(booking, BookingDetailsResponse.class);
    }

    public String cancelBookingById(Long id) {
        Booking booking =
                bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));

        Booking updatedBooking =
                Booking.builder()
                        .id(id)
                        .pickupLocation(booking.getPickupLocation())
                        .dropoffLocation(booking.getDropoffLocation())
                        .fare(booking.getFare())
                        .bookingTime(LocalDateTime.parse(LocalDateTime.now().toString()))
                        .status(Status.CANCELLED)
                        .build();

        bookingRepository.save(updatedBooking);
        return "Booking with ID: " + id + " has been cancelled successfully.";
    }
}
