package com.logistics.service;

import com.logistics.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private static final Map<String, User> USER_DB = new HashMap<>();

    static {
        USER_DB.put("1", new User("1", "Alice", 25));
        USER_DB.put("2", new User("2", "Bob", 30));
    }

    @Cacheable(value = "users", key = "#id")
    public User getUserById(String id) {
        System.out.println("Fetching from DB...");
        return USER_DB.get(id); // simulate DB call
    }

    @CacheEvict(value = "users", key = "#id")
    public void evictUserFromCache(String id) {
        System.out.println("Cache evicted for ID: " + id);
    }
}
