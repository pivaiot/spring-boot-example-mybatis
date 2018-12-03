package com.pivaiot.example.mybatis.service.impl.entity;

import com.pivaiot.example.mybatis.common.db.BaseEntity;
import com.pivaiot.example.mybatis.service.data.User;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity<User> {

    private String username;

}
