package com.rysiw.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;

}
