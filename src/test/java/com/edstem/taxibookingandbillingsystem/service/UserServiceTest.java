package com.edstem.taxibookingandbillingsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.edstem.taxibookingandbillingsystem.configuration.JwtService;
import com.edstem.taxibookingandbillingsystem.contract.request.LoginRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.RegisterRequest;
import com.edstem.taxibookingandbillingsystem.contract.request.UpdateAccountRequest;
import com.edstem.taxibookingandbillingsystem.contract.response.LoginResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.RegisterResponse;
import com.edstem.taxibookingandbillingsystem.contract.response.UpdateAccountResponse;
import com.edstem.taxibookingandbillingsystem.exception.InvalidLoginException;
import com.edstem.taxibookingandbillingsystem.model.User;
import com.edstem.taxibookingandbillingsystem.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private ModelMapper modelMapper;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private JwtService jwtService;
    @Mock private AuthenticationManager authenticationManager;
    @InjectMocks private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegister() {
        RegisterRequest request = new RegisterRequest("name", "email", "password");
        User user =
                User.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build();
        RegisterResponse expectedResponse = new RegisterResponse(1L, "name", "email");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, RegisterResponse.class)).thenReturn(expectedResponse);

        RegisterResponse actualResponse = userService.register(request);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testLogin() {
        User user = new User(1L, "name", "email", "password", 0.0);
        LoginRequest request = new LoginRequest("email", "password");
        LoginResponse expectedResponse = new ModelMapper().map(request, LoginResponse.class);

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(!passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);

        LoginResponse actualResponse = userService.login(request);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testInvalidLoginException() {
        String expectedMessage = "Invalid Login";
        InvalidLoginException exception =
                assertThrows(
                        InvalidLoginException.class,
                        () -> {
                            throw new InvalidLoginException();
                        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testUpdateBalance() {

        Long id = 1L;
        UpdateAccountRequest request = new UpdateAccountRequest(50.0);
        User user = new User(1L, "name", "email@email.com", "password", 100.0);
        User updatedUser = new User(1L, "name", "email@email.com", "password", 150.0);
        UpdateAccountResponse expectedResponse = new UpdateAccountResponse(150.0);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        when(modelMapper.map(updatedUser, UpdateAccountResponse.class))
                .thenReturn(expectedResponse);

        UpdateAccountResponse actualResponse = userService.updateBalance(id, request);

        assertEquals(expectedResponse, actualResponse);
    }
}
