package com.demo.project.minimart.controller;


import com.demo.project.minimart.model.User;
import com.demo.project.minimart.model.UserResponse;
import com.demo.project.minimart.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minimart")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/addUser", consumes = "application/json")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PatchMapping(value = "/patchUser/{id}", consumes = "application/json")
    public ResponseEntity<?> patchUser(@PathVariable String id, @RequestBody User user) {
        return userService.patchUser(id, user);
    }

    @GetMapping(value = "/getUser/{id}", produces = "application/json")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/getAllUser")
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/users/me", produces = "application/json")
    public UserResponse getUserByKeycloakId(@AuthenticationPrincipal Jwt jwt) {
        return userService.getUserByKeycloakId(jwt);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}
