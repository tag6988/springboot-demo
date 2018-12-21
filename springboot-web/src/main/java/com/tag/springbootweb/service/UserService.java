package com.tag.springbootweb.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tag.springbootmybatis.beans.User;
import com.tag.springbootmybatis.mapper.test.UserMapper;
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

    /**
     * 使用pagehelper实现分页
     * @param page
     * @param pageSize
     * @return
     */
    public PageInfo<User> getAllUserByPage(int page,int pageSize) {
        //底层使用aop拦截sql进行分页拼接
        PageHelper.startPage(page,pageSize);
        //获得对应区间的数据
        List<User> list = this.userMapper.selectAllUser();
        //装载到page中
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        return pageInfo;
    }

}
