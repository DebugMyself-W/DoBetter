package com.better.modules.base.mapper;

import com.better.modules.base.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findByAccount(@Param("account") String account);
}
