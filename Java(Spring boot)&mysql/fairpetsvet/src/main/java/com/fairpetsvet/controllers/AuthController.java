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

@Controller
public class AuthController {
    @Autowired
    UserRepository repository;

    @GetMapping("/signup")
    String signup(){ return "signup"; }

    @GetMapping("/login")
    String login(){
        return "login";
    }

    @PostMapping("/login")
    ModelAndView validateLogin(@RequestParam String email,@RequestParam String password){
        ModelAndView mv = new ModelAndView();
        User user = repository.findByEmailAndPassword(email,password);
        if(user == null){
            mv.addObject("error","User Email and Password don't matches.");
            mv.setViewName("/login");
        } else {
            mv.addObject("user",user);
            mv.setViewName("/");
        }
        return mv;
    }
}
