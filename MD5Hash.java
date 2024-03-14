import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {

    public static void main(String[] args) {
        String message = "Hello, world!"; // Your message here

        try {
            // Create MD5 hash instance
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Update the digest with the message bytes
            md.update(message.getBytes());

            // Generate the MD5 hash
            byte[] hashBytes = md.digest();

            // Convert byte array to hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            // Print the MD5 hash (message authentication code)
            System.out.println("MD5 Hash (Message Authentication Code): " + sb.toString());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("MD5 algorithm not available.");
            e.printStackTrace();
        }
    }
}
	 	  	 	 	  	  	 	    	     	     	 	
