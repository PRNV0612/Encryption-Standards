import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

public class RSA {
    
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Key size
        return keyPairGenerator.generateKeyPair();
    }
    
    public static String encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    
    public static String decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
    
    public static void main(String[] args) {
        try {
            String message = "Hello, world!";
            
            // Generate key pair
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            
            // Encrypt the message using the public key
            String encryptedMessage = encrypt(message, publicKey);
            System.out.println("Encrypted message: " + encryptedMessage);
            
            // Decrypt the message using the private key
            String decryptedMessage = decrypt(encryptedMessage, privateKey);
            System.out.println("Decrypted message: " + decryptedMessage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
