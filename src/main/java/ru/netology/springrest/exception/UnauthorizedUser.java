package ru.netology.springrest.exception;

/**
 * Исключитель ситуации с неавторизованным юзером.
 */
public class UnauthorizedUser extends RuntimeException {
    public UnauthorizedUser(String msg) {
        super(msg);
    }
}