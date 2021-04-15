package com.better.modules.base.service.impl;

import com.better.modules.base.entity.Student;
import com.better.modules.base.mapper.StudentMapper;
import com.better.modules.base.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Student getStudentById(String id) {
        Student student=studentMapper.getStudentById(id);
        return student;
    }
}
