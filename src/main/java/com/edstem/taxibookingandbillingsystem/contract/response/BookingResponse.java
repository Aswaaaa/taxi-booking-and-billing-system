package com.edstem.taxibookingandbillingsystem.contract.response;

import com.edstem.taxibookingandbillingsystem.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookingResponse {
    private Long id;
    private String pickupLocation;
    private String dropoffLocation;
    private double fare;
    private Status status;
}
