package com.tag.springbootshiro.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author tag
 * @date 2018/11/7 10:21
 * @description
 */
@Slf4j
public class ShiroRealm extends AuthenticatingRealm {
    /**
     * 用于判断登录信息的方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        //当前用户输入的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        log.info("username:" + name + " , " + "password:" + password);
        //数据库查询账号密码
        String realName = "tag";
        String realPassword = "1234";
        //加盐
        ByteSource byteSource = ByteSource.Util.bytes(realName);
        //加密
        SimpleHash md5Password = new SimpleHash("MD5", realPassword, byteSource, 10);
        //return new SimpleAuthenticationInfo(realName, md5Password, getName());
        //前端数据通过加盐加密处理
        return new SimpleAuthenticationInfo(realName, md5Password, byteSource, getName());
    }

    /**
     * 这里是用来返回用户权限的方法
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
