package com.edstem.taxibookingandbillingsystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edstem.taxibookingandbillingsystem.contract.request.TaxiRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.TaxiUpdateRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.TaxiUpdateResponse;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import com.edstem.taxibookingandbillingsystem.repository.TaxiRepository;
import com.edstem.taxibookingandbillingsystem.service.TaxiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TaxiControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private TaxiService taxiService;
    @MockBean private TaxiRepository taxiRepository;

    @Test
    void testAddingTaxi() throws Exception {
        TaxiRequest request = new TaxiRequest("Name", "123ABC", "Location1");
        TaxiResponse expectedResponse = new TaxiResponse(1L, "Name", "123ABC", "Location1");

        when(taxiService.addTaxi(any(TaxiRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(
                        post("/api/addingTaxi")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }

    @Test
    void testUpdateTaxiLocation() throws Exception {

        Long id = 1L;
        TaxiUpdateRequest request = new TaxiUpdateRequest("New Location");
        TaxiUpdateResponse expectedResponse = new TaxiUpdateResponse("New Location");

        when(taxiService.updateTaxiLocation(id, request)).thenReturn(expectedResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/updatingTaxiLocation/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }

    @Test
    public void testFindTaxi() throws Exception {

        String pickupLocation = "Location1";
        List<Taxi> expectedResponse =
                Arrays.asList(
                        new Taxi(1L, "Name1", "123ABC", "Location1"),
                        new Taxi(2L, "Name2", "456DEF", "Location1"));

        when(taxiService.findTaxi(pickupLocation)).thenReturn(expectedResponse);

        mockMvc.perform(
                        get("/api/findTaxi")
                                .param("pickupLocation", pickupLocation)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }
}
