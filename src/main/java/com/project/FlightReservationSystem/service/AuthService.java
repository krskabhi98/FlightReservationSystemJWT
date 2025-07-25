package com.project.FlightReservationSystem.service;


import com.project.FlightReservationSystem.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final Map<String, String> users = new HashMap<>();

    public boolean register(User user) {
        if (users.containsKey(user.getUsername())) {
            return false;
        }
        users.put(user.getUsername(), user.getPassword());
        return true;
    }

    public boolean authenticate(User user) {
        return users.containsKey(user.getUsername()) &&
                users.get(user.getUsername()).equals(user.getPassword());
    }
}

