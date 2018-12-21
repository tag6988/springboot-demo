package com.tag.springbootweb.controller;

import com.github.pagehelper.PageInfo;
import com.tag.springbootmybatis.beans.User;
import com.tag.springbootweb.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author tag
 * @date 2018/11/5 17:36
 * @description
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/userHome")
public class UserController {

    @Autowired
    @Qualifier("user")
    private UserService userService;

    @ApiOperation("获取所有用户")
    @GetMapping("index")
    public String goToIndex() {
        return "springboot-1.9";
    }

    @ApiOperation("获取所有用户")
    @GetMapping("users")
    public List<User> getAllUser() {
        List<User> users = this.userService.getAllUser();
        return users;
    }

    @ApiOperation("获取所有用户")
    @GetMapping("users/{page}/{size}")
    public PageInfo<User> getAllUserByPage(@PathVariable("page") int page,
                                           @PathVariable("size") int size) {
        PageInfo<User> users = this.userService.getAllUserByPage(page, size);
        return users;
    }


    @ApiOperation(value = "根据用户ID获取对应用户", notes = "用于提示内容")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户id",
            dataType = "int", paramType = "path", example = "1", required =
            true, defaultValue = "2"),})
    @GetMapping("user/{id}")
    public User getUserById(@ApiParam(name = "id", value = "用户id", required =
            true) @PathVariable(value = "id", required = true) int id) {
        User user = this.userService.getUserById(id);
        System.out.println(user);
        return user;
    }

    @ApiOperation(value = "用户实体", notes = "用于提示内容")
    @ApiImplicitParams({@ApiImplicitParam(name = "user", value = "用户实体",
            dataType = "User", paramType = "path", example = "user",
            required = true, defaultValue = "2"),})
    @GetMapping("saveUser")
    public void saveUser(@ApiParam(name = "user", value = "用户实体", required =
            true) @PathParam(value = "user") User user) {
        this.userService.saveUser();
    }

}
