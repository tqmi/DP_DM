package com.dpdm.gatewayservice.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.gateway_api.api.InstitutionApiController;
import com.dpdm.gateway_api.model.FileResponse;
import com.dpdm.gateway_api.model.Institution;
import com.dpdm.gateway_api.model.SignRequest;
import com.dpdm.gatewayservice.models.InternalUser;
import com.dpdm.gatewayservice.service_providers.ServiceProvider;
import com.dpdm.gatewayservice.service_providers.UserProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        
        List<FileResponse> resp ;

        resp = (List<FileResponse>)restTemplate.getForObject(serviceProvider.getServiceURI("storage_service") + "institution/{id}/templates", List.class,id);

        return new ResponseEntity<List<FileResponse>>(resp,HttpStatus.OK);
    }

    public ResponseEntity<List<Institution>> getInstitutions() {

        List<Institution> resp = null;

        resp = (List<Institution>)restTemplate.getForObject(serviceProvider.getServiceURI("storage_service") + "/institutions", List.class);

        return ResponseEntity.ok().body(resp);
    }

    public ResponseEntity<Void> uploadInstitutionTemplate(@Parameter(in = ParameterIn.PATH, description = "institutions id", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile filename) {
        InternalUser user = userProvider.getUser(request);
        if(user == null || !user.getUser().getInstitutionlink().equals(id)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        
        
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", filename.getResource());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body,headers);

        restTemplate.postForObject(serviceProvider.getServiceURI("storage_service") + "/institution/{id}/templates", request,Void.class,id);


        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> createInstitution(@Parameter(in = ParameterIn.DEFAULT, description = "the institution details", required=true, schema=@Schema()) @Valid @RequestBody Institution body) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<Institution> request = new HttpEntity<Institution>(body,headers);
        

        restTemplate.postForObject(serviceProvider.getServiceURI("storage_service") + "/institutions", request,Void.class);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteTemplate(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("fileid") String fileid) {
        InternalUser user = userProvider.getUser(request);
        if(user == null || !user.getUser().getInstitutionlink().equals(id)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        restTemplate.delete(serviceProvider.getServiceURI("storage_service") + "institution/{id}/templates/{fileId}",id,fileid);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Institution> getInstitution(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id) {
        Institution resp = null;

        resp = restTemplate.getForObject(serviceProvider.getServiceURI("storage_service") + "/institution/{id}", Institution.class,id);

        return ResponseEntity.ok().body(resp);
    }




    public ResponseEntity<String> getTemplateDownloadLink(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("fileid") String fileid) {

        String resp = restTemplate.getForObject(serviceProvider.getServiceURI("storage_service") + "/institution/{id}/templates/{fileid}/dlink", String.class,id,fileid);

        return new ResponseEntity<String>(resp,HttpStatus.OK);
    }

    public ResponseEntity<Void> sendRequest(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody SignRequest body) {
        
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<SignRequest> request = new HttpEntity<SignRequest>(body,headers);
        

        restTemplate.postForObject(serviceProvider.getServiceURI("storage_service") + "/institution/{id}/requests", request,Void.class,id);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<SignRequest>> getRequests(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("id") String id) {
        
        InternalUser user = userProvider.getUser(request);
        if(user == null || !user.getUser().getInstitutionlink().equals(id)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        
        List<SignRequest> resp = null;

        resp = (List<SignRequest>)restTemplate.getForObject(serviceProvider.getServiceURI("storage_service") + "/institution/{id}/requests", List.class,id);

        return ResponseEntity.ok().body(resp);
    }

    public ResponseEntity<Void> deleteRequest(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("reqid") String reqid) {
        
        InternalUser user = userProvider.getUser(request);
        if(user == null || !user.getUser().getInstitutionlink().equals(id)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        restTemplate.delete(serviceProvider.getServiceURI("storage_service") + "/institution/{id}/requests/{reqid}",id,reqid);

        return ResponseEntity.ok().build();
    }

}
