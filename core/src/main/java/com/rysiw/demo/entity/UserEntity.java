package com.rysiw.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
    private String phone;
    private String email;

}
