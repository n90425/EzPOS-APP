package com.project.pos_system_update;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class test {
	
	@GetMapping("/test")
    public String hello() {
        return "Hello from Spring Boot!";
    }
	
}
