package com.better.modules.base.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {

    List<String> findByRoleId(@Param("roleIds") List<Integer> roleIds);
}
