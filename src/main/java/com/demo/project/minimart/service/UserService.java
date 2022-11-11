package com.demo.project.minimart.service;

import com.demo.project.minimart.interfaces.UserInterface;
import com.demo.project.minimart.interfaces.UserRepository;
import com.demo.project.minimart.model.User;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Log4j
public class UserService implements UserInterface {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> addUser(User user) {
        if (ObjectUtils.isEmpty(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        generateId(user);
        userRepository.insert(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patchUser(String id, User userPatch) {
        var user = userRepository.findById(id);
        if (user.isPresent()) {
            userPatch.setId(user.get().getId());
            userRepository.save(userPatch);
        }
        return new ResponseEntity<>(userPatch, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUser(String id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(String id) {
        try {
            var ret = userRepository.findById(id);
            if (ret.isPresent()) {
                return new ResponseEntity<>(ret.get(), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return getUserById(id);
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        var ret = userRepository.findAll();
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    private void generateId(User user) {
        var users = userRepository.findAll();
        var lastUser = getLastUser(users);
        if (!ObjectUtils.isEmpty(lastUser)) {
            var newUserId = Integer.parseInt(lastUser.getId()) + 1;
            user.setId(String.valueOf(newUserId));
        }
    }

    private User getLastUser(List<User> users) {
        return users.isEmpty() ? null : users.get(users.size() - 1);
    }
}
