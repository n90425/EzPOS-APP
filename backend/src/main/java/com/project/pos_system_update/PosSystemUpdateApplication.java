package com.project.pos_system_update;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping("/api")
public class PosSystemUpdateApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosSystemUpdateApplication.class, args);
    }

}
