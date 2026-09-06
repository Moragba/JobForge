package com.jobforge.backend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
//Frontend den Zugriff erlauben, da es auf einem anderen Port läuft
@CrossOrigin(origins = "http://localhost:8091")
public class TestController {
    
    //Test endpoint to check if the backend is working
    @GetMapping("/")
    public String helloWorld() {       
        return "Hello World!";
    }
    
    @GetMapping("/api/test")
    public Map<String, String> getMethodName() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Backend wurde erfolgreich angesprochen und das ist die Antwort");
        return response;
    }
    
}
