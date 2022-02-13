package ru.netology.springrest.dto;

import ru.netology.springrest.repository.Authorities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password; // должен быть ByteChar[]
    private List<Authorities> authorities;

    public User(String username, String password, List<Authorities> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        authorities = new ArrayList<>();
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
