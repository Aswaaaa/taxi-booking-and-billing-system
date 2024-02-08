package com.edstem.taxibookingandbillingsystem.contract.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterResponse {
    private Long id;
    private String name;
    private String email;
}
