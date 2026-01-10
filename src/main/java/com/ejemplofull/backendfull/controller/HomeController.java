package com.ejemplofull.backendfull.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {
    
    @GetMapping("/hola")
    public String home() {
        return "Hola controller";
    }
    
}
