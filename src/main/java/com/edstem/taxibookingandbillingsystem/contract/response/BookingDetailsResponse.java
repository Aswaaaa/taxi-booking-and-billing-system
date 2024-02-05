package com.edstem.taxibookingandbillingsystem.contract.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookingDetailsResponse {

    private Long id;
    private String pickupLocation;
    private String bookingTime;
    private double fare;
}
