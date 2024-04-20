package utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptClass {


    public static String encryptPassword(String password, String secretKey) throws Exception {
        // Decode the secret key from Base64
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        // Create a SecretKeySpec object using the decoded key
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        // Initialize the Cipher object for encryption
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, originalKey);
        // Encrypt the password
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        // Encode the encrypted password to Base64
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
