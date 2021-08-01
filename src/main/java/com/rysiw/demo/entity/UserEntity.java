package com.rysiw.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
public class UserEntity {
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
    private String phone;
    private String email;

}
