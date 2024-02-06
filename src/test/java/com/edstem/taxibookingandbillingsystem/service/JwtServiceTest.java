package com.edstem.taxibookingandbillingsystem.service;

import com.edstem.taxibookingandbillingsystem.configuration.JwtService;
import com.edstem.taxibookingandbillingsystem.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JwtServiceTest {
    private final JwtService jwtService = new JwtService();
    private final User mockUser = Mockito.mock(User.class);
    private final UserDetails mockUserDetails = Mockito.mock(UserDetails.class);

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGenerateToken() {
        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(1L);
        when(mockUser.getUsername()).thenReturn("testUser");

        String token = jwtService.generateToken(mockUser);

        assertNotNull(token);
    }

}
