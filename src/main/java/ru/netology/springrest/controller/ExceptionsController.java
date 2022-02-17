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

    /**
     * Исключитель ситуации с неверными данными авторизации.
     * @param ic возникшее исключение типа {@code InvalidCredentials}.
     * @return  квалифицированную для http-ответа сущность.
     */
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentials ic) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ic.getLocalizedMessage());
    }

    /**
     * Исключитель ситуации с ненайденным юзером.
     * @param ua возникшее исключение типа {@code UnauthorizedUser}.
     * @return  квалифицированную для http-ответа сущность.
     */
    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUserHandler(UnauthorizedUser ua) {
        System.out.println(ua.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ua.getLocalizedMessage());
    }

    /**
     * Исключитель ситуации с неверной структурой запроса.
     * @param msrp возникшее исключение типа {@code MissingServletRequestParameterException}.
     * @return  квалифицированную для http-ответа сущность.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> missingServletRequestParameterHandler(MissingServletRequestParameterException msrp){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(msrp.getLocalizedMessage());
    }

}
