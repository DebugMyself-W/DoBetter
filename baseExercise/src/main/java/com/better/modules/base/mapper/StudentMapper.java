package com.better.modules.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.better.modules.base.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends BaseMapper<Student> {
    public Student getStudentById(String id);
}
