package com.rysiw.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEntity {
    @TableId
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String email;

}
