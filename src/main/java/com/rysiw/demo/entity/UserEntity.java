package com.rysiw.demo.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;

}
