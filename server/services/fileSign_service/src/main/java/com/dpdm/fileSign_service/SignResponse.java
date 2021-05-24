package com.dpdm.fileSign_service;

public class SignResponse {
    private String signature;
    private String publicKey;
    
    public SignResponse(String signature, String publicKey) {
        this.signature = signature;
        this.publicKey = publicKey;
    }

    public String getSignature() {  return this.signature;  }
    
    public String getPublicKey() {  return this.publicKey;  }
}
