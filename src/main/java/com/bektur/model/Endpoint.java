package com.bektur.model;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component("modelEndpoint")
public class Endpoint {

    @GetMapping("/model")
    public String sayHello() {
        return "Hello World!";
    }
}
