package com.tag.springbootmybatis.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户实体",description = "封装用户信息")
public class User {

    @ApiModelProperty(value = "用户Id",name = "uId",dataType = "int",required = true,example = "1",hidden = true)
    private int uId;
    @ApiModelProperty(value = "用户名",name = "uName",dataType = "String",required = true,example = "李白")
    private String uName;
    @ApiModelProperty(value = "用户年龄",name = "uAge",dataType = "int",required = true,example = "18")
    private int uAge;
    @ApiModelProperty(value = "用户电话",name = "uTell",dataType = "String",required = true,example = "15677862349")
    private String uTell;

}
