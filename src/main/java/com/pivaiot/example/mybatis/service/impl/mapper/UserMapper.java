package com.pivaiot.example.mybatis.service.impl.mapper;

import com.pivaiot.example.mybatis.service.impl.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserMapper {

    Optional<UserEntity> findById(Long id);

    List<UserEntity> findAll();

    UserEntity save(UserEntity userEntity);

}
