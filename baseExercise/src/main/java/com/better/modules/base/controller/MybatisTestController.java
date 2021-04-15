package com.better.modules.base.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.better.modules.base.entity.Student;
import com.better.modules.base.service.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("测试mybatis")
@RequestMapping("mybatisTestController")
public class MybatisTestController {

    @Autowired
    private IStudentService studentService;


    @ApiOperation(value = "mybatisTest")
    @GetMapping("getStudentById")
    public String getStudentById(String id){
        if(null==id) id="1";
        Student student=studentService.getStudentById(id);
        return student.toString();
    }

    @ApiOperation(value = "mybatisPlusTest")
    @GetMapping("getStudentByMybatis")
    public String getStudentByMybatis(String id){
        if(null==id) id="1";
        Student student=studentService.getStudentById(id);
        return student.toString();
    }

    /*public List<Student> findList(int pageNo, int pageSize){


        //创建查询条件封装器
        QueryWrapper<Student> wrapper = new QueryWrapper<>();

        Page<Student> page = new Page<>(pageNo, pageSize);

        IPage<Student> iPage = userService.page(page, wrapper);

        List<Student> records = iPage.getRecords();

        return records;

    }*/

}
