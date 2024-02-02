package com.edstem.taxibookingandbillingsystem.controller;

import com.edstem.taxibookingandbillingsystem.contract.request.BookingRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.TaxiRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiResponse;
import com.edstem.taxibookingandbillingsystem.service.TaxiService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaxiController {
    private final TaxiService taxiService;

    @PostMapping("/addingTaxi")
    public TaxiResponse addTaxi(@Valid @RequestBody TaxiRequest request){
        return taxiService.addTaxi(request);

    }
    @GetMapping("/availableTaxis")
    public List<TaxiResponse> availableTaxis (@RequestParam String pickupLocation){
        return taxiService.findAvailableTaxis(pickupLocation);
    }
    @PostMapping("/assigningTaxi")
    public TaxiResponse assignTaxi (@RequestBody BookingRequest request){
        return taxiService.assignTaxiForBooking(request);
    }

}
