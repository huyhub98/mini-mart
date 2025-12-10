package com.demo.project.minimart.service;

import com.demo.project.minimart.interfaces.UserRepository;
import com.demo.project.minimart.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Mock
    private List<User> mockedUsers;

    User user = new User("1", "Huy", "25", "HN", "huy@gmail.com");
    User userIncrement = new User("2", "Huy", "25", "HN", "huy@gmail.com");

    @Test
    void addUser() {
        when(userRepository.insert(user)).thenReturn(user);
        var result = userService.addUser(user);
        assertEquals(user, result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void addUserAndIncrementId() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userRepository.insert(user)).thenReturn(userIncrement);
        var result = userService.addUser(user);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }

    @Test
    void addUserError() {
        var result = userService.addUser(null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void patchUser() {
        when(userRepository.findById("test")).thenReturn(Optional.ofNullable(user));
        var result = userService.patchUser("test", user);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void deleteUser() {
        var result = userService.deleteUser("test");
        assertNull(result.getBody());
    }

    @Test
    void getInvalidUser() {
        when(userRepository.findById("test")).thenReturn(null);
        var result = userService.getUserById("test");
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void getUserById() {
        when(userRepository.findById("test")).thenReturn(Optional.ofNullable(user));
        var result = userService.getUserById("test");
        assertEquals(user, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getAllUsers() {
        mockedUsers.add(user);
        when(userRepository.findAll()).thenReturn(mockedUsers);
        var result = userService.getAllUsers();
        assertEquals(mockedUsers, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}