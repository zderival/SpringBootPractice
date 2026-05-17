package com.zderival.springbootpractice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// @ControllerAdvice - Globally talks to all controllers in your program and tells the controllers
// what to do in specific situation

/* In this case, GlobalExceptionHandler is a class that tells your controllers what to output
 in case of an exception needing to be thrown*/

@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler - tells Spring how to respond when a specific exception happens.

    /* In this case, it's giving the instructions on how to respond when
     SoftwareEngineerNotFoundException is thrown */

    @ExceptionHandler(SoftwareEngineerNotFoundException.class)
    // The method converts a Java exception into an HTTP response that Spring sends back to the client.
    public ResponseEntity<String> handleNotFoundException(SoftwareEngineerNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
