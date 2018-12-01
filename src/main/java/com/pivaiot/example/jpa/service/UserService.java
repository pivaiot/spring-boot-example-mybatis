package com.pivaiot.example.jpa.service;

import com.pivaiot.example.jpa.service.data.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> findAllUsers();

    User save(User user);

}
