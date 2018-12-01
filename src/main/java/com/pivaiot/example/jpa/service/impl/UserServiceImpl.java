package com.pivaiot.example.jpa.service.impl;

import com.pivaiot.common.lang.util.BeanUtil;
import com.pivaiot.example.jpa.service.data.User;
import com.pivaiot.example.jpa.service.impl.entity.UserEntity;
import com.pivaiot.example.jpa.service.impl.repository.UserRepository;
import com.pivaiot.example.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).map(UserEntity::toData).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll().stream().map(UserEntity::toData).collect(Collectors.toList());
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = user.getId() == null ? new UserEntity() : userRepository.findById(user.getId()).orElse(new UserEntity());
        BeanUtil.copyProperties(user, userEntity);
        userEntity = userRepository.save(userEntity);
        return userEntity.toData();
    }
}
