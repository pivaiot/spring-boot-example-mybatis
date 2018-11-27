package com.pivaiot.starter.project.service;

import com.pivaiot.starter.project.service.data.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> findAllUsers();

    User save(User user);

}
