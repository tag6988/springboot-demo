package com.tag.springbootshiro.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author tag
 * @date 2018/11/6 17:20
 * @description
 */
@Data
@NoArgsConstructor          //空参构造器
@AllArgsConstructor         //全参构造器
@Accessors(chain=true)      //链式风格访问
public class UserInfo {

    private String username;
    private String password;

}
