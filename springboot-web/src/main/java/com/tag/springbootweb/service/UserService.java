package com.tag.springbootweb.service;

import com.tag.springbootmybatis.beans.User;
import com.tag.springbootmybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tag
 * @date 2018/11/5 17:53
 * @description
 */
@Slf4j
@Service("user")
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> getAllUser() {
        return this.userMapper.selectAllUser();
    }

    public User getUserById(int id) {
        return this.userMapper.selectUserById(id);
    }

    public void saveUser() {
        User user = new User();
        user.setUName("马七");
        user.setUAge(21);
        user.setUTell("18720987786");
        log.info(""+user);
        this.userMapper.saveUser(user);
        log.info(""+user);
    }

}
