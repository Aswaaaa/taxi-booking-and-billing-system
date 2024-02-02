package com.edstem.taxibookingandbillingsystem.contract.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BookingRequest {
    private String pickupLocation;
    private String dropoffLocation;
}
