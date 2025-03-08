package org.example.productservice.advices;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//Extra layer after controller to handle exceptions,to give advice before sending the response to dispatcher servlet
//Applicable to all controllers
@ControllerAdvice
@RestController
public class ExceptionAdvices {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return e.getMessage() + "Something went wrong!";
    }
}
