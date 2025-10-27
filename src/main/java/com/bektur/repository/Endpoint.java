package com.bektur.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component("repositoryEndpoint")
public class Endpoint {

    @GetMapping("/repository")
    public String sayHello() {
        return "Hello World!";
    }
}
