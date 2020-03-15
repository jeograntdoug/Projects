package com.fairpetsvet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    String home(){
        return "index";
    }

    @GetMapping("/staff")
    String staff(){
        return "staff";
    }

    @GetMapping("/services")
    String services(){
        return "services";
    }

    @GetMapping("reservation")
    String reservation(){
        return "reservation";
    }

    /*TODO : add notice page
    @GetMapping("/products")
    String products(){
        return "products";
    }
    */
}
