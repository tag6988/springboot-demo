package com.tag.springbootshiro.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * @author tag
 * @date 2018/11/8 9:22
 * @description
 */
@Slf4j
public class ShiroRealm2 extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        /*UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());*/
        //加盐
        ByteSource byteSource = ByteSource.Util.bytes("xs");
        //加密
        SimpleHash md5Password = new SimpleHash("SHA", "1111", byteSource, 10);
        return new SimpleAuthenticationInfo("xs", md5Password, byteSource, getName());
    }
}
