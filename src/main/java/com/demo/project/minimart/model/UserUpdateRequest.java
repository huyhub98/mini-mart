package com.demo.project.minimart.model;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;
    private Integer age;
    private String address;
}
