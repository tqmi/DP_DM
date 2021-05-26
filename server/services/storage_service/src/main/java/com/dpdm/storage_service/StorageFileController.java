package com.dpdm.storage_service;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.storage_api.api.FileApiController;
import com.dpdm.storage_api.model.FileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public class StorageFileController extends FileApiController{

    public StorageFileController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
        //TODO Auto-generated constructor stub
    }
    
    public ResponseEntity<String> getDlink(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("fileid") String fileid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<String>(objectMapper.readValue("\"\"", String.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<String>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<FileResponse>> getFiles(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<FileResponse>>(objectMapper.readValue("[ {\n  \"owner\" : \"owner\",\n  \"fileName\" : \"fileName\",\n  \"signedBy\" : [ {\n    \"institutionlink\" : \"institutionlink\",\n    \"address\" : \"address\",\n    \"cnp\" : \"cnp\",\n    \"phone\" : \"phone\",\n    \"accesslevel\" : \"accesslevel\",\n    \"name\" : \"name\",\n    \"type\" : \"type\",\n    \"email\" : \"email\"\n  }, {\n    \"institutionlink\" : \"institutionlink\",\n    \"address\" : \"address\",\n    \"cnp\" : \"cnp\",\n    \"phone\" : \"phone\",\n    \"accesslevel\" : \"accesslevel\",\n    \"name\" : \"name\",\n    \"type\" : \"type\",\n    \"email\" : \"email\"\n  } ],\n  \"fileid\" : \"fileid\",\n  \"status\" : \"status\"\n}, {\n  \"owner\" : \"owner\",\n  \"fileName\" : \"fileName\",\n  \"signedBy\" : [ {\n    \"institutionlink\" : \"institutionlink\",\n    \"address\" : \"address\",\n    \"cnp\" : \"cnp\",\n    \"phone\" : \"phone\",\n    \"accesslevel\" : \"accesslevel\",\n    \"name\" : \"name\",\n    \"type\" : \"type\",\n    \"email\" : \"email\"\n  }, {\n    \"institutionlink\" : \"institutionlink\",\n    \"address\" : \"address\",\n    \"cnp\" : \"cnp\",\n    \"phone\" : \"phone\",\n    \"accesslevel\" : \"accesslevel\",\n    \"name\" : \"name\",\n    \"type\" : \"type\",\n    \"email\" : \"email\"\n  } ],\n  \"fileid\" : \"fileid\",\n  \"status\" : \"status\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<FileResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<FileResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> signFile(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("fileid") String fileid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> uploadFile(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile filename) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
}
