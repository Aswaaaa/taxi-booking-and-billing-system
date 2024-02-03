package com.edstem.taxibookingandbillingsystem.contract.request;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "It cannot be empty")
    private String pickupLocation;

    @NotBlank(message = "It cannot be empty")
    private String dropoffLocation;
}
