package com.wonghengguan.maybank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final InMemoryUserDetailsManager userDetailsManager;

    public AuthController(InMemoryUserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetails> login(@RequestBody AuthRequest authRequest) {
        if (isValidCredentials(authRequest.getUsername(), authRequest.getPassword())) {
            UserDetails userDetails = userDetailsManager.loadUserByUsername(authRequest.getUsername());
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful");
    }

    private boolean isValidCredentials(String username, String password) {
        // hardcoded for assignment
        return "user".equals(username) && "password".equals(password);
    }

    @Data
    static class AuthRequest {
        private String username;
        private String password;
    }

}
