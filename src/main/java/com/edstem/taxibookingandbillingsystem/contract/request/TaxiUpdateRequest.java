package com.edstem.taxibookingandbillingsystem.contract.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiUpdateRequest {
    private String updatedLocation;
}
