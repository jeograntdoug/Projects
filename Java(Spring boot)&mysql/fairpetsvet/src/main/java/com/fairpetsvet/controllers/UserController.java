package com.fairpetsvet.controllers;

import com.fairpetsvet.models.User;
import com.fairpetsvet.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserRepository repository;

    @PostMapping("/user")
    ModelAndView create(@RequestParam String email,
                        @RequestParam String password,
                        @RequestParam String confirm){

        ModelAndView mv = new ModelAndView();
        if(!email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")){
            mv.addObject("errorEmail","Invalid Email.");
        }

        if(!password.matches("[a-zA-Z0-9]{8,20}")){
            mv.addObject("errorPassword","Invalid Password");
        }

        if(!password.equals(confirm)){
            mv.addObject("errorConfirm","Password Doesn't Match.");
        }

        if(mv.isEmpty()) {
            mv.setViewName("index");
            User user = repository.save(new User(email,password));
        }else {
            mv.setViewName("signup");
        }
        return mv;
    }
}
