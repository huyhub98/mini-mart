package com.demo.project.minimart.interfaces;

import com.demo.project.minimart.model.User;
import com.demo.project.minimart.model.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

public interface UserInterface {

    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> patchUser(String id, User user);

    ResponseEntity<?> deleteUser(String id);

    ResponseEntity<?> getUserById(String id);

    ResponseEntity<?> getAllUsers();

    UserResponse findOrCreateUserByKeycloakId(Jwt jwt);

}
