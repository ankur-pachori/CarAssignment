package com.example.assignment.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class FieldsEmptyException extends RuntimeException {
    private String errorMessage;
    private String errorCode;
    private static final Long serrialVersionUID = 1l;

    public FieldsEmptyException(String errorMessage, String errorCode) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public FieldsEmptyException() {

    }
}
