package com.rysiw.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.rysiw.demo.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity>{

    @Select("SELECT * FROM users ")
    List<UserEntity> getAllBetweenTime();

    @Select("SELECT * FROM users")
    Page<UserEntity> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    UserEntity getUserById(Long id);

    @Insert("INSERT INTO users(username,password,phone,email)" +
            "VALUES(#{username}, #{password}, #{phone}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(UserEntity userEntity);

    @Update("UPDATE users SET username=#{username}, password=#{password}, phone=#{phone}, email=#{email} WHERE id=#{id}")
    Long update(UserEntity user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    Long delete(Long id);

    @Select("SELECT * FROM users WHERE username = #{username}")
    UserEntity getUserByUsername(String username);
}
