package com.dpdm.storage_service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.storage_api.api.InstitutionApiController;
import com.dpdm.storage_api.model.Institution;
import com.dpdm.storage_service.firebase.FirebaseConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

@CrossOrigin
@RestController
public class StorageInstitutionController extends InstitutionApiController{

    public StorageInstitutionController(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
        //TODO Auto-generated constructor stub
    }
    
    public ResponseEntity<Void> createInstitution(@Parameter(in = ParameterIn.DEFAULT, description = "the institution details", required=true, schema=@Schema()) @Valid @RequestBody Institution body) {
        
        Firestore fs = FirebaseConfig.getFirestore();
        CollectionReference instCol = fs.collection("institutions");

        try {
            DocumentReference instDoc = instCol.document();
            Query instQuery = instCol.whereEqualTo("name",body.getName());
            QuerySnapshot snp= instQuery.get().get();

            if(!snp.isEmpty()){
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }else{

                if(instDoc.create(body.id(instDoc.getId())).get() != null){
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

    public ResponseEntity<Institution> getInstitution(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id) {
        Firestore fs = FirebaseConfig.getFirestore();
        CollectionReference instCol = fs.collection("institutions");
        Institution resp = null;
        
        try {
            DocumentReference instRef =  instCol.document(id);
            
            resp = instRef.get().get().toObject(Institution.class);

        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return ResponseEntity.ok().body(resp);
    }

    public ResponseEntity<List<Institution>> getInstitutions() {
        Firestore fs = FirebaseConfig.getFirestore();
        CollectionReference instCol = fs.collection("institutions");
        List<Institution> resp = new ArrayList<>();
        
        try {
            QuerySnapshot snp =  instCol.get().get();
            resp = snp.getDocuments().stream().map((qds) -> {
                
                return qds.toObject(Institution.class);
            }).collect(Collectors.toList());


        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return ResponseEntity.ok().body(resp);
    }
}
