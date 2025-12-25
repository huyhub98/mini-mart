package com.demo.project.minimart.util;

import com.demo.project.minimart.model.User;
import com.demo.project.minimart.model.UserResponse;

public class UserMapping {
    public static UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setAge(user.getAge());
        userResponse.setEmail(user.getEmail());
        userResponse.setAddress(user.getAddress());
        userResponse.setRole(user.getRole().getRole());
        userResponse.setStatus(user.getStatus());
        return userResponse;
    }
}
