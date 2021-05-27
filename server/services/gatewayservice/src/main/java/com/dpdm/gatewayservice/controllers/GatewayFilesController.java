package com.dpdm.gatewayservice.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.gateway_api.api.FileApiController;
import com.dpdm.gateway_api.model.FileResponse;
import com.dpdm.gateway_api.model.Signature;
import com.dpdm.gatewayservice.models.InternalUser;
import com.dpdm.gatewayservice.models.SignResponse;
import com.dpdm.gatewayservice.service_providers.FirebaseService;
import com.dpdm.gatewayservice.service_providers.ServiceProvider;
import com.dpdm.gatewayservice.service_providers.UserProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@CrossOrigin
@RestController
public class GatewayFilesController extends FileApiController{

    public GatewayFilesController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
    }


    @Autowired
    ServiceProvider serviceProvider;
    
    @Autowired
    UserProvider userProvider;

    @Autowired
    RestTemplate restTemplate;
    
    public ResponseEntity<String> getDownloadLink(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("fileid") String fileid) {
       
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String resp = restTemplate.getForObject(serviceProvider.getServiceURI("storage_service") + "/" + user.getUid() + "/file/" + fileid + "/dlink", String.class);

        return new ResponseEntity<String>(resp,HttpStatus.OK);
    }

    public ResponseEntity<List<FileResponse>> getMyFiles() {
        
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<FileResponse> resp ;

        resp = (List<FileResponse>)restTemplate.getForObject(serviceProvider.getServiceURI("storage_service") + "/" + user.getUid() + "/files", List.class);

        return new ResponseEntity<List<FileResponse>>(resp,HttpStatus.OK);
    }

    public ResponseEntity<Void> uploadFile(@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile filename) {
        
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        
        
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", filename.getResource());
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body,headers);

        restTemplate.postForObject(serviceProvider.getServiceURI("storage_service") + "/{id}/files", request,Void.class,user.getUid());


        return ResponseEntity.ok().build();

    }

    public ResponseEntity<Void> deleteFile(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("fileId") String fileId) {
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        restTemplate.delete(serviceProvider.getServiceURI("storage_service") + "/{id}/file/{fileId}",user.getUid(),fileId);

        return ResponseEntity.ok().build();
    }


    public ResponseEntity<Void> signFile(@Parameter(in = ParameterIn.HEADER, description = "the files owner id" ,required=true,schema=@Schema()) @RequestHeader(value="ownerId", required=true) String ownerId,@Parameter(in = ParameterIn.PATH, description = "the files id", required=true, schema=@Schema()) @PathVariable("fileid") String fileid,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Signature body) {
        InternalUser user = userProvider.getUser(request);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Blob fileBlob = FirebaseService.getBucket().get(ownerId+"/"+fileid, null); 

        byte [] filedata = fileBlob.getContent(null);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> rqbody = new LinkedMultiValueMap<>();
        rqbody.add("plainFile", filedata);
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(rqbody,headers);
        SignResponse resp = restTemplate.postForObject(serviceProvider.getServiceURI("sign_service") + "/sign", request,SignResponse.class);

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("ownerId", ownerId);

        HttpEntity<Signature> request2 = new HttpEntity<Signature>(body.publicKey(resp.getPublicKey()).signature(resp.getSignature()).by(user.getUser()),headers);

        restTemplate.put(serviceProvider.getServiceURI("storage_service") + "", request2);

        return ResponseEntity.ok().build();
    }


}
