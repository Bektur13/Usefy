package com.bektur.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component("controllerEndpoint")
public class Endpoint {

    @GetMapping("/controller")
    public String sayHello() {
        return "Hello World!";
    }
}
