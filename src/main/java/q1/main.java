package q1;

public class main {
    public static void main(String[] args) {
        String data = "Don't forget to test your code!\n" +
                " Otherwise you will be very disappointed.";
        String key = "PROGRAMMING";
        VigenereCipher cipher = new VigenereCipher();
        String encrypted = cipher.encrypt(data, key);
        String decrypted = cipher.decrypt(encrypted, key);
        System.out.println("Original message: " + data);
        System.out.println("Encrypted message: " + encrypted);
        System.out.println("Decrypted message: " + decrypted);
        System.out.println("Decrypted message equals to original message: " + decrypted.equals(data.toUpperCase()));

    }
}
