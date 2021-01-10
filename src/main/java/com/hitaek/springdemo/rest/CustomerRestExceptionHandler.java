package com.hitaek.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {

    // add exception handling code
    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException ex){
        // create a EmployeeErrorResponse

        CustomerErrorResponse error = new CustomerErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<CustomerErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    // another exception for catching any exception
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception ex){
        // create a EmployeeErrorResponse

        CustomerErrorResponse error = new CustomerErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        // return ResponseEntity

        return new ResponseEntity<CustomerErrorResponse>(error, HttpStatus.BAD_REQUEST);

    }
}
