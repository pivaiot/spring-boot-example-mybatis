package com.pivaiot.example.jpa.web.converter;

import com.pivaiot.common.lang.converter.BaseConverter;
import com.pivaiot.example.jpa.service.data.User;
import com.pivaiot.example.jpa.web.model.UserModel;


public class UserConverter extends BaseConverter<User, UserModel> {
    public final static UserConverter INSTANCE = new UserConverter();
}
