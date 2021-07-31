package com.example.register.controllor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class submitControllor {
    @Autowired
    private final UserRepository userRepository;

    public submitControllor(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping("/submitForm")
    public String add(UserInfo user) {
        userRepository.save(user);
        return "success";
    }

    @GetMapping("/getAllUsers")
    public List<UserInfo> getAllUsers() {
        return userRepository.findAll();
    }

}
