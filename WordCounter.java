import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text or provide a file path to count words:");
        String input = scanner.nextLine();
        String[] words;

        if (input.startsWith("file:")) {
            String filePath = input.substring(5);
            try {
                File file = new File(filePath);
                Scanner fileScanner = new Scanner(file);
                StringBuilder stringBuilder = new StringBuilder();
                while (fileScanner.hasNextLine()) {
                    stringBuilder.append(fileScanner.nextLine()).append("\n");
                }
                words = stringBuilder.toString().split("\\s+|\\p{Punct}");
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Please provide a valid file path.");
                return;
            }
        } else {
            words = input.split("\\s+|\\p{Punct}");
        }
        int wordCount = 0;
        HashMap<String, Integer> wordFrequency = new HashMap<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount++;
                String lowercaseWord = word.toLowerCase();
                wordFrequency.put(lowercaseWord, wordFrequency.getOrDefault(lowercaseWord, 0) + 1);
            }
        }

        System.out.println("Total number of words: " + wordCount);
        System.out.println("Word frequency:");
        for (String word : wordFrequency.keySet()) {
            System.out.println(word + ": " + wordFrequency.get(word));
        }

        scanner.close();
    }
}
