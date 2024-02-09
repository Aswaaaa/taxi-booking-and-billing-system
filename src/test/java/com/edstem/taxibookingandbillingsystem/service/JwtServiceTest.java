package com.edstem.taxibookingandbillingsystem.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.edstem.taxibookingandbillingsystem.configuration.JwtService;
import com.edstem.taxibookingandbillingsystem.model.User;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

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

    @Test
    public void testIsTokenValid() {
        User mockUser = mock(User.class);
        when(mockUser.getUsername()).thenReturn("testUser");

        JwtService jwtService = new JwtService();

        String token = jwtService.generateToken(mockUser);

        boolean isValid = jwtService.isTokenValid(token, mockUser);
        assertTrue(isValid, "Token should be valid");
    }

    @Test
    public void testIsTokenExpired() {
        String token = "DummyToken";

        JwtService jwtService = mock(JwtService.class);

        boolean isExpired = jwtService.isTokenExpired(token);

        assertFalse(isExpired, "Token should not be expired");
    }
}
