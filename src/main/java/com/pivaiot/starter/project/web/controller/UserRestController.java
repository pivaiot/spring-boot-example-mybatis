package com.pivaiot.starter.project.web.controller;

import com.pivaiot.common.lang.http.ResponseJson;
import com.pivaiot.starter.project.service.data.User;
import com.pivaiot.starter.project.web.converter.UserConverter;
import com.pivaiot.starter.project.web.model.UserModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.pivaiot.common.lang.http.ResponseJson.ok;


@RestController
@RequestMapping("/users")
public class UserRestController extends BaseController {

    @GetMapping("/{userId}")
    public ResponseJson<UserModel> getUserById(@PathVariable("userId") Long userId) {
        return ok(UserConverter.toData(userService.getUserById(userId)));
    }

    @GetMapping
    public ResponseJson<List<UserModel>> findAllUsers() {

        return ok(UserConverter.toDataList(userService.findAllUsers()));

    }

    @PostMapping
    public ResponseJson<UserModel> createUser(@RequestBody UserModel userModel) {
        User user = UserConverter.toData(userModel);
        return ok(UserConverter.toData(userService.save(user)));
    }

}
