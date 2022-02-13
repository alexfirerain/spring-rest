package ru.netology.springrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.netology.springrest.dto.User;
import ru.netology.springrest.exception.InvalidCredentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.netology.springrest.repository.Authorities.*;

@Repository
public class UserRepository {
    public static final User DEMO_USER = new User("DemoUser", "password", List.of(READ, WRITE));
    private final Map<String, User> users;

    public UserRepository(Map<String, User> users) {
        this.users = users;
    }

    @Autowired
    public UserRepository() {
        users = new ConcurrentHashMap<>();
        addUser(DEMO_USER);
    }

    /**
     * Возвращает список разрешений для юзера, если такой юзер есть в базе, а
     * переданный пароль подошёл.
     * Если имени юзера нет в базе, возвращает пустой список.
     * Если юзер есть в базе, но переданный пароль не подходит, выбрасывает InvalidCredentials.
     * @param user     имя запрашиваемого юзера.
     * @param password  переданный пароль для запрашиваемого юзера.
     * @return  список разрешений для авторизованного юзера.
     * @throws  InvalidCredentials  если переданный пароль не соответствует запрошенному имени.
     */
    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> authorities = new ArrayList<>(Authorities.values().length);
        User userEntity = users.get(user);
        if (userEntity != null) {
            if (!userEntity.wordPasses(password))
                throw new InvalidCredentials("Неверный пароль.");
            authorities.addAll(userEntity.getAuthorities());
        }
        return authorities;
    }
    
    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
    
    public void deleteUser(String username) {
        users.remove(username);
    }
}