package com.example.mybatis.service;

import com.example.mybatis.entity.User;

public interface UserService {
    public User userLogin(String username,String password);

    public int adduser(String username,String password);
}
