package com.edstem.taxibookingandbillingsystem.exception;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException() {
        super("Invalid Login");
    }
}
