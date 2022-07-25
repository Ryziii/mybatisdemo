package com.rysiw.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rysiw.demo.common.constant.RespCode;
import com.rysiw.demo.common.dto.ResultDTO;
import com.rysiw.demo.dao.UserMapper;
import com.rysiw.demo.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends ServiceImpl<UserMapper, UserEntity> {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UserEntity> pageInfo = new PageInfo<>(userMapper.getAll());
        Long total = pageInfo.getTotal();
        List<UserEntity> userEntityList = pageInfo.getList();
        logger.info(userEntityList.toString());
        total = total == 0 ? userEntityList.size() : total;
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", userEntityList);
        return map;
    }

    
    public UserEntity getUserById(Long id) {
        UserEntity user = userMapper.getUserById(id);
        logger.info("通过id：{}查找用户，查找结果：{}",id,user);
        return user;
    }

    
    public ResultDTO insertUser(UserEntity userEntity) {
        try {
            logger.info("插入用户{}", userEntity);
            Long id = userEntity.getId();
            if (id != null) {
                UserEntity user = getUserById(id);
                if (user != null) {
                    logger.info("插入用户失败，用户已存在");
                    return ResultDTO.builder().
                            code(RespCode.SUCCESS.getCode()).
                            msg("创建用户失败，用户已存在").
                            build();
                }
            }
            userMapper.insert(userEntity);
            return ResultDTO.builder().
                    code(RespCode.SUCCESS.getCode()).
                    msg("创建用户成功").
                    data(userEntity).
                    build();
        }catch (Exception e){
            logger.info("插入用户失败,插入数据:{}",userEntity);
            return ResultDTO.builder().
                    code(RespCode.SUCCESS.getCode()).
                    msg("创建用户失败").
                    data(e.getLocalizedMessage()).
                    build();
        }
    }

    
    public Boolean update(UserEntity userEntity) {
        Long res = userMapper.update(userEntity);
        return userMapper.update(userEntity) != 0;
    }

    
    public ResultDTO deleteById(Long id) {
        try {
            return userMapper.delete(id) == 0 ? ResultDTO.builder().code(RespCode.SUCCESS.getCode()).msg("删除失败，无影响行数").build()
                    : ResultDTO.builder().code(RespCode.SUCCESS.getCode()).msg("删除成功").build();
        }catch (Exception e){
            return ResultDTO.builder().code(RespCode.SUCCESS.getCode()).msg("删除失败").data(e.getLocalizedMessage()).build();
        }
    }

    
    public UserEntity getUserByUsername(String username) {
        try{
            return userMapper.getUserByUsername(username);
        }catch (Exception e){
            logger.error("查询用户失败，用户名：{}", username);
            return null;
        }
    }

}
