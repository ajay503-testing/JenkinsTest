package utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DecryptClass {

    public static String encryptedPassWord="wzVwxXxB9X1h8M0Y6BdDrg==";

    public  static String decryptedPass;
    static String jenkinsSecretKey;
    static String frameworkSecretKey;
    public static String getdecryptPassword()  {

        //decript logic
        //dript only when scret key which is coming from jenkins and key which is in our framework matches
       /* if(jenkinseccretkey.equalIgnore("secretKey"))
        {
            //decrypt logic
        }*/
   /*     byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassWord);
        String decryptedPass= new String(decodedBytes);*/




        // Compare the secret keys
        /*if (compareSecretKeys(jenkinsSecretKey, frameworkSecretKey)) {
            // Decrypt the password
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassWord);
            decryptedPass= new String(decodedBytes);
        }*/

     /*jenkinsSecretKey = generateSecretKey();
    frameworkSecretKey = generateSecretKey();*/
        //jenkinsSecretKey = "gDbiuXZ0V3jzSqc5N5V5u3XvNqHex+LAmn/Lf7RWULU=";
        frameworkSecretKey = "gDbiuXZ0V3jzSqc5N5V5u3XvNqHex+LAmn/Lf7RWULU=";
        jenkinsSecretKey= System.getenv("secretKey");

        System.out.println(jenkinsSecretKey);
    System.out.println(frameworkSecretKey);
        // Compare the secret keys
        if (compareSecretKeys(jenkinsSecretKey, frameworkSecretKey)) {
            // Decrypt the password
            try {
                 decryptedPass = decryptPassword(generateSecretKey());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return decryptedPass;
    }



    public static String decryptPassword(String secretKey) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        SecretKeySpec keySpec = new SecretKeySpec(decoder.decode(secretKey), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
       // byte[] base64decodedTokenArr = Base64.getDecoder().decode(encryptedPassWord.getBytes());
        //byte[] decryptedBytes = cipher.doFinal(base64decodedTokenArr);
       System.out.println(EncryptClass.encryptPassword("admin123",secretKey));
       byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(EncryptClass.encryptPassword("admin123",secretKey)));
        //byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassWord));
        return new String(decryptedBytes).trim();
    }

    public static boolean compareSecretKeys(String jenkinsSecretKey, String frameworkSecretKey) {
        return jenkinsSecretKey.equals(frameworkSecretKey);
    }



    private static String secretKey;

    public static String generateSecretKey() {

        if(secretKey==null)
        {
            try {
                KeyGenerator keygenerator=KeyGenerator.getInstance("AES");
                keygenerator.init(256);
                SecretKey generatedKey=keygenerator.generateKey();
                secretKey=Base64.getEncoder().encodeToString(generatedKey.getEncoded());

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        return secretKey;
    }

}
