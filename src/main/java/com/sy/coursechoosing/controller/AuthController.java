package com.sy.coursechoosing.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("login")
    public String login(String username, String password, Boolean rememberMe) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password, rememberMe);
        subject.login(usernamePasswordToken);
        return "login";
    }

    @GetMapping("logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("error")
    public String error() {
        return "no auth";
    }

}
