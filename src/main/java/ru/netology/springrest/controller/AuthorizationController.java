package ru.netology.springrest.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.springrest.dto.User;
import ru.netology.springrest.model.Authorities;
import ru.netology.springrest.service.AuthorizationService;

import javax.validation.Valid;
import java.util.List;

/**
 * Обработчик запросов авторизации.
 */
@RestController
@Validated
public class AuthorizationController {
    /**
     * Служба авторизации, используемая приложением.
     */
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    /**
     * Возвращает в ответ на обращение к '/authorize' с юзер-объектом в запросе
     * список разрешений для указанного юзера, как он предоставляется службой.
     * @param user     запрошенный юзер-объект.
     * @return  список разрешений, как он возвращается службой.
     */
    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid User user) {
        return service.getAuthorities(user.getName(), user.getPassword());
    }

}