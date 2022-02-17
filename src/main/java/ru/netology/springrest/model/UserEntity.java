package ru.netology.springrest.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Представление юзера как структуры данных: имя, пароль, набор полномочий.
 */
@Validated
public class UserEntity {
    @NotBlank
    private String username;
    @NotBlank
    private String password; // должен быть ByteChar[]
    private List<Authorities> authorities;

    public UserEntity(String username, String password, List<Authorities> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        authorities = new ArrayList<>(Authorities.values().length);
    }

    public boolean wordPasses(String password) {
        return this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }

    public void setAuthority(Authorities authority) {
        if (!authorities.contains(authority))
            authorities.add(authority);
    }

    public void removeAuthority(Authorities authority) {
        authorities.remove(authority);
    }
}
