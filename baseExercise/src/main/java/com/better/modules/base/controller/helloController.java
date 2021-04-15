package com.better.modules.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("swagger测试")
@RestController
public class helloController {

//    @Value("${friend.name}")
//    private String name;

    @ApiOperation(value = "hello", produces = MediaType.TEXT_PLAIN_VALUE, notes = "返回：Hello swagger")
    @RequestMapping("/hello")
    public String helloWorld(){
        return "helloWorld";
    }

    /*@RequestMapping("/testConfigurationProperties")
    public String testConfigurationProperties(){
        Friend friend = new Friend();
        System.out.println(name+"/"+friend.toString());
        return "testConfigurationProperties";
    }*/
}
