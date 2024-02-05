package com.edstem.taxibookingandbillingsystem.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaxiRequest {
    @NotBlank(message = "It cannot be empty")
    private String driverName;

    @NotBlank(message = "It cannot be empty")
    private String licenseNumber;

    @NotBlank(message = "It cannot be empty")
    private String currentLocation;
}
