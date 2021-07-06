package com.better.modules.base.config;

import com.better.modules.base.service.IOnlineHospitalWsService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class OnlineHospitalWSConfig {

    @Bean
    public ServletRegistrationBean wsServlet(){
        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
    }

    @Autowired
    private IOnlineHospitalWsService testApiService;

    @Autowired
    @Qualifier(Bus.DEFAULT_BUS_ID)
    private SpringBus bus;

    @Bean
    public Endpoint endpoint(){
        EndpointImpl endpoint = new EndpointImpl(bus, testApiService);
        endpoint.publish("/apiService");
        return endpoint;
    }

}
