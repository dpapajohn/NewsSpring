package com.papajohn.NewsSpring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    String helloThere () {
        return "hello world";
    }
    
}
