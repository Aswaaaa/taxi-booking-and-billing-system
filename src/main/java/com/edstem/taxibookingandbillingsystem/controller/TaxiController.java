package com.edstem.taxibookingandbillingsystem.controller;

import com.edstem.taxibookingandbillingsystem.contract.request.TaxiRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.TaxiUpdateRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiUpdateResponse;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import com.edstem.taxibookingandbillingsystem.service.TaxiService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaxiController {
    private final TaxiService taxiService;

    @PostMapping("/addingTaxi")
    public TaxiResponse addTaxi(@Valid @RequestBody TaxiRequest request) {
        return taxiService.addTaxi(request);
    }

    @PutMapping("/updatingTaxiLocation/{id}")
    public TaxiUpdateResponse updateTaxiLocation(
            @PathVariable Long id, @Valid @RequestBody TaxiUpdateRequest request) {
        return taxiService.updateTaxiLocation(id, request);
    }

    @GetMapping("/findTaxi")
    public List<Taxi> findTaxi(@RequestParam String pickupLocation) {
        return taxiService.findTaxi(pickupLocation);
    }
}
