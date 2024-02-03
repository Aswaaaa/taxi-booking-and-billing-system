package com.edstem.taxibookingandbillingsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.edstem.taxibookingandbillingsystem.contract.request.TaxiRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.TaxiUpdateRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiUpdateResponse;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import com.edstem.taxibookingandbillingsystem.repository.TaxiRepository;
import com.edstem.taxibookingandbillingsystem.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

public class TaxiServiceTest {
    @Mock private TaxiRepository taxiRepository;
    @Mock private UserRepository userRepository;
    @Mock private ModelMapper modelMapper;
    @InjectMocks private TaxiService taxiService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddingTaxi() {

        TaxiRequest request = new TaxiRequest("Name", "123ABC", "Location1");
        TaxiResponse expectedResponse = new TaxiResponse(1L, "Name", "123ABC", "Location1");

        when(taxiService.addTaxi(any(TaxiRequest.class))).thenReturn(expectedResponse);

        TaxiResponse actualResponse = taxiService.addTaxi(request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testUpdatingTaxiLocation() {
        Long id = 1L;
        TaxiUpdateRequest request = new TaxiUpdateRequest("location");
        Taxi taxi = new Taxi(id, "name", "licenseNumber", "location");
        Taxi updatedTaxi = new Taxi(id, "name", "licenseNumber", "location");
        TaxiUpdateResponse expectedResponse = new TaxiUpdateResponse("location");

        when(taxiRepository.findById(id)).thenReturn(Optional.of(taxi));
        when(taxiRepository.save(updatedTaxi)).thenReturn(updatedTaxi);
        when(modelMapper.map(updatedTaxi, TaxiUpdateResponse.class)).thenReturn(expectedResponse);

        TaxiUpdateResponse actualResponse = taxiService.updateTaxiLocation(id, request);

        assertEquals(expectedResponse, actualResponse);
    }
}
