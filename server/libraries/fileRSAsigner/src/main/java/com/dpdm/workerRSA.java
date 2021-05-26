package com.dpdm;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class workerRSA {
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        KeyPair pair = generator.generateKeyPair();
    
        return pair;
    }

    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
    
        // javax.crypto.IllegalBlockSizeException: Data must not be longer than 245 bytes
        byte[] cipherText = encryptCipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
    
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(cipherText);
    
        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);
    
        return new String(decriptCipher.doFinal(bytes), StandardCharsets.UTF_8);
    }

    public static String sign(byte[] plainFile, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(plainFile);
    
        byte[] signature = privateSignature.sign();
    
        return Base64.getEncoder().encodeToString(signature);
    }

    public static boolean verify(byte[] plainFile, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainFile);
    
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
    
        return publicSignature.verify(signatureBytes);
    }
}