package com.demo.project.minimart.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "age")
    private Integer age;

    @Field(name = "address")
    private String address;

    @Indexed(unique = true)
    @Field(name = "email")
    private String email;

    @Field(name = "role")
    private Role role;

    @Field(name = "status")
    private String status;

    @Indexed(unique = true)
    @Field(name = "keycloak_id")
    private String keycloakId;

    @Getter
    public enum Role {
        ADMIN("admin"),
        USER("user"),
        GOLD_USER("goldUser"),
        DIAMOND_USER("diamondUser");

        private final String value;

        Role(String value) {
            this.value = value;
        }
    }
}
