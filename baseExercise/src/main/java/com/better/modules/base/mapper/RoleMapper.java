package com.better.modules.base.mapper;

import com.better.modules.base.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    List<Role> findRoleByUserId(@Param("userId") Integer userId);
}
