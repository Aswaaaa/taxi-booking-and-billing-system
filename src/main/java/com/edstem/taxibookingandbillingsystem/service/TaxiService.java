package com.edstem.taxibookingandbillingsystem.service;

import com.edstem.taxibookingandbillingsystem.contract.request.BookingRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.TaxiRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiResponse;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import com.edstem.taxibookingandbillingsystem.repository.BookingRepository;
import com.edstem.taxibookingandbillingsystem.repository.TaxiRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaxiService {
    private final TaxiRepository taxiRepository;
    private final BookingRepository bookingRepository;
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

    public List<TaxiResponse> findAvailableTaxis(String pickupLocation) {
        List<Taxi> availableTaxis = taxiRepository.findByCurrentLocation(pickupLocation);
        return availableTaxis.stream()
                .map(taxi -> modelMapper.map(taxi, TaxiResponse.class))
                .collect(Collectors.toList());
    }

    public TaxiResponse assignTaxiForBooking(BookingRequest request) {
        List<Taxi> availableTaxis = taxiRepository.findByCurrentLocation(request.getPickupLocation());

        if (!availableTaxis.isEmpty()) {
            Taxi assignedTaxi = findTaxiWithMatchingLocation(availableTaxis, request.getPickupLocation());
            if (assignedTaxi != null) {
                return modelMapper.map(assignedTaxi, TaxiResponse.class);
            } else {
                throw new EntityNotFoundException("Could not found nearest taxi ");
            }
        } else {
            throw new EntityNotFoundException("No available taxis");

        }
    }
    private Taxi findTaxiWithMatchingLocation(List<Taxi>taxis,String pickupLocation){
        for (Taxi taxi: taxis){
            if (pickupLocation.equals(taxi.getCurrentLocation())){
                return taxi;
            }
        }
        return null;
    }
}
