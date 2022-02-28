package ru.netology.springrest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JustController {
    @GetMapping("/just")
    public String justHallo() {
        return "Hello from just a controller!";
    }
}
