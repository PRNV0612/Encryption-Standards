import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;

public class DES {
    
    public static String encrypt(String message, String keyString) throws Exception {
        byte[] keyBytes = keyString.getBytes();
        DESKeySpec keySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(keySpec);
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public static String decrypt(String encryptedMessage, String keyString) throws Exception {
        byte[] keyBytes = keyString.getBytes();
        DESKeySpec keySpec = new DESKeySpec(keyBytes);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(keySpec);
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        byte[] decodedEncryptedMessage = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = cipher.doFinal(decodedEncryptedMessage);
        
        return new String(decryptedBytes);
    }
    
    public static void main(String[] args) {
        try {
            String message = "Hello, Im Pranav Raj!";
            String keyString = "21BC0372"; // Should be 8 characters long
            
            // Encrypt the message
            String encryptedMessage = encrypt(message, keyString);
            System.out.println("Encrypted message: " + encryptedMessage);
            
            // Decrypt the message
            String decryptedMessage = decrypt(encryptedMessage, keyString);
            System.out.println("Decrypted message: " + decryptedMessage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
