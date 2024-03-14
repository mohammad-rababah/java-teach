package q1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;

public class VigenereCipher implements Cipher {
    private static final int ALPHABET_LENGTH = 26;
    private static final char[][] VIGENERE_SQUARE = new char[ALPHABET_LENGTH][ALPHABET_LENGTH];
    static final String filePath = "src/main/java/q1";

    static {
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            for (int j = 0; j < ALPHABET_LENGTH; j++) {
                VIGENERE_SQUARE[i][j] = (char) ((i + j) % ALPHABET_LENGTH + 'A');
            }
        }
    }

    @Override
    public String encrypt(String message_filename, String
            key_filename) {
        String fullPath = "";
        String currentDir = "";
        String message = "";
        String key = "";
        try {
            currentDir = System.getProperty("user.dir");
            fullPath = Paths.get(currentDir, filePath, message_filename).toString();
            BufferedReader reader = new BufferedReader(new FileReader(fullPath));
            String line;
            while ((line = reader.readLine()) != null) {
                message += line;
            }
            fullPath = Paths.get(currentDir, filePath, key_filename).toString();
            reader = new BufferedReader(new FileReader(fullPath));

            while ((line = reader.readLine()) != null) {
                key += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder encryptedMessage = new StringBuilder();
        int keyIndex = 0;
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                int rowIndex = Character.toUpperCase(key.charAt(keyIndex % key.length())) - 'A';
                int colIndex = Character.toUpperCase(c) - 'A';
                encryptedMessage.append(VIGENERE_SQUARE[rowIndex][colIndex]);
                keyIndex++;
            } else {
                encryptedMessage.append(c);
            }
        }
        return encryptedMessage.toString();
    }

    @Override
    public String decrypt(String message_filename, String key_filename) {
        String fullPath = "";
        String currentDir = "";
        String message = "";
        String key = "";
        try {
            currentDir = System.getProperty("user.dir");
            fullPath = Paths.get(currentDir, filePath, message_filename).toString();
            BufferedReader reader = new BufferedReader(new FileReader(fullPath));

            String line;
            while ((line = reader.readLine()) != null) {
                message += line;
            }
            fullPath = Paths.get(currentDir, filePath, key_filename).toString();
            reader = new BufferedReader(new FileReader(fullPath));

            while ((line = reader.readLine()) != null) {
                key += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder decryptedMessage = new StringBuilder();
        int keyIndex = 0;
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                int rowIndex = Character.toUpperCase(key.charAt(keyIndex % key.length())) - 'A';
                int colIndex = 0;
                while (VIGENERE_SQUARE[rowIndex][colIndex] != Character.toUpperCase(c)) {
                    colIndex++;
                }
                decryptedMessage.append((char) (colIndex + 'A'));
                keyIndex++;
            } else {
                decryptedMessage.append(c);
            }
        }
        return decryptedMessage.toString();
    }
}