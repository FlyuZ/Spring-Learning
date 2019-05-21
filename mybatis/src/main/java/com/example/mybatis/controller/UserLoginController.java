package com.example.mybatis.controller;

import com.example.mybatis.entity.User;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/user"})
public class UserLoginController {

    @Autowired
    private UserService userLoginService;

    @RequestMapping("/")
    public String controller(){
        return "/userLogin";
    }
    /**
     * 获取用户名与密码，用户登录
     * @return 登录成功页面
     */
    @RequestMapping(value = {"/userLogin"})
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        User user = userLoginService.userLogin(username, password);
        if (user != null) {                                                  //登录成功
            request.getSession().setAttribute("session_user", user);     //将用户信息放入session
            return "index";
        }
        return "loginError";
    }
    @RequestMapping(value = {"/registerpage"})
    public String registerpage(){
        return "register";
    }
    /**
     * 注册新用户
     * @return 注册结果
     */
    @ResponseBody
    @RequestMapping(value = {"/register"})
    public String addUser(@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password2") String password2) {
        if (!password.equals(password2)) {
            return "两次密码不相同，注册失败！！";
        } else {
            int res = userLoginService.adduser(username, password);
            if (res == 0) {
                return "注册失败！";
            } else {
                return "注册成功！";
            }
        }
    }
}
