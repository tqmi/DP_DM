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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;


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
        return ResponseEntity.ok(List.of(
            new Institution().name("Institution1").id("1").location("honolulu1"),
            new Institution().name("Institution2").id("2").location("honolulu2"),
            new Institution().name("Institution3").id("3").location("honolulu3"),
            new Institution().name("Institution4").id("4").location("honolulu4"),
            new Institution().name("Institution5").id("5").location("honolulu5"),
            new Institution().name("Institution6").id("6").location("honolulu6"),
            new Institution().name("Institution7").id("7").location("honolulu7")
        ));
    }

    public ResponseEntity<Void> uploadInstitutionTemplate(@Parameter(in = ParameterIn.PATH, description = "institutions id", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile filename) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
}
