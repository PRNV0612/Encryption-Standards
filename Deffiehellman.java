import java.math.BigInteger;
import java.security.SecureRandom;

public class Deffiehellman {

    private static final BigInteger p = BigInteger.valueOf(23); // Prime number
    private static final BigInteger g = BigInteger.valueOf(5); // Generator

    public static void main(String[] args) {
        // Parties
        Party partyA = new Party("A");
        Party partyB = new Party("B");
        Party partyC = new Party("C");

        // Exchange public keys
        partyA.receivePublicKey(partyB.getPublicKey());
        partyA.receivePublicKey(partyC.getPublicKey());

        partyB.receivePublicKey(partyA.getPublicKey());
        partyB.receivePublicKey(partyC.getPublicKey());

        partyC.receivePublicKey(partyA.getPublicKey());
        partyC.receivePublicKey(partyB.getPublicKey());

        // Compute shared secrets
        partyA.computeSharedSecret();
        partyB.computeSharedSecret();
        partyC.computeSharedSecret();

        // Demonstrate man-in-the-middle attack
        ManInTheMiddle attacker = new ManInTheMiddle();
        attacker.attack(partyA, partyB, partyC);
    }

    static class Party {
        private String name;
        private BigInteger privateKey;
        private BigInteger publicKey;
        private BigInteger receivedPublicKey;
        private BigInteger sharedSecret;

        public Party(String name) {	 	  	 	 	  	  	 	    	     	     	 	
            this.name = name;
            generateKeys();
        }

        private void generateKeys() {
            SecureRandom random = new SecureRandom();
            privateKey = new BigInteger(8, random); // Generate private key randomly
            publicKey = g.modPow(privateKey, p); // Compute public key
        }

        public BigInteger getPublicKey() {
            return publicKey;
        }

        public void receivePublicKey(BigInteger publicKey) {
            receivedPublicKey = publicKey;
        }

        public void computeSharedSecret() {
            sharedSecret = receivedPublicKey.modPow(privateKey, p);
            System.out.println(name + " computed shared secret: " + sharedSecret);
        }
    }

    static class ManInTheMiddle {
        public void attack(Party partyA, Party partyB, Party partyC) {
            // Attacker intercepts and replaces public keys
            partyA.receivePublicKey(partyB.getPublicKey());
            partyA.receivePublicKey(partyC.getPublicKey());

            partyB.receivePublicKey(partyA.getPublicKey());
            partyB.receivePublicKey(partyC.getPublicKey());

            partyC.receivePublicKey(partyA.getPublicKey());
            partyC.receivePublicKey(partyB.getPublicKey());

            // Compute shared secrets for the attacker
            BigInteger sharedSecretA = partyA.getPublicKey().modPow(partyB.privateKey, p);
            BigInteger sharedSecretB = partyB.getPublicKey().modPow(partyC.privateKey, p);
            BigInteger sharedSecretC = partyC.getPublicKey().modPow(partyA.privateKey, p);

            // Demonstrate successful MITM attack
            System.out.println("Attacker computed shared secret with A: " + sharedSecretA);
            System.out.println("Attacker computed shared secret with B: " + sharedSecretB);
            System.out.println("Attacker computed shared secret with C: " + sharedSecretC);
        }	 	  	 	 	  	  	 	    	     	     	 	
    }
}
