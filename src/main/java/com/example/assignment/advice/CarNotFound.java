package com.example.assignment.advice;

import lombok.Data;
import org.springframework.stereotype.Component;
@Component
@Data
public class CarNotFound extends RuntimeException {
    private String errorMessage;
    private String errorCode;
    public CarNotFound(String errorMessage, String errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public CarNotFound() {

    }
}
