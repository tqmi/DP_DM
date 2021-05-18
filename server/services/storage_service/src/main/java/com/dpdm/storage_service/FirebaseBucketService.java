package com.dpdm.storage_service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.dpdm.storage_service.firebase.FirebaseConfig;
import com.google.cloud.Date;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.auth.FirebaseToken;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FirebaseBucketService implements StorageService{
    

    @Override
    public void store(MultipartFile file,FirebaseToken user) {
        
        BlobId blobId = BlobId.of(FirebaseConfig.getBucketName(), file.getOriginalFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        try {
            FirebaseConfig.getStorage().create(blobInfo, file.getBytes());
            // WriteBatch batch = FirebaseConfig.getFirestore().batch();
            CollectionReference colref= FirebaseConfig.getFirestore().collection("files");
            colref.add(new MyFile(blobId.getName(),"tamas"));
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public Stream<MyFile> loadAll() {
        return StreamSupport.stream(FirebaseConfig.getBucket().list().iterateAll().spliterator(),false).map(blob -> new MyFile(blob.getName(),"tamas"));
    }

    @Override
    public URI load(String filename) {
        try {
            return new URI(FirebaseConfig.getBucket().get(filename).getSelfLink());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        return new UrlResource(FirebaseConfig.getStorage().get(BlobId.of(FirebaseConfig.getBucket().getName(), filename)).signUrl(10, TimeUnit.SECONDS));
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        
    }
    
}
