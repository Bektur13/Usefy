package com.bektur.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoint {

    @GetMapping("/service")
    public String sayHello() {
        return "Hello World!";
    }
}
