package com.better.modules.base.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * // 暴露服务名称
 * // 命名空间,一般是接口的包名倒序)
 */
@WebService(targetNamespace = "http://service.base.modules.better.com",
        name = "onlineHospitalService")
public interface IOnlineHospitalWsService {

    @WebMethod
    @WebResult(name="String",targetNamespace="")
    public String process(@WebParam(name = "param") String param);

}
