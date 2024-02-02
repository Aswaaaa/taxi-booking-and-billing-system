package com.edstem.taxibookingandbillingsystem.controller;

import com.edstem.taxibookingandbillingsystem.contract.request.BookingRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingDetailsResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingResponse;
import com.edstem.taxibookingandbillingsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;


    @PostMapping("/booking_taxi/{userId}")
    public @ResponseBody BookingResponse bookingTaxi(@PathVariable Long userId, @RequestParam double distance, @RequestBody BookingRequest request,String pickupLocation){
        return bookingService.bookingTaxi(userId,distance,request,pickupLocation);
    }
    @GetMapping("/booking_details/{id}")
    public BookingDetailsResponse getBookingById(@PathVariable Long id){
        return bookingService.getBookingDetails(id);
    }

    @PutMapping("/cancel_booking/{id}")
    public String cancelBooking(@PathVariable Long id){
        return bookingService.cancelBookingById(id);
    }
}
