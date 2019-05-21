package com.example.mybatis.service.Impl;

import com.example.mybatis.entity.User;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usermapper;


    //用户登录
    public User userLogin(String username,String password){
        User user = usermapper.userlogin(username,password);
        return user;
    }

    //注册新用户
    public int adduser(String username,String password){
        return usermapper.adduser(username,password);
    }
}
