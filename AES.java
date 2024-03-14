import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {    
    public static String encrypt(String message, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public static String decrypt(String encryptedMessage, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        
        byte[] decodedEncryptedMessage = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(decodedEncryptedMessage);
        
        return new String(decryptedBytes);
    }
    
    public static void main(String[] args) {
        try {
            String message = "Hello, Im Pranav Raj!";
            String key = "AA21BCI0372VITAA"; // 16 bytes key for AES-128
            
            // Encrypt the message
            String encryptedMessage = encrypt(message, key);
            System.out.println("Encrypted message: " + encryptedMessage);
            
            // Decrypt the message
            String decryptedMessage = decrypt(encryptedMessage, key);
            System.out.println("Decrypted message: " + decryptedMessage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
