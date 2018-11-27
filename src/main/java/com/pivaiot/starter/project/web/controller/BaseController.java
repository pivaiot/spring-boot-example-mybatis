package com.pivaiot.starter.project.web.controller;

import com.pivaiot.starter.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

    @Autowired
    protected UserService userService;

}
