package com.dpdm.gatewayservice.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.gateway_api.api.UserApiController;
import com.dpdm.gateway_api.model.MyUser;
import com.dpdm.gatewayservice.models.InternalUser;
import com.dpdm.gatewayservice.service_providers.ServiceProvider;
import com.dpdm.gatewayservice.service_providers.UserProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;


@RestController
public class GatewayUserController extends UserApiController{

   
    
    public GatewayUserController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
        //TODO Auto-generated constructor stub
    }

    @Autowired
    ServiceProvider serviceProvider;
    
    @Autowired
    UserProvider userProvider;

    @Autowired
    RestTemplate restTemplate;


    public ResponseEntity<Void> deleteUser() {
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        restTemplate.delete(serviceProvider.getServiceURI("user_service") + "/user/{id}",user.getUid());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "the users new details", required=true, schema=@Schema()) @Valid @RequestBody MyUser body) {
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        restTemplate.postForEntity(serviceProvider.getServiceURI("user_service") + "/user/{id}",body,Void.class,user.getUid());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<MyUser> getUserInfo() {
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return new ResponseEntity<MyUser>(user.getUser(),HttpStatus.OK);
    }

    public ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "the users new details", required=true, schema=@Schema()) @Valid @RequestBody MyUser body) {
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        restTemplate.put(serviceProvider.getServiceURI("user_service") + "/user/{id}",body,user.getUid());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}
