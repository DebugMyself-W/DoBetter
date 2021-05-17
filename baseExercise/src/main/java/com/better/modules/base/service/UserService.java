package com.better.modules.base.service;

import com.better.modules.base.entity.User;

public interface UserService {

    public User findByAccount(String account);
}
