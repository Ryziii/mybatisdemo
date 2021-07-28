package com.rysiw.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rysiw.demo.common.ResultDTO;
import com.rysiw.demo.dao.UserMapper;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> getAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<UserEntity> pageInfo = new PageInfo<>(userMapper.getAll());
        Long total = pageInfo.getTotal();
        List<UserEntity> userEntityList = pageInfo.getList();
        total = total == 0 ? userEntityList.size() : total;
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", userEntityList);
        log.info(map);
        return map;
    }

    @Override
    public UserEntity getUserById(Long id) {
        UserEntity user = userMapper.getUserById(id);
        log.info("通过id：{}查找用户，查找结果：{}",id,user);
        return user;
    }

    @Override
    public ResultDTO insertUser(UserEntity userEntity) {
        try {
            log.info("插入用户{}", userEntity);
            Long id = userEntity.getId();
            if (id != null) {
                UserEntity user = getUserById(id);
                if (user != null) {
                    log.info("插入用户失败，用户已存在");
                    return ResultDTO.builder().code(200).msg("创建用户失败，用户已存在").build();
                }
            }
            userMapper.insert(userEntity);
            return ResultDTO.builder().code(200).msg("创建用户成功").data(userEntity.getId()).build();
        }catch (Exception e){
            log.info("插入用户失败,插入数据:{}",userEntity);
            return ResultDTO.builder().code(200).msg("创建用户失败").data(e.getLocalizedMessage()).build();
        }
    }

    @Override
    public ResultDTO update(UserEntity userEntity) {
        Long res = userMapper.update(userEntity);
        return userMapper.update(userEntity) == 0 ?
                ResultDTO.builder().code(200).msg("更新失败，无影响行数").build() :
                ResultDTO.builder().code(200).msg("更新成功，影响行数：" + res).build();
    }

    @Override
    public ResultDTO deleteById(Long id) {
        try {
            return userMapper.delete(id) == 0 ? ResultDTO.builder().code(200).msg("删除失败，无影响行数").build()
                    : ResultDTO.builder().code(200).msg("删除成功").build();
        }catch (Exception e){
            return ResultDTO.builder().code(200).msg("删除失败").data(e.getLocalizedMessage()).build();
        }
    }
}
