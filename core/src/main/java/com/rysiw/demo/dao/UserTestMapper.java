package com.rysiw.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.entity.UserTestEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserTestMapper extends BaseMapper<UserTestEntity> {
}
