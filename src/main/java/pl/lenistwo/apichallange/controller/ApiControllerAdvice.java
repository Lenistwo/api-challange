package pl.lenistwo.apichallange.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import pl.lenistwo.apichallange.exception.CurrencyAlreadyExistsException;
import pl.lenistwo.apichallange.exception.CurrencyNotFoundException;
import pl.lenistwo.apichallange.exception.ProductNotFoundException;
import pl.lenistwo.apichallange.model.response.ErrorResponse;

@RestController
@ControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler({ProductNotFoundException.class, CurrencyNotFoundException.class})
    public ErrorResponse handleNotFoundException(Exception e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

    @ExceptionHandler({CurrencyAlreadyExistsException.class})
    public ErrorResponse handleConflictException(Exception e) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage());
    }
}
