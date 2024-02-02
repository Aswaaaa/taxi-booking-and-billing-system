package com.edstem.taxibookingandbillingsystem.service;

import com.edstem.taxibookingandbillingsystem.contract.request.TaxiRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiResponse;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import com.edstem.taxibookingandbillingsystem.repository.TaxiRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaxiService {
    private final TaxiRepository taxiRepository;
    private final ModelMapper modelMapper;
    public TaxiResponse addTaxi(TaxiRequest request) {
        Taxi taxi = Taxi.builder()
                .driverName(request.getDriverName())
                .licenseNumber(request.getLicenseNumber())
                .currentLocation(request.getCurrentLocation())
                .build();
        taxi = taxiRepository.save(taxi);
        return modelMapper.map(taxi, TaxiResponse.class);
    }
    public List<TaxiResponse> findAvailableTaxis(String pickupLocation){
        List<Taxi> availableTaxis = taxiRepository.findByCurrentLocation(pickupLocation );
        return availableTaxis.stream()
                .map(taxi -> modelMapper.map(taxi,TaxiResponse.class))
                .collect(Collectors.toList());
    }
}
