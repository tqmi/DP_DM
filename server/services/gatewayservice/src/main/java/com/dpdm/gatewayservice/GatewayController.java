package com.dpdm.gatewayservice;


import java.net.URI;
import java.util.List;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GatewayController {
    
    @Autowired
    EurekaClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    private String getServiceURI(String serviceId){

        InstanceInfo instance = discoveryClient.getNextServerFromEureka(serviceId, true);

        return instance.getHomePageUrl();
       

    }

    @GetMapping("/getFiles")
	public ResponseEntity<?> getFiles(@RequestHeader HttpHeaders headers) throws Exception {
        List<String> auth = headers.getValuesAsList(HttpHeaders.AUTHORIZATION);
        if(auth.size() == 0)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        String token = auth.get(0).substring("Bearer ".length());

        if(token.equals("asd"))
        {
            return restTemplate().getForEntity( getServiceURI("storage_service") + "/files",List.class);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
