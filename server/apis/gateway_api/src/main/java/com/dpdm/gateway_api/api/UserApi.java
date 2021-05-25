/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.dpdm.gateway_api.api;

import com.dpdm.gateway_api.model.MyUser;
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

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-25T09:32:59.971Z[GMT]")
@Validated
public interface UserApi {

    @Operation(summary = "create user details in db", description = "create user", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK") })
    @RequestMapping(value = "/user/info",
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "the users new details", required=true, schema=@Schema()) @Valid @RequestBody MyUser body);


    @Operation(summary = "delete user in db", description = "delete user details", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "ok") })
    @RequestMapping(value = "/user/info",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteUser();


    @Operation(summary = "Get the user information", description = "asd", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = MyUser.class))),
        
        @ApiResponse(responseCode = "204", description = "user not in db, update info with PUT") })
    @RequestMapping(value = "/user/info",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<MyUser> getUserInfo();


    @Operation(summary = "update user details in db", description = "update user details", security = {
        @SecurityRequirement(name = "bearerAuth")    }, tags={ "User" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "OK") })
    @RequestMapping(value = "/user/info",
        consumes = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.DEFAULT, description = "the users new details", required=true, schema=@Schema()) @Valid @RequestBody MyUser body);

}

