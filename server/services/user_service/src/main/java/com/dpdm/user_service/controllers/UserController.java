package com.dpdm.user_service.controllers;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.user_api.api.UserApiController;
import com.dpdm.user_api.model.MyUser;
import com.dpdm.user_service.firebase.FirebaseConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public class UserController extends UserApiController{

    public UserController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
    }
    

    public ResponseEntity<Void> createUser(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.DEFAULT, description = "the users new details", required=true, schema=@Schema()) @Valid @RequestBody MyUser body) {
        Firestore fs = FirebaseConfig.getFirestore();
        CollectionReference userCol = fs.collection("users");

        try {

            DocumentReference userdoc = userCol.document(id);

            if(userdoc.get().get().exists()){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }else{
                if(userdoc.create(body).get() != null){
                    return ResponseEntity.status(HttpStatus.CREATED).build();
                }
                else{
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> deleteUser(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id) {
        Firestore fs = FirebaseConfig.getFirestore();
        CollectionReference userCol = fs.collection("users");

        try {

            DocumentReference userdoc = userCol.document(id);

            if(userdoc.get().get().exists()){
                if(userdoc.delete().get() != null){
                    return ResponseEntity.status(HttpStatus.OK).build();
                }
                else{
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }else{
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<MyUser> getUserInfo(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id) {
        Firestore fs = FirebaseConfig.getFirestore();
        CollectionReference userCol = fs.collection("users");
        try {

            DocumentReference userdoc = userCol.document(id);
            DocumentSnapshot usersnap = userdoc.get().get(); 
            if(usersnap.exists()){
                return ResponseEntity.status(HttpStatus.OK).body(usersnap.toObject(MyUser.class));
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity<Void> updateUser(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.DEFAULT, description = "the users new details", required=true, schema=@Schema()) @Valid @RequestBody MyUser body) {
        Firestore fs = FirebaseConfig.getFirestore();
        CollectionReference userCol = fs.collection("users");
        try {

            DocumentReference userdoc = userCol.document(id);
            DocumentSnapshot usersnap = userdoc.get().get(); 
            if(usersnap.exists()){

                userdoc.set(body);

                return ResponseEntity.status(HttpStatus.OK).build();
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
