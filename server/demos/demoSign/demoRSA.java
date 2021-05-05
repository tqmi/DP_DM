package demoSign;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyPair;

import fileRSAsigner.workerRSA;

public class demoRSA {
    public static void main(String[] args) {
        try {
            KeyPair keyPair = workerRSA.generateKeyPair();

            File f1 = new File("D:\\University\\Year III\\Sem 2\\DP\\DP_DM\\demoSign\\testFile.txt");
            // File f2 = new File("D:\\University\\Year III\\Sem 2\\DP\\DP_DM\\README.md");

            String signature = workerRSA.sign(f1, keyPair.getPrivate());

            boolean isCorrect = workerRSA.verify(f1, signature, keyPair.getPublic());
            
            System.out.println("Signature correct: " + isCorrect + "\n");

            // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

            String content = Files.readString(f1.toPath());

            String cipherContent = workerRSA.encrypt(content, keyPair.getPublic());

            System.out.println(cipherContent + "\n");

            String decriptedContent = workerRSA.decrypt(cipherContent, keyPair.getPrivate());

            System.out.println(decriptedContent + "\n");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
