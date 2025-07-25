package com.project.FlightReservationSystem.controller;

import com.project.FlightReservationSystem.model.User;
import com.project.FlightReservationSystem.security.JwtUtil;
import com.project.FlightReservationSystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.project.FlightReservationSystem.model.UriConst.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final String REGISTERED_USER="User registered";
    private final String USER_ALREADY_EXIT="Username already exists";
    private final String INVALID_CREDENTIAL="Invalid credentials";

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping(REGISTER_NEW_USER)
    public ResponseEntity<?> register(@RequestBody User user) {
        return authService.register(user)
                ? ResponseEntity.ok(REGISTERED_USER)
                : ResponseEntity.badRequest().body(USER_ALREADY_EXIT);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<?> login(@RequestBody User user) {
        if (authService.authenticate(user)) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body(INVALID_CREDENTIAL);
    }
}

