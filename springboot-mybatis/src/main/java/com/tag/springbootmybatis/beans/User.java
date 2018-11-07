package com.tag.springbootmybatis.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author tag
 * @date 2018/11/5 17:40
 * @description
 */
@Data
@NoArgsConstructor          //空参构造器
@AllArgsConstructor         //全参构造器
@Accessors(chain=true)      //链式风格访问
public class User {

    private int uId;
    private String uName;
    private int uAge;
    private String uTell;

}
