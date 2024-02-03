package com.edstem.taxibookingandbillingsystem.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiUpdateRequest {
    @NotBlank(message = "Cannot be empty")
    private String updatedLocation;
}
