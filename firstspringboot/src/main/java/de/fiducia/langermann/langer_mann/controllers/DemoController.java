package de.fiducia.langermann.langer_mann.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

//@Component
//    @Service
//    @Repository
//    @Controller
//        @RestController

@RestController
@RequestMapping("/v1/demo")
public class DemoController {

    @GetMapping("gruss")
    public String getString() {
        return "Hallo Rest";
    }

    @GetMapping("uuid")
    public String getUUID() {
        return UUID.randomUUID().toString();
    }
}
