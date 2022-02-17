package ru.netology.springrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.springrest.model.Authorities;
import ru.netology.springrest.service.AuthorizationService;

import java.util.List;

/**
 * Обработчик запросов авторизации.
 */
@RestController
public class AuthorizationController {
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    /**
     * Возвращает в ответ на обращение к '/authorize' с именем и паролем в запросе
     * список разрешений для укзанного юзера, как он предоставляется службой.
     * @param user     запрошенное имя юзера.
     * @param password пароль для указанного юзера.
     * @return  список разрешений, как он возвращается службой.
     */
    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

}