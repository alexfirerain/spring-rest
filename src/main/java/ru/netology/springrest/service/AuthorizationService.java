package ru.netology.springrest.service;

import org.springframework.stereotype.Service;
import ru.netology.springrest.exception.InvalidCredentials;
import ru.netology.springrest.exception.UnauthorizedUser;
import ru.netology.springrest.repository.Authorities;
import ru.netology.springrest.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {
    UserRepository userRepository;

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("Имя юзера или пароль: пусто");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Незнакомый юзер: " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}