import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RSA {
    private BigInteger p, q, N, phi, e, d;
    private int bitlength = 1024;
    private Random r;

    // Constructor to generate RSA keys
    public RSA() {
        r = new Random();
        // Generate two prime numbers p and q
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        System.out.println("Prime number p is" + p);
        System.out.println("prime number q is" + q);
        // Compute N and φ(N)
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        // Choose public key e such that e and φ(N) are coprime
        e = BigInteger.probablePrime(bitlength / 2, r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        System.out.println("Public key is" + e);
        // Compute private key d
        d = e.modInverse(phi);
        System.out.println("Private key is" + d);
    }

    // Constructor with predefined keys
    public RSA(BigInteger e, BigInteger d, BigInteger N) {
        this.e = e;
        this.d = d;
        this.N = N;
    }

    public static void main(String[] args) throws IOException {        
        RSA rsa = new RSA(); // Create an instance of the RSA class
        // Input stream to read user input
        DataInputStream in = new DataInputStream(System.in);
        String testString; // Variable to store user input
        // Prompt the user to enter plain text
        System.out.println("Enter the plain text:");
        testString = in.readLine(); // Read user input
        // Print the text before encryption
        System.out.println("Encrypting string: " + testString);
        // Print the string in bytes
        System.out.println("String in bytes: " + bytesToString(testString.getBytes()));
        // Encrypt the plain text using RSA encryption
        byte[] encrypted = rsa.encrypt(testString.getBytes());
        // Decrypt the encrypted text
        byte[] decrypted = rsa.decrypt(encrypted);
        // Print decrypted bytes
        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
        // Print the decrypted string
        System.out.println("Decrypted string: " + new String(decrypted));
    }


    // Convert byte array to string
    private static String bytesToString(byte[] encrypted) {
        StringBuilder test = new StringBuilder();
        for (byte b : encrypted) {
            test.append(Byte.toString(b));
        }
        return test.toString();
    }

    // Encryption method
    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    // Decryption method
    public byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
}
