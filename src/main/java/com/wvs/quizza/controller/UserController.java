package com.wvs.quizza.controller;

import com.wvs.quizza.dto.User;
import com.wvs.quizza.exceptions.UsernameNotUniqueException;
import com.wvs.quizza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        if (isUsernameUnique(user.getUsername())) {
            return repo.save(user);
        } else
            throw new UsernameNotUniqueException();
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return repo.getOne(userId);
    }

    @GetMapping("/user")
    public List<User> getAllUser() {
        return repo.findAll();
    }

    @DeleteMapping("/user/{userId}")
    public void delete(@PathVariable Long userId) {
        //TODO: Wie bekommt man den Current User
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
