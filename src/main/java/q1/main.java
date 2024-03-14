package q1;

public class main {
    public static void main(String[] args) {

        VigenereCipher cipher = new VigenereCipher();
        String encrypted = cipher.encrypt("message.txt", "key.txt");
        String decrypted = cipher.decrypt("dec.txt", "key.txt");
        System.out.println("Encrypted message: " + encrypted);
        System.out.println("Decrypted message: " + decrypted);

    }
}
