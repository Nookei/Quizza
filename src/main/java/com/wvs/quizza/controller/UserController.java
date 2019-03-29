package com.wvs.quizza.controller;

import com.wvs.quizza.dto.User;
import com.wvs.quizza.exceptions.UsernameNotUniqueException;
import com.wvs.quizza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/auth")
    public void authUser() {
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        if (isUsernameUnique(user.getUsername())) {
            return repo.save(user);
        } else
            throw new UsernameNotUniqueException();
    }

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        for (User user:repo.findAll()) {
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        throw new UsernameNotFoundException(username + " not found");
    }

    @GetMapping("/user")
    public List<User> getAllUser() {
        return repo.findAll();
    }

    @DeleteMapping("/user/{userId}")
    public void delete(@PathVariable Long userId) {
        repo.deleteById(userId);
    }

    private boolean isUsernameUnique(String username) {
        for (User u : repo.findAll()) {
            if (u.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

}