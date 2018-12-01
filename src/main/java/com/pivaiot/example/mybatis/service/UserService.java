package com.pivaiot.example.mybatis.service;

import com.pivaiot.example.mybatis.service.data.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> findAllUsers();

    User save(User user);

}
