package q1;

public class VigenereCipher implements Cipher {
    private static final int ALPHABET_LENGTH = 26;
    private static final char[][] VIGENERE_SQUARE = new char[ALPHABET_LENGTH][ALPHABET_LENGTH];

    static {
        for (int i = 0; i < ALPHABET_LENGTH; i++) {
            for (int j = 0; j < ALPHABET_LENGTH; j++) {
                VIGENERE_SQUARE[i][j] = (char) ((i + j) % ALPHABET_LENGTH + 'A');
            }
        }
    }

    @Override
    public String encrypt(String message, String key) {
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
    public String decrypt(String message, String key) {
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