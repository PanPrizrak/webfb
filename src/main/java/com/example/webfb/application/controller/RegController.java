package com.example.webfb.application.controller;

import com.example.webfb.application.entity.Role;
import com.example.webfb.application.entity.User;
import com.example.webfb.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegController {//RegController
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/reg")
    public String reg(Map<String,Object> model){
        model.put("message", "Add new user!");
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(User user, Map<String,Object> model){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "reg";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
       // return "registration";
        }
}
