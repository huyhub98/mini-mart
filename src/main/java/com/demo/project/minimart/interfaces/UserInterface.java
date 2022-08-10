package com.demo.project.minimart.interfaces;

import com.demo.project.minimart.model.User;
import org.springframework.http.ResponseEntity;

public interface UserInterface {

    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> patchUser(String id, User user);

    ResponseEntity<?> deleteUser(String id);

    ResponseEntity<?> getUserById(String id);

    ResponseEntity<?> getAllUsers();

}
