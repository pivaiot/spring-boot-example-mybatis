package com.pivaiot.example.jpa.web.controller;

import com.pivaiot.example.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    @Autowired
    protected UserService userService;

}
