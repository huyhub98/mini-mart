package com.demo.project.minimart.service;

import com.demo.project.minimart.model.AuthRequest;
import com.demo.project.minimart.model.AuthResponse;
import com.demo.project.minimart.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final String adminUsername;
    private final String adminPassword;

    public AuthService(JwtUtil jwtUtil,
                       @Value("${user.admin.id}") String adminUsername,
                       @Value("${user.admin.password}") String adminPassword) {
        this.jwtUtil = jwtUtil;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    public ResponseEntity<?> authenticate(AuthRequest authRequest) {
        if (authRequest == null || authRequest.getUsername() == null || authRequest.getPassword() == null) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST);
        }

        if (!adminUsername.equals(authRequest.getUsername()) || !adminPassword.equals(authRequest.getPassword())) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(authRequest.getUsername());
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }
}
