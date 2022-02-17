package ru.netology.springrest.exception;

/**
 * Исключитель ситуации с недействительным удостоверением.
 */
public class InvalidCredentials extends RuntimeException {
    public InvalidCredentials(String msg) {
        super(msg);
    }
}