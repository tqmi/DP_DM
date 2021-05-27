package com.dpdm.storage_service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.storage_api.api.InstitutionApiController;
import com.dpdm.storage_api.model.FileResponse;
import com.dpdm.storage_api.model.Institution;
import com.dpdm.storage_api.model.SignRequest;
import com.dpdm.storage_service.firebase.FirebaseConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    public ResponseEntity<Void> deleteTemplate(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("fileid") String fileid) {
        CollectionReference fileColRef = FirebaseConfig.getFirestore().collection("institutions/"+ id +"/templates");
        DocumentReference fileRef = fileColRef.document(fileid);

        String filename = null;
        try {
            filename = id +"/" +fileRef.get().get().getString("fileName");
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(filename == null)
            return ResponseEntity.notFound().build();

        fileRef.delete();
        FirebaseConfig.getStorage().get(BlobId.of(FirebaseConfig.getBucket().getName(), filename)).delete(null);


        return ResponseEntity.ok().build();
    }


    public ResponseEntity<List<FileResponse>> getInstitutionFiles(@Parameter(in = ParameterIn.PATH, description = "institutions id", required=true, schema=@Schema()) @PathVariable("id") String id) {
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

    public ResponseEntity<List<FileResponse>> getInstitutionTemplates(@Parameter(in = ParameterIn.PATH, description = "institutions id", required=true, schema=@Schema()) @PathVariable("id") String id) {
        CollectionReference fileColRef = FirebaseConfig.getFirestore().collection("institutions/"+ id +"/templates");

        List<FileResponse> files = null;

        try {
            files = fileColRef.get().get().getDocuments().stream().map((qds) -> {return qds.toObject(FileResponse.class);}).collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

		return new ResponseEntity<List<FileResponse>>(files,HttpStatus.OK);
    }

    

    public ResponseEntity<String> getTemplateDownloadLink(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("fileid") String fileid) {
        CollectionReference fileColRef = FirebaseConfig.getFirestore().collection("institutions/"+ id +"/templates");
        DocumentReference fileRef = fileColRef.document(fileid);

        String filename = null;
        try {
            filename = id +"/" +fileRef.get().get().getString("fileName");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        if(filename == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(FirebaseConfig.getStorage().get(BlobId.of(FirebaseConfig.getBucket().getName(), filename)).signUrl(10, TimeUnit.SECONDS).toString());
    }

    public ResponseEntity<Void> uploadInstitutionTemplate(@Parameter(in = ParameterIn.PATH, description = "institutions id", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile filename) {
        BlobId blobId = BlobId.of(FirebaseConfig.getBucketName(),id + "/" +  filename.getOriginalFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(new HashMap<String,String>(){{put("owner",id);}}).build();
        try {
            FirebaseConfig.getStorage().create(blobInfo, filename.getBytes());
            CollectionReference colref = FirebaseConfig.getFirestore().collection("institutions");
            DocumentReference docref =  colref.document(id);
            CollectionReference fileColRef = docref.collection("templates");
            DocumentReference fileDoc = fileColRef.document();

            fileDoc.create(new FileResponse().fileName(filename.getOriginalFilename()).fileid(fileDoc.getId()).owner(id).status("template"));
            
        } catch (IOException e) {
            e.printStackTrace();
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<SignRequest>> getRequests(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("id") String id) {
        CollectionReference reqColRef = FirebaseConfig.getFirestore().collection("institutions/"+ id +"/requests");

        List<SignRequest> files = null;

        try {
            files = reqColRef.get().get().getDocuments().stream().map((qds) -> {return qds.toObject(SignRequest.class);}).collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

		return new ResponseEntity<List<SignRequest>>(files,HttpStatus.OK);
    }


    public ResponseEntity<Void> sendRequest(@Parameter(in = ParameterIn.PATH, description = "id string that was sent with the file", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody SignRequest body) {
        CollectionReference reqColRef = FirebaseConfig.getFirestore().collection("institutions/"+ id +"/requests");

        DocumentReference docref =  reqColRef.document();

        try {
            if(docref.create(body.id(docref.getId())).get() == null){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


        return ResponseEntity.ok().build();
    }


}
