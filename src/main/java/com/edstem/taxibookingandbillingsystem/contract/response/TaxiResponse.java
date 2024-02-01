package com.edstem.taxibookingandbillingsystem.contract.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiResponse {
    private String driverName;
    private String licenseNumber;
}
