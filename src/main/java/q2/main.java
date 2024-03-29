package q2;

public class main {

    public static String redact(String content, String[] redactWords) {
        String[] words = content.split(" ");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            String normalizedWord = word.replaceAll("[^a-zA-Z\\d]", "").toLowerCase(); // Remove non-alphabetic characters and convert to lowercase
            boolean redacted = false;

            for (String redactWord : redactWords) {
                if (normalizedWord.equals(redactWord.toLowerCase())) { // Check if word matches any redact word
                    redacted = true;
                    break;
                }
            }

            if (redacted) {
                result.append(redactWord(word)); // Redact the word
            } else {
                result.append(word); // Append the original word
            }

            result.append(" "); // Append space between words
        }

        return result.toString().trim(); // Remove trailing space and return the result
    }

    private static String redactWord(String word) {
        StringBuilder redactedWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i)) || Character.isDigit(word.charAt(i))) {
                redactedWord.append('*'); // Replace each character with '*'
            } else {
                redactedWord.append(word.charAt(i)); // Keep non-alphabetic characters
            }
        }
        return redactedWord.toString();
    }

    public static void main(String[] args) {
        String content = "The quick brown fox jumps over the lazy d0g.";
        String[] redactWords = {"Fox", "jumps", "d0g"};

        String redactedContent = redact(content, redactWords);
        System.out.println(redactedContent);
    }
}
