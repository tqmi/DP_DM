package com.dpdm.storage_service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.dpdm.storage_api.api.FileApiController;
import com.dpdm.storage_api.model.FileResponse;
import com.dpdm.storage_service.firebase.FirebaseConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage.BlobGetOption;

import org.springframework.beans.factory.annotation.Autowired;
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
        
        CollectionReference fileColRef = FirebaseConfig.getFirestore().collection("users/"+ id +"/files");
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

        return ResponseEntity.ok().body(FirebaseConfig.getStorage().get(BlobId.of(FirebaseConfig.getBucket().getName(), filename)).signUrl(10, TimeUnit.SECONDS).toString());
    }

    public ResponseEntity<List<FileResponse>> getFiles(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id) {
        
        CollectionReference fileColRef = FirebaseConfig.getFirestore().collection("users/"+ id +"/files");

        List<FileResponse> files = null;

        try {
            files = fileColRef.get().get().getDocuments().stream().map((qds) -> {return qds.toObject(FileResponse.class);}).collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

		return new ResponseEntity<List<FileResponse>>(files,HttpStatus.OK);
    }

    public ResponseEntity<Void> signFile(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("fileid") String fileid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> uploadFile(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(description = "file detail") @Valid @RequestPart("file") MultipartFile filename) {
        BlobId blobId = BlobId.of(FirebaseConfig.getBucketName(),id + "/" +  filename.getOriginalFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(new HashMap<String,String>(){{put("owner",id);}}).build();
        try {
            FirebaseConfig.getStorage().create(blobInfo, filename.getBytes());
            CollectionReference colref = FirebaseConfig.getFirestore().collection("users");
            DocumentReference docref =  colref.document(id);
            CollectionReference fileColRef = docref.collection("files");
            DocumentReference fileDoc = fileColRef.document();

            fileDoc.create(new FileResponse().fileName(filename.getOriginalFilename()).fileid(fileDoc.getId()).owner(id).status("new"));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }
}
