package com.dpdm.gatewayservice.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.gateway_api.api.FileApiController;
import com.dpdm.gateway_api.model.FileResponse;
import com.dpdm.gatewayservice.service_providers.ServiceProvider;
import com.dpdm.gatewayservice.service_providers.UserProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
       

        return new ResponseEntity<String>("down link",HttpStatus.OK);
    }

    public ResponseEntity<List<FileResponse>> getMyFiles() {
        

        List<FileResponse> list = new ArrayList<FileResponse>();

        list.add(new FileResponse().fileName("file1").fileid("1").owner("owner1").status("new"));
        list.add(new FileResponse().fileName("file2").fileid("2").owner("owner1").status("new"));
        list.add(new FileResponse().fileName("file3").fileid("3").owner("owner1").status("new"));
        list.add(new FileResponse().fileName("file4").fileid("4").owner("owner1").status("new"));

        return new ResponseEntity<List<FileResponse>>(list,HttpStatus.OK);
    }

    public ResponseEntity<Void> signFile(@Parameter(in = ParameterIn.PATH, description = "the files id", required=true, schema=@Schema()) @PathVariable("fileid") String fileid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> uploadFile(@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile filename) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
