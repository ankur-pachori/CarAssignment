package com.example.assignment.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(FieldsEmptyException.class)
    public ResponseEntity<?> handleFieldsEmptyException(FieldsEmptyException exception, WebRequest request){
//        FieldsEmptyException exceptionDetails  = new FieldsEmptyException(exception.getErrorMessage(),exception.getErrorCode());
//        return new ResponseEntity(exceptionDetails, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>(exception.getErrorMessage(),HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(CarNotFound.class)
    public ResponseEntity<?> handleCarNotFound(CarNotFound exception, WebRequest request){
//        FieldsEmptyException exceptionDetails  = new FieldsEmptyException(exception.getErrorMessage(),exception.getErrorCode());
//        return new ResponseEntity(exceptionDetails, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<String>(exception.getErrorMessage(),HttpStatus.BAD_REQUEST);
    }
}
