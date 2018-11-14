package com.tag.springbootweb.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author tag
 * @date 2018/11/6 15:39
 * @description
 */
@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @ResponseBody
    @GetMapping("/index")
    public String gotoIndex() {
        return "系统首页";
    }

    @ResponseBody
    @GetMapping("/unauth")
    public String unAuthPage() {
        return "未授权页面";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            log.info("用户已登录：" + subject.getSession().getId());
            return "redirect:index";
        } else {
            log.info("用户未登录，进入登录页面");
            return "login";
        }
    }

    @ResponseBody
    @PostMapping("/loginIn")
    public String loginIn(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            log.info("登陆成功：" + username);
            log.error("aasaasss阿萨斯");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("登陆失败：" + username);
            return "error";
        }
    }

}
