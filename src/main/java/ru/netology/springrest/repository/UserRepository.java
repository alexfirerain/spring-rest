package ru.netology.springrest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.netology.springrest.model.UserEntity;
import ru.netology.springrest.exception.InvalidCredentials;
import ru.netology.springrest.model.Authorities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ru.netology.springrest.model.Authorities.*;

/**
 * Южерохранилище.
 */
@Repository
public class UserRepository {
    private static final UserEntity DEMO_USER = new UserEntity("DemoUser", "password", List.of(READ, WRITE));
    private final Map<String, UserEntity> users;

    public UserRepository(Map<String, UserEntity> users) {
        this.users = users;
    }

    @Autowired
    public UserRepository() {
        users = new ConcurrentHashMap<>();
        addUser(DEMO_USER);
    }

    /**
     * Возвращает список полномочий для юзера, если такой юзер есть в базе, а
     * переданный пароль подошёл.
     * Если имени юзера нет в базе, возвращает пустой список.
     * Если юзер есть в базе, но переданный пароль не подходит, выбрасывает InvalidCredentials.
     * @param user     имя запрашиваемого юзера.
     * @param password  переданный пароль для запрашиваемого юзера.
     * @return  список полномочий для авторизованного юзера.
     * @throws  InvalidCredentials  если переданный пароль не соответствует запрошенному имени.
     */
    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> authorities = new ArrayList<>(Authorities.values().length);
        UserEntity userEntity = users.get(user);
        if (userEntity != null) {
            if (!userEntity.wordPasses(password))
                throw new InvalidCredentials("Неверный пароль");
            authorities.addAll(userEntity.getAuthorities());
        }
        return authorities;
    }

    /**
     * Добавляет нового юзера в юзерохранилище (т.е. репозиторий).
     * @param user добавляемый юзер.
     */
    public void addUser(UserEntity user) {
        users.put(user.getUsername(), user);
    }

    /**
     * Удаляет указанного юзера из юзерохранилища (т.е. репозитория).
     * @param username имя удаляемого юзера.
     */
    public void deleteUser(String username) {
        users.remove(username);
    }
}