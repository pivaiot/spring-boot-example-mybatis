package com.pivaiot.starter.project.web.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserModel {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("个人简介")
    private String intro;

}
