package com.demo.project.minimart.model;

import lombok.Data;

@Data
public class UserResponse {
    // ID của user trong hệ thống mini-mart (MongoDB _id)
    private String id;
    private String name;
    private Integer age;
    private String email;
    private String address;

    //(admin, user, goldUser, diamondUser)
    private String role;
    private String status; // ACTIVE, INACTIVE, BLOCKED
}
