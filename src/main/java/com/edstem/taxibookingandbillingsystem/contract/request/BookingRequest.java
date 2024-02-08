package com.edstem.taxibookingandbillingsystem.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class BookingRequest {
    @NotBlank(message = "Field cannot be blank")
    private String pickupLocation;

    @NotBlank(message = "Field cannot be blank")
    private String dropoffLocation;
}
