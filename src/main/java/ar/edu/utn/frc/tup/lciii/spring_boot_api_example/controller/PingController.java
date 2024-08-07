package ar.edu.utn.frc.tup.lciii.spring_boot_api_example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }
}
