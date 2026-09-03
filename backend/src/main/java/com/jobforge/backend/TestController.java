package com.jobforge.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
    
    //Test endpoint to check if the backend is working
    @GetMapping("/")
    public String helloWorld() {       
        return "Hello World!";
    }        
}
