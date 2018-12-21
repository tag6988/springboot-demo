package com.tag.springbootmybatis.mapper.test;

import com.tag.springbootmybatis.beans.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author tag
 * @date 2018/11/5 17:39
 * @description
 */
@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> selectAllUser();

    @Select("select * from user where u_id = #{u_id}")
    User selectUserById(int u_id);

    @Insert("insert into user(u_id,u_name,u_age,u_tell) values (#{uId}, " +
            "#{uName}, #{uAge}, #{uTell})")
    @Options(useGeneratedKeys = true, keyColumn = "u_id", keyProperty = "uId")
    void saveUser(User user);

}
