package com.edstem.taxibookingandbillingsystem.contract.response;

import com.edstem.taxibookingandbillingsystem.model.Taxi;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BookingDetailsResponse {

    private Long id;
    private String pickupLocation;
    private TaxiResponse taxi;

}