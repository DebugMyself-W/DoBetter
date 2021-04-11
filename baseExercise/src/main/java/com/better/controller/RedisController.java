package com.better.controller;

import com.better.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("redisTest")
@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "redisTest")
    @GetMapping("redisTest")
    public String contextLoads() {
        redisTemplate.opsForValue().set("myKey","诸葛孔暗学架构");
        redisUtil.set("yourKey","debugmyself学架构");
        return redisTemplate.opsForValue().get("myKey");
    }
}
