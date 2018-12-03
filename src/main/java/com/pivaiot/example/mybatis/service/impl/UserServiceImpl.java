package com.pivaiot.example.mybatis.service.impl;

import com.pivaiot.common.lang.util.BeanUtil;
import com.pivaiot.example.mybatis.service.data.User;
import com.pivaiot.example.mybatis.service.impl.entity.UserEntity;
import com.pivaiot.example.mybatis.service.UserService;
import com.pivaiot.example.mybatis.service.impl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.findById(id).map(UserEntity::toData).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userMapper.findAll().stream().map(UserEntity::toData).collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = user.getId() == null ? new UserEntity() : userMapper.findById(user.getId()).orElse(new UserEntity());
        BeanUtil.copyProperties(user, userEntity);
        userEntity = userMapper.save(userEntity);
        return userEntity.toData();
    }
}
