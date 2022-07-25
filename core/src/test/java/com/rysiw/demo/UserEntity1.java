package com.rysiw.demo;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity1 {
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
    private String phone;
    private String email;

    private JSONArray jsonArray;

}
