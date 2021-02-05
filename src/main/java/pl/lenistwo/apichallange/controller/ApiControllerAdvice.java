package pl.lenistwo.apichallange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.lenistwo.apichallange.exception.CurrencyNotFoundException;
import pl.lenistwo.apichallange.exception.ProductNotFoundException;
import pl.lenistwo.apichallange.model.response.ErrorResponse;

@ControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler({ProductNotFoundException.class, CurrencyNotFoundException.class})
    public ErrorResponse handleProductNotFound(Exception e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }
}
