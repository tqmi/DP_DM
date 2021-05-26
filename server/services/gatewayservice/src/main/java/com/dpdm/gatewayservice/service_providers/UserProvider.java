package com.dpdm.gatewayservice.service_providers;

import javax.servlet.http.HttpServletRequest;

import com.dpdm.gateway_api.model.MyUser;
import com.dpdm.gatewayservice.models.InternalUser;
import com.google.common.net.HttpHeaders;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class UserProvider {

    @Autowired
    ServiceProvider serviceProvider;

    @Autowired
    RestTemplate restTemplate;

    public InternalUser getUser(HttpServletRequest request){
        
        if(request == null)
            return null;

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        
        if(header == null)
            return null;
        
        String authToken = header.substring("Bearer ".length());
        FirebaseAuth authService = FirebaseAuth.getInstance();

        FirebaseToken userToken;
        try {
            userToken = authService.verifyIdToken(authToken);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("here");
        MyUser user = null;
        try{
            user = restTemplate.getForObject(serviceProvider.getServiceURI("user_service") + "user/{id}",MyUser.class, userToken.getUid());
        }catch(RestClientResponseException e){
            user = null;
        }
            
            // if(resp.hasBody())
        //     user = (MyUser)resp.getBody();

        return new InternalUser(user,userToken.getUid());
        // return restTemplate.getForEntity( serviceProvider.getServiceURI("auth_service") + "/user/info",User.class,auth).getBody();
    }
}
