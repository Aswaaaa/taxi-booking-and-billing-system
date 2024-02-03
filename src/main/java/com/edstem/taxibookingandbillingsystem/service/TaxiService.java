package com.edstem.taxibookingandbillingsystem.service;

import com.edstem.taxibookingandbillingsystem.contract.request.TaxiRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.TaxiUpdateRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiUpdateResponse;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import com.edstem.taxibookingandbillingsystem.repository.TaxiRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxiService {
    private final TaxiRepository taxiRepository;
    private final ModelMapper modelMapper;

    public TaxiResponse addTaxi(TaxiRequest request) {
        Taxi taxi =
                Taxi.builder()
                        .driverName(request.getDriverName())
                        .licenseNumber(request.getLicenseNumber())
                        .currentLocation(request.getCurrentLocation())
                        .build();
        taxi = taxiRepository.save(taxi);
        return modelMapper.map(taxi, TaxiResponse.class);
    }

    public TaxiUpdateResponse updateTaxiLocation(Long id, TaxiUpdateRequest request) {
        Taxi taxi =
                taxiRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("Taxi not found with id " + id));
        Taxi updatedTaxi =
                Taxi.builder()
                        .id(taxi.getId())
                        .driverName(taxi.getDriverName())
                        .licenseNumber(taxi.getLicenseNumber())
                        .currentLocation(request.getUpdatedLocation())
                        .build();
        updatedTaxi = taxiRepository.save(updatedTaxi);

        return modelMapper.map(updatedTaxi, TaxiUpdateResponse.class);
    }

    public List<TaxiResponse> findTaxi(String pickupLocation) {
        List<Taxi> allTaxis = taxiRepository.findAll();
        List<Taxi> availableTaxis = new ArrayList<>();
        for (Taxi taxis : allTaxis) {
            if (taxis.getCurrentLocation().equals(pickupLocation)) {
                availableTaxis.add(taxis);
            }
        }
        if (availableTaxis.isEmpty()) {
            throw new EntityNotFoundException("Taxi not available");
        } else {
            return availableTaxis.stream()
                    .map(taxi -> modelMapper.map(taxi, TaxiResponse.class))
                    .collect(Collectors.toList());
        }
    }
}
