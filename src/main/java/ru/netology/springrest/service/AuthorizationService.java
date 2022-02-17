package ru.netology.springrest.service;

import org.springframework.stereotype.Service;
import ru.netology.springrest.exception.InvalidCredentials;
import ru.netology.springrest.exception.UnauthorizedUser;
import ru.netology.springrest.model.Authorities;
import ru.netology.springrest.repository.UserRepository;

import java.util.List;

/**
 * Служба авторизации.
 */
@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Возвращает список разрешений для данной пары логин-пароль, если такой
     * юзер зарегистрирован, а пароль корректен. В иных случаях бросает исключения.
     * @param user     имя юзера.
     * @param password пароль юзера.
     * @return  список разрешений для авторизованного пользователя, полученный от репозитория.
     * @throws InvalidCredentials если переданные имя и/или пароль пусты.
     * @throws UnauthorizedUser если репозиторий вернул пустой список.
     */
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

    /**
     * Сообщает, является ли переданная строка пустой.
     * @param str обсуждаемая строка.
     * @return  {@code истинно}, если строка ссылается на {@code ничто}, или ея длина равна 0.
     */
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Сообщает, является ли переданный список пустым.
     * @param str обсуждаемый список.
     * @return  {@code истинно}, если список ссылается на {@code ничто}, или его длина равна 0.
     */
    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}

