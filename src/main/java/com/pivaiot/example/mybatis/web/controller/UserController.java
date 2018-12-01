package com.pivaiot.example.mybatis.web.controller;

import com.pivaiot.common.lang.http.ResponseJson;
import com.pivaiot.example.mybatis.service.data.User;
import com.pivaiot.example.mybatis.web.converter.UserConverter;
import com.pivaiot.example.mybatis.web.model.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pivaiot.common.lang.http.ResponseJson.ok;


@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @GetMapping("/{userId}")
    public ResponseJson<UserModel> getUserById(@PathVariable("userId") Long userId) {
        return ok(UserConverter.INSTANCE.toModel(userService.getUserById(userId)));
    }

    @GetMapping
    public ResponseJson<List<UserModel>> findAllUsers() {

        return ok(UserConverter.INSTANCE.toModelList(userService.findAllUsers()));

    }

    @PostMapping
    public ResponseJson<UserModel> createUser(@RequestBody UserModel userModel) {
        User user = UserConverter.INSTANCE.toData(userModel);
        return ok(UserConverter.INSTANCE.toModel(userService.save(user)));
    }

}
