package com.dpdm.fileSign_service;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.validation.Valid;

import com.dpdm.workerRSA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileSignController {

    @Autowired
    public FileSignController() {
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    @PostMapping("/sign")
    public ResponseEntity<SignResponse> fileSign(
            @Valid @RequestPart("plainFile") byte[] plainFile) {
        String signature = null;
        KeyPair keys = null;
        try {
            keys = workerRSA.generateKeyPair();
            signature = workerRSA.sign(plainFile, keys.getPrivate());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(keys.getPublic().getFormat());

        return new ResponseEntity<SignResponse>(new SignResponse(signature, bytesToHex(keys.getPublic().getEncoded())),
                HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<Boolean> fileVerify(
            @Valid @RequestPart(value = "plainFile", required = true) MultipartFile plainFile,
            @Valid @RequestPart(value = "signature", required = true) String signature,
            @Valid @RequestPart(value = "publicKey", required = true) String publicKey) {
        Boolean ok = false;

        try {
            ok = workerRSA.verify(plainFile.getBytes(), signature,
                    KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(hexStringToByteArray(publicKey))));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new ResponseEntity<>(ok, HttpStatus.OK);
    }
}
