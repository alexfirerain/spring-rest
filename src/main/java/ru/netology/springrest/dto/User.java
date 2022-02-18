package ru.netology.springrest.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Транзитная структура для передачи данных о юзере: имя и пароль.
 */
@Validated
public class User {
    @NotBlank
    @Size(min = 2)
    private String name;

    @NotBlank
    @Size(min = 5)
    private String password;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
