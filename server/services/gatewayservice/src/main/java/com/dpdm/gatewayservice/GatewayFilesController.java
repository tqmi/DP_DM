package com.dpdm.gatewayservice;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import com.dpdm.gateway_api.controllers.FilesApiController;
import com.dpdm.gateway_api.model.FileResponse;
import com.dpdm.gateway_api.model.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
public class GatewayFilesController extends FilesApiController{
    
    public GatewayFilesController(@Lazy NativeWebRequest request) {
        super(request);
    }

    @Autowired
    EurekaClient discoveryClient;

    // @Autowired
    // RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private String getServiceURI(String serviceId){

        InstanceInfo instance = discoveryClient.getNextServerFromEureka(serviceId, true);

        return instance.getHomePageUrl();
    }

    private User getUser(String token){
        if(token == null)
            return null;
        String auth = token.substring("Bearer ".length());

        return restTemplate().getForEntity( getServiceURI("auth_service") + "/user/info",User.class,auth).getBody();
    }

    // @GetMapping("/getFiles")
	// public ResponseEntity<?> getFiles(@RequestHeader HttpHeaders headers) throws Exception {
    //     List<String> auth = headers.getValuesAsList(HttpHeaders.AUTHORIZATION);
    //     if(auth.size() == 0)
    //         return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    //     String token = auth.get(0).substring("Bearer ".length());

    //     if(token.equals("asd"))
    //     {
    //         return restTemplate().getForEntity( getServiceURI("storage_service") + "/files",List.class);
    //     }

    //     return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	// }

    


    /**
     * GET /files/{fileid} : Get file link
     * Get the download link for the file
     *
     * @param fileid id string that was sent with the file (required)
     * @return OK (status code 200)
     */
    @ApiOperation(value = "Get file link", nickname = "getDownloadLink", notes = "Get the download link for the file", response = String.class, authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "File", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = String.class) })
    @GetMapping(
        value = "/files/{fileid}",
        produces = { "application/json" }
    )
    public ResponseEntity<String> getDownloadLink(@ApiParam(value = "id string that was sent with the file",required=true) @PathVariable("fileid") String fileid) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /files/myFiles : Returns a list of filenames,authors and download links
     * asd
     *
     * @return OK (status code 200)
     */
    @ApiOperation(value = "Returns a list of filenames,authors and download links", nickname = "getMyFiles", notes = "asd", response = FileResponse.class, responseContainer = "List", authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "File", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = FileResponse.class, responseContainer = "List") })
    @GetMapping(
        value = "/files/myFiles",
        produces = { "application/json" }
    )
    public ResponseEntity<List<FileResponse>> getMyFiles() {
        
        // User user = getUser(getRequest().get().getHeader(HttpHeaders.AUTHORIZATION));

        // if(user == null)
        //     return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        
        List<FileResponse> respType = new ArrayList<FileResponse>();
        return (ResponseEntity<List<FileResponse>>) restTemplate().getForEntity(getServiceURI("storage_service") + "/files",respType.getClass());

    }


    /**
     * POST /files/myFiles : Uploads the file to the stoarge bucket
     * asd
     *
     * @param filename  (optional)
     * @return OK (status code 200)
     *         or invalid request (status code 400)
     */
    @ApiOperation(value = "Uploads the file to the stoarge bucket", nickname = "uploadFile", notes = "asd", authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "File", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "invalid request") })
    @PostMapping(
        value = "/files/myFiles",
        consumes = { "multipart/form-data" }
    )
    public ResponseEntity<Void> uploadFile(@ApiParam(value = "") @Valid @RequestPart(value = "filename", required = false) MultipartFile filename) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
