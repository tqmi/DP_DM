/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.dpdm.gateway_api.api;

import com.dpdm.gateway_api.model.FileResponse;
import org.springframework.core.io.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-20T13:45:16.599Z[GMT]")
@Validated
public interface FileApi {

    @Operation(summary = "Get file link", description = "Get the download link for the file", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "File" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = String.class))) })
    @RequestMapping(value = "/files/{fileid}/dlink",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> getDownloadLink(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("fileid") String fileid);


    @Operation(summary = "Returns a list of filenames,authors and download links", description = "asd", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "File" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FileResponse.class)))) })
    @RequestMapping(value = "/files/myFiles",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<FileResponse>> getMyFiles();


    @Operation(summary = "sign file", description = "sign file pointed by fileid", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "File" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK") })
    @RequestMapping(value = "/files/{fileid}/sign",
        method = RequestMethod.PUT)
    ResponseEntity<Void> signFile(@Parameter(in = ParameterIn.PATH, description = "the files id", required=true, schema=@Schema()) @PathVariable("fileid") String fileid);


    @Operation(summary = "Uploads the file to the stoarge bucket", description = "asd", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "File" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK"),
        
        @ApiResponse(responseCode = "400", description = "invalid request") })
    @RequestMapping(value = "/files/myFiles",
        consumes = { "multipart/form-data" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> uploadFile(@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile filename);

}

