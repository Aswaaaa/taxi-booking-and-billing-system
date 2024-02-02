package com.edstem.taxibookingandbillingsystem.contract.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateAccountRequest {
    private double accountBalance;
}
