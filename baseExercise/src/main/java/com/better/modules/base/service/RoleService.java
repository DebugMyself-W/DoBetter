package com.better.modules.base.service;

import com.better.modules.base.entity.Role;

import java.util.List;

public interface RoleService {

    public List<Role> findRoleByUserId(Integer id);
}
