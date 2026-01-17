package com.demo.project.minimart.service;

import com.demo.project.minimart.interfaces.UserInterface;
import com.demo.project.minimart.interfaces.UserRepository;
import com.demo.project.minimart.model.User;

import com.demo.project.minimart.model.UserResponse;
import com.demo.project.minimart.util.UserMapping;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserService implements UserInterface {
    private final UserRepository userRepository;
    private final Logger log = getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<?> addUser(User user) {
        if (ObjectUtils.isEmpty(user)) {
            log.error("user input is missing");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        generateId(user);
        userRepository.insert(user);
        log.info("User: {}", user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> patchUser(String id, User userPatch) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            if (userPatch.getName() != null) {
                existingUser.setName(userPatch.getName());
            }
            if (userPatch.getEmail() != null) {
                existingUser.setEmail(userPatch.getEmail());
            }
            if (userPatch.getRole() != null) {
                existingUser.setRole(userPatch.getRole());
            }
            if (userPatch.getAddress() != null) {
                existingUser.setAddress(userPatch.getAddress());
            }
            if (userPatch.getAge() != null) {
                existingUser.setAge(userPatch.getAge());
            }
            if (userPatch.getStatus() != null) {
                existingUser.setStatus(userPatch.getStatus());
            }
            if (userPatch.getKeycloakId() != null){
                existingUser.setKeycloakId(userPatch.getKeycloakId());
            }
            userRepository.save(existingUser);
            log.info("Patched user with id: {}", id);
            return new ResponseEntity<>(existingUser, HttpStatus.OK);
        } else {
            log.error("User not found with id: {}", id);
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> deleteUser(String id) {
        userRepository.deleteById(id);
        log.info("deleted user with id: {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUserById(String id) {
        var ret = userRepository.findById(id);
        if (ret.isPresent()) {
            return new ResponseEntity<>(ret.get(), HttpStatus.OK);
        } else {
            log.error("invalid id, user not found");
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllUsers() {
        var ret = userRepository.findAll();
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @Override
    public UserResponse findOrCreateUserByKeycloakId(Jwt jwt) {
        String keycloakId = jwt.getSubject();
        String email = jwt.getClaim("email");
        String username = jwt.getClaimAsString("preferred_username");
        Optional<User> ret = userRepository.findByKeycloakId(keycloakId);
        if (ret.isEmpty()) {
            Optional<User> userByEmail = userRepository.findByEmail(email);
            if (userByEmail.isPresent()) {
                return UserMapping.toUserResponse(userByEmail.get());
            }
        }
        User user = new User();
        generateId(user);
        user.setKeycloakId(keycloakId);
        user.setEmail(email);
        user.setName(username);
        user.setRole(User.Role.USER);
        return UserMapping.toUserResponse(userRepository.save(user));
    }


    private void generateId(User user) {
        var users = userRepository.findAll();
        var lastUser = getLastUser(users);
        if (!ObjectUtils.isEmpty(lastUser)) {
            var newUserId = Integer.parseInt(lastUser.getId()) + 1;
            user.setId(String.valueOf(newUserId));
            log.info("generating new user id {}", newUserId);
        } else {
            user.setId("1");
        }
    }

    private User getLastUser(List<User> users) {
        return users.isEmpty() ? null : users.get(users.size() - 1);
    }
}
