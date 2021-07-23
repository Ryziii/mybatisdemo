package com.rysiw.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rysiw.demo.dao.UserMapper;
import com.rysiw.demo.entity.UserEntity;
import com.rysiw.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

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
        return map;
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void insertUser(UserEntity userEntity) {
        userMapper.insert(userEntity);
    }

    @Override
    public void update(UserEntity userEntity) {
        userMapper.update(userEntity);
    }

    @Override
    public void deleteById(Long id) {
        userMapper.delete(id);
    }
}
