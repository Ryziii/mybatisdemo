package com.rysiw.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class UserTestEntity {

    @TableId
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
    private String phone;
    private String email;

}
