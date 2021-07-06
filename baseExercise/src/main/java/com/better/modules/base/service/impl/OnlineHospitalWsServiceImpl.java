package com.better.modules.base.service.impl;

import com.better.modules.base.service.IOnlineHospitalWsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jws.WebService;

@Slf4j
@WebService(serviceName="onlineHospitalService",//对外发布的服务名
        targetNamespace="http://service.base.modules.better.com",//指定你想要的名称空间，通常使用使用包名反转
        endpointInterface="com.better.modules.base.service.IOnlineHospitalWsService")//服务接口全路径, 指定做SEI（Service EndPoint Interface）服务端点接口
@Component
public class OnlineHospitalWsServiceImpl implements IOnlineHospitalWsService {



    @Override
    public String process(String param) {
        String result="";
        try {
//            result=iOnlineHospitalService.process(param);
        } catch (Exception e) {
            log.info("请求出现异常:{}",e);
        }
        return result;
    }
}
