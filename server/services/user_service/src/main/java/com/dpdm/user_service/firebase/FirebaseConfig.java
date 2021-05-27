package com.dpdm.user_service.firebase;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.UpdateBuilder;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FirebaseConfig {   

    private static GoogleCredentials credentials;
    private static Storage storage;
    private static Bucket bucket;
    private static Firestore firestore;
    private static String bucketName = "dp-dm-f9041.appspot.com";

    @PostConstruct
    public static void initialize(){
        try{
            FileInputStream serviceAccount =
            new FileInputStream(new ClassPathResource("firebase-adminsdk.json").getFile());

            credentials = GoogleCredentials.fromStream(serviceAccount);

            FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(credentials)
            .setStorageBucket(bucketName)
            .build();
            FirebaseApp.initializeApp(options);
            
            bucket = StorageClient.getInstance().bucket(bucketName);
            storage = bucket.getStorage();
            firestore = FirestoreClient.getFirestore();
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // public static GoogleCredentials getCredentials() {
    //     return credentials;
    // }

    public static Firestore getFirestore() {
        return firestore;
    }

    public static Storage getStorage() {
        return storage;
    }

    public static Bucket getBucket() {
        return bucket;
    }

    public static String getBucketName() {
        return bucketName;
    }

}
