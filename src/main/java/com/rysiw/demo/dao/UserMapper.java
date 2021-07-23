package com.rysiw.demo.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rysiw.demo.entity.UserEntity;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Select("SELECT * FROM users")
    Page<UserEntity> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    UserEntity getUserById(Long id);

    @Insert("INSERT INTO users(username,password,phone,email)" +
            "VALUES(#{username}, #{password}, #{phone}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(UserEntity userEntity);

    @Update("UPDATE users SET username=#{username}, password=#{password}, phone=#{phone}, email=#{email}")
    void update(UserEntity user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void delete(Long id);
}
