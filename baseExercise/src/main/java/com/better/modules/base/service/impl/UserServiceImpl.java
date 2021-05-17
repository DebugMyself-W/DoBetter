package com.better.modules.base.service.impl;

import com.better.modules.base.entity.User;
import com.better.modules.base.mapper.UserMapper;
import com.better.modules.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByAccount(String account) {
        return userMapper.findByAccount(account);
    }
}
