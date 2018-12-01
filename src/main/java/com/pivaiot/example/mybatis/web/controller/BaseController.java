package com.pivaiot.example.mybatis.web.controller;

import com.pivaiot.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    @Autowired
    protected UserService userService;

}
