package ru.netology.springrest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.springrest.exception.InvalidCredentials;
import ru.netology.springrest.exception.UnauthorizedUser;

/**
 * Обработчик исключений.
 */
@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentials ic) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ic.getLocalizedMessage());
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUserHandler(UnauthorizedUser ua) {
        System.out.println(ua.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ua.getLocalizedMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> missingServletRequestParameterHandler(MissingServletRequestParameterException msrp){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(msrp.getLocalizedMessage());
    }

}
