package com.tag.springbootshiro.config;

import com.tag.springbootshiro.security.RoleRealm;
import com.tag.springbootshiro.security.ShiroRealm;
import com.tag.springbootshiro.security.ShiroRealm2;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tag
 * @date 2018/11/7 10:30
 * @description
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private ShiroRealm shiroRealm;

    /**
     * Shiro 的使用中只有一个SecurityManager,用于管理所有的安全操作
     * 这里生成SecurityManager 并配置Realm
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //单一realm验证
        //securityManager.setRealm(shiroRealm());
        //多realm验证
        //securityManager.setRealms(Arrays.asList(shiroRealm(), shiroRealm()));

        //第二种方式实现多realm注册，直接在bean中注入modularrealmauthenticator
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setRealms(Arrays.asList(shiroRealm, shiroRealm2()));
        securityManager.setAuthenticator(authenticator);
        /**
         * 认证策略,使用多realm认证时，认证策略需要在authenticator中声明
         * AllSuccessfulStrategy:全部需要认证
         * FirstSuccessfulStrategy:只要一个realm认证成功即可，返回第一个realm的验证信息
         * AtLeastOneSuccessfulStrategy:只要有一个realm认证成功即可，返回所有realm的验证信息
         */
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());

        //权限验证处理器
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer();
        authorizer.setRealms(Arrays.asList(roleRealm()));
        securityManager.setAuthorizer(authorizer);

        return securityManager;
    }

    /**
     * 配置权限验证器
     *
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashMatcher());
        return shiroRealm;
    }

    @Bean
    public ShiroRealm2 shiroRealm2() {
        ShiroRealm2 shiroRealm2 = new ShiroRealm2();
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName("SHA");
        hashMatcher.setHashIterations(10);
        shiroRealm2.setCredentialsMatcher(hashMatcher());
        return shiroRealm2;
    }

    public RoleRealm roleRealm() {
        return new RoleRealm();
    }

    /**
     * 配置加密处理器
     *
     * @return
     */
    public HashedCredentialsMatcher hashMatcher() {
        HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
        hashMatcher.setHashAlgorithmName("MD5");
        hashMatcher.setHashIterations(10);
        return hashMatcher;
    }

    /**
     * 这个方法生成ShiroFilter的工厂类，因为Shiro要起作用,就需要配置到Spring的过滤器链中。
     * 这样shiro就会拦截到请求进行处理
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login/loginPage");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/login/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/login/unauth");

        //配置静态资源放行
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login/loginIn", "anon");
        filterChainDefinitionMap.put("/login/loginPage", "anon");

        //设置页面权限验证
        filterChainDefinitionMap.put("/userHome/**", "roles[admin]");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


}
