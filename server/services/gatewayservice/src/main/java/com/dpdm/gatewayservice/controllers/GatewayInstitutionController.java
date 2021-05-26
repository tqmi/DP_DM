package com.dpdm.gatewayservice.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.gateway_api.api.InstitutionApiController;
import com.dpdm.gateway_api.model.FileResponse;
import com.dpdm.gateway_api.model.Institution;
import com.dpdm.gatewayservice.service_providers.ServiceProvider;
import com.dpdm.gatewayservice.service_providers.UserProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@CrossOrigin
@RestController
public class GatewayInstitutionController extends InstitutionApiController{

    
    
    public GatewayInstitutionController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
    }

    @Autowired
    ServiceProvider serviceProvider;
    
    @Autowired
    UserProvider userProvider;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<List<FileResponse>> getInstitutionFiles(@Parameter(in = ParameterIn.PATH, description = "institutions id", required=true, schema=@Schema()) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<FileResponse>>(objectMapper.readValue("[ {\n  \"owner\" : \"owner\",\n  \"fileName\" : \"fileName\",\n  \"signedBy\" : [ {\n    \"institutionlink\" : \"institutionlink\",\n    \"address\" : \"address\",\n    \"phone\" : \"phone\",\n    \"accesslevel\" : \"accesslevel\",\n    \"name\" : \"name\",\n    \"type\" : \"type\",\n    \"email\" : \"email\"\n  }, {\n    \"institutionlink\" : \"institutionlink\",\n    \"address\" : \"address\",\n    \"phone\" : \"phone\",\n    \"accesslevel\" : \"accesslevel\",\n    \"name\" : \"name\",\n    \"type\" : \"type\",\n    \"email\" : \"email\"\n  } ],\n  \"fileid\" : \"fileid\",\n  \"status\" : \"status\"\n}, {\n  \"owner\" : \"owner\",\n  \"fileName\" : \"fileName\",\n  \"signedBy\" : [ {\n    \"institutionlink\" : \"institutionlink\",\n    \"address\" : \"address\",\n    \"phone\" : \"phone\",\n    \"accesslevel\" : \"accesslevel\",\n    \"name\" : \"name\",\n    \"type\" : \"type\",\n    \"email\" : \"email\"\n  }, {\n    \"institutionlink\" : \"institutionlink\",\n    \"address\" : \"address\",\n    \"phone\" : \"phone\",\n    \"accesslevel\" : \"accesslevel\",\n    \"name\" : \"name\",\n    \"type\" : \"type\",\n    \"email\" : \"email\"\n  } ],\n  \"fileid\" : \"fileid\",\n  \"status\" : \"status\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<FileResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<FileResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<FileResponse>> getInstitutionTemplates(@Parameter(in = ParameterIn.PATH, description = "institutions id", required=true, schema=@Schema()) @PathVariable("id") String id) {
        List<FileResponse> list = new ArrayList<FileResponse>();

        if(id.equals("1")){
            list.add(new FileResponse().fileName("template1").fileid("1").owner("Institution1").status("new"));
            list.add(new FileResponse().fileName("template2").fileid("2").owner("Institution1").status("new"));
            list.add(new FileResponse().fileName("template3").fileid("3").owner("Institution1").status("new"));
            list.add(new FileResponse().fileName("template4").fileid("4").owner("Institution1").status("new"));
        }
        return new ResponseEntity<List<FileResponse>>(list,HttpStatus.OK);
    }

    public ResponseEntity<List<Institution>> getInstitutions() {

        List<Institution> resp = null;

        resp = (List<Institution>)restTemplate.getForObject(serviceProvider.getServiceURI("storage_service") + "/institutions", List.class);

        return ResponseEntity.ok().body(resp);
    }

    public ResponseEntity<Void> uploadInstitutionTemplate(@Parameter(in = ParameterIn.PATH, description = "institutions id", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile filename) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> createInstitution(@Parameter(in = ParameterIn.DEFAULT, description = "the institution details", required=true, schema=@Schema()) @Valid @RequestBody Institution body) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<Institution> request = new HttpEntity<Institution>(body,headers);
        

        restTemplate.postForObject(serviceProvider.getServiceURI("storage_service") + "/institutions", request,Void.class);

        return ResponseEntity.ok().build();
    }
}
