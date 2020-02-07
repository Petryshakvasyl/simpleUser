package com.example.demo.controller;

import com.example.demo.controller.error.BadRequestException;
import com.example.demo.controller.error.ResourceNotFoundException;
import com.example.demo.entity.User;
import com.example.demo.reository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user){
        if (user.getId() != null) {
            throw new BadRequestException();
        }
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Integer id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    @GetMapping("/users")
    public List<User> findUsers(){
        return userRepository.findAll();
    }

}
