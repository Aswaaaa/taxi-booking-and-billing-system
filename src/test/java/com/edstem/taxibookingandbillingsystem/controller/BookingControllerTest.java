package com.edstem.taxibookingandbillingsystem.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edstem.taxibookingandbillingsystem.constant.Status;
import com.edstem.taxibookingandbillingsystem.contract.request.BookingRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingDetailsResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.BookingResponse;
import com.edstem.taxibookingandbillingsystem.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BookingControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private BookingService bookingService;

    @Test
    void testBookingTaxi() throws Exception {
        Long userId = 1L;
        double distance = 10.00;

        BookingRequest request = new BookingRequest("location1", "location2");
        BookingResponse expectedResponse =
                new BookingResponse(1L, "location1", "location2", 55.0, Status.CONFIRMED);

        when(bookingService.bookingTaxi(userId, distance, request)).thenReturn(expectedResponse);

        mockMvc.perform(
                        post("/api/bookingTaxi/" + userId + "?distance=" + distance)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }

    @Test
    void testGetBookingDetails() throws Exception {
        Long id = 1L;
        BookingDetailsResponse bookingDetailsResponse =
                new BookingDetailsResponse(id, "location1", LocalDateTime.now().toString(), 55.0);

        when(bookingService.getBookingDetails(id)).thenReturn(bookingDetailsResponse);

        mockMvc.perform(get("/api/bookingDetails/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(
                        content()
                                .json(
                                        new ObjectMapper()
                                                .writeValueAsString(bookingDetailsResponse)));
    }

    @Test
    void testCancelBooking() throws Exception {
        Long id = 1L;
        String expectedResponse = "Booking with ID: " + id + " has been cancelled successfully.";

        when(bookingService.cancelBookingById(id)).thenReturn(expectedResponse);

        mockMvc.perform(put("/api/cancelBooking/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}
