package com.demo.project.minimart.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "User")
public class User {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "age")
    private String age;

    @Field(name = "address")
    private String address;

    @Field(name = "email")
    private String email;
}
