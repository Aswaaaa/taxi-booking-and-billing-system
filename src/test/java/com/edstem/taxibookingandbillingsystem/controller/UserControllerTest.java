package com.edstem.taxibookingandbillingsystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.edstem.taxibookingandbillingsystem.contract.request.LoginRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.RegisterRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.UpdateAccountRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.LoginResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.RegisterResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.UpdateAccountResponse;
import com.edstem.taxibookingandbillingsystem.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private UserService userService;

    @Test
    void testRegister() throws Exception {

        RegisterRequest request = new RegisterRequest("name", "email@email", "password");
        RegisterResponse expectedResponse = new RegisterResponse(1L, "name", "email@email");

        when(userService.register(any(RegisterRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(
                        post("/user/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }

    @Test
    void testLogin() throws Exception {
        LoginRequest request = new LoginRequest("email@email", "password");
        LoginResponse expectedResponse = new LoginResponse("testToken");

        when(userService.login(any(LoginRequest.class))).thenReturn(expectedResponse);

        mockMvc.perform(
                        post("/user/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }

    @Test
    void testUpdateBalance() throws Exception {
        Long id = 1L;
        UpdateAccountRequest request = new UpdateAccountRequest(555.0);
        UpdateAccountResponse expectedResponse = new UpdateAccountResponse(666);

        when(userService.updateBalance(id, request)).thenReturn(expectedResponse);

        mockMvc.perform(
                        put("/user/updateBalance/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }
}
