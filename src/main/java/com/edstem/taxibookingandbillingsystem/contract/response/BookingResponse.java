package com.edstem.taxibookingandbillingsystem.contract.response;

import com.edstem.taxibookingandbillingsystem.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {
    private Long id;
    private String pickupLocation;
    private String dropoffLocation;
    private double fare;
    private Status status;
}
