package com.dpdm.gatewayservice.service_providers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;


public class ServiceProvider {
    
    @Autowired
    EurekaClient discoveryClient;

    public String getServiceURI(String serviceId){

        InstanceInfo instance = discoveryClient.getNextServerFromEureka(serviceId, true);

        return instance.getHomePageUrl();
    }


}
