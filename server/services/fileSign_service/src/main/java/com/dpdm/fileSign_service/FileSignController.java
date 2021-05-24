package com.dpdm.fileSign_service;

import java.io.File;
import java.security.KeyPair;

import com.dpdm.workerRSA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class FileSignController {

    @Autowired
    public FileSignController() {
    }

    @GetMapping("/keys")
    public ResponseEntity<KeyPair> getKeys() {
        KeyPair keys = null;
        try {
            keys = workerRSA.generateKeyPair();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ResponseEntity<KeyPair>(keys, HttpStatus.OK);
    }
    
    @GetMapping("/sign")
    public ResponseEntity<SignResponse> fileSign(File plainFile, KeyPair keys) {
        String signature = null;
        try {
            signature = workerRSA.sign(plainFile, keys.getPrivate());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return new ResponseEntity<SignResponse>(new SignResponse(signature, keys.getPublic().toString()), HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> fileVerify(File plainFile, String signature, KeyPair keys) {
        Boolean ok = false;

        try {
            ok = workerRSA.verify(plainFile, signature, keys.getPublic());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ResponseEntity<>(ok, HttpStatus.OK);
    }
}
