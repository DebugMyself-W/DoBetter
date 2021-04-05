package com.debugmyself.controller;

import com.debugmyself.dto.Friend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @Value("${friend.name}")
    private String name;
    @RequestMapping("/hello")
    public String helloWorld(){
        return "helloWorld";
    }

    @RequestMapping("/testConfigurationProperties")
    public String testConfigurationProperties(){
        Friend friend = new Friend();
        System.out.println(name+"/"+friend.toString());
        return "testConfigurationProperties";
    }
}
