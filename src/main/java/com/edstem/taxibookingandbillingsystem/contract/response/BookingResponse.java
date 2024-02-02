package com.edstem.taxibookingandbillingsystem.contract.response;

import com.edstem.taxibookingandbillingsystem.constant.Status;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Long id;
//    private Long userId;
    private Taxi taxi;
    private String pickupLocation;
    private String dropoffLocation;
    private double fare;
    private LocalDateTime bookingTime;
    private Status status;
}
