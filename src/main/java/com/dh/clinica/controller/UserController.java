package com.dh.clinica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String user(){
        return "<h1> Hola User! </h1>";
    }

    @GetMapping("/admin")
    public String admin(){
        return "<h1> Hola Admin! </h1>";
    }
}
