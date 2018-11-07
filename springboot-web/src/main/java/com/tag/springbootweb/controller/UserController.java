package com.tag.springbootweb.controller;

import com.tag.springbootmybatis.beans.User;
import com.tag.springbootweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tag
 * @date 2018/11/5 17:36
 * @description
 */
@RestController
@RequestMapping("/userHome")
public class UserController {

    @Autowired
    @Qualifier("user")
    private UserService userService;

    @GetMapping("users")
    public List<User> getAllUser(){
        List<User> users = this.userService.getAllUser();
        System.out.println(users);
        return users;
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable("id") int id){
        User user = this.userService.getUserById(id);
        System.out.println(user);
        return user;
    }

    @GetMapping("saveUser")
    public void saveUser(){
        this.userService.saveUser();
    }

}
