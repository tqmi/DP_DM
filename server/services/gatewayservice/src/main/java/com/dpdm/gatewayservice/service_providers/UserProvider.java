package com.dpdm.gatewayservice.service_providers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.dpdm.gateway_api.model.MyUser;
import com.google.common.net.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;

public class UserProvider {

    @Autowired
    ServiceProvider serviceProvider;

    @Autowired
    RestTemplate restTemplate;

    public MyUser getUser(HttpServletRequest request){
        
        if(request == null)
            return null;
        System.out.println("here");
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        System.out.println("here");
        if(header == null)
            return null;
        
        System.out.println(header);
        String auth = header.substring("Bearer ".length());
        
        System.out.println("here");
        if(auth.equals( "regular")){
            MyUser u = new MyUser().name("Test Name").email("test@email").address("test address").phone("+40 777 777 777").type("normal").accesslevel("regular").institutionlink("");
            
            System.out.println(u);
            return u;
            // return new User().name("Test Name").email("test@email").address("test address").phone("+40 777 777 777").type("normal").accessLevel("regular").institutionLink("");
        }else{
            return new MyUser().name("Test Name2").email("test@email2").address("test address2").phone("+40 777 777 777").type("linked").accesslevel("high").institutionlink("some institution");
        }
        // return restTemplate.getForEntity( serviceProvider.getServiceURI("auth_service") + "/user/info",User.class,auth).getBody();
    }
}
