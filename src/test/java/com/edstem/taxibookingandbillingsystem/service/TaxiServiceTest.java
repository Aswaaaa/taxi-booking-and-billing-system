package com.edstem.taxibookingandbillingsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.edstem.taxibookingandbillingsystem.contract.request.TaxiRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.TaxiUpdateRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiUpdateResponse;
import com.edstem.taxibookingandbillingsystem.exception.TaxiNotAvailableException;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import com.edstem.taxibookingandbillingsystem.repository.TaxiRepository;
import com.edstem.taxibookingandbillingsystem.repository.UserRepository;
import java.util.Collections;
import java.util.List;
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
    void testAddTaxi() {

        TaxiRequest request = new TaxiRequest("NAME", "ABC123", "Location");
        Taxi taxi = new Taxi(1L, "NAME", "ABC123", "Location");
        TaxiResponse expectedResponse = new TaxiResponse(1L, "NAME", "ABC123", "Location");

        when(taxiRepository.save(any(Taxi.class))).thenReturn(taxi);
        when(modelMapper.map(taxi, TaxiResponse.class)).thenReturn(expectedResponse);

        TaxiResponse actualResponse = taxiService.addTaxi(request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testUpdateTaxiLocation() {

        Long id = 1L;
        TaxiUpdateRequest request = new TaxiUpdateRequest("New Location");
        Taxi taxi = new Taxi(1L, "Name", "ABC123", "Old Location");
        Taxi updatedTaxi = new Taxi(1L, "Name", "ABC123", "New Location");
        TaxiUpdateResponse expectedResponse = new TaxiUpdateResponse("New Location");

        when(taxiRepository.findById(id)).thenReturn(Optional.of(taxi));
        when(taxiRepository.save(any(Taxi.class))).thenReturn(updatedTaxi);
        when(modelMapper.map(updatedTaxi, TaxiUpdateResponse.class)).thenReturn(expectedResponse);

        TaxiUpdateResponse actualResponse = taxiService.updateTaxiLocation(id, request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testFindTaxi() {
        String pickupLocation = "SomeLocation";
        Taxi taxi1 = new Taxi(1L, "Driver1", "ABC123", "SomeLocation");
        Taxi taxi2 = new Taxi(2L, "Driver2", "XYZ456", "SomeLocation");
        List<Taxi> allTaxis = List.of(taxi1, taxi2);
        List<Taxi> expectedResponse =
                List.of(
                        new Taxi(1L, "Driver1", "ABC123", "SomeLocation"),
                        new Taxi(2L, "Driver2", "XYZ456", "SomeLocation"));

        when(taxiRepository.findAll()).thenReturn(allTaxis);
        when(modelMapper.map(taxi1, Taxi.class)).thenReturn(expectedResponse.get(0));
        when(modelMapper.map(taxi2, Taxi.class)).thenReturn(expectedResponse.get(1));

        List<Taxi> actualResponse = taxiService.findTaxi(pickupLocation);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testFindTaxi_WhenNoTaxisAvailable() {
        String pickupLocation = "pickupLocation";

        when(taxiRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(
                TaxiNotAvailableException.class,
                () -> {
                    taxiService.findTaxi(pickupLocation);
                });
    }
}
