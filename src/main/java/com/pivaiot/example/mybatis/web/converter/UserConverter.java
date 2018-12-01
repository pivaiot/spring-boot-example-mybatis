package com.pivaiot.example.mybatis.web.converter;

import com.pivaiot.common.lang.converter.BaseConverter;
import com.pivaiot.example.mybatis.service.data.User;
import com.pivaiot.example.mybatis.web.model.UserModel;


public class UserConverter extends BaseConverter<User, UserModel> {
    public final static UserConverter INSTANCE = new UserConverter();
}
