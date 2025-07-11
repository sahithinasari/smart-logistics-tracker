package com.logistics.controller;

import com.logistics.entity.User;
import com.logistics.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        //return new User("1","A",46);
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}/evict")
    public void evictUser(@PathVariable String id) {
        userService.evictUserFromCache(id);
    }
}
