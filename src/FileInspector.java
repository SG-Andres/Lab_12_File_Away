import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileInspector {
    public static void main(String[] args) {
        // create JFileChooser that opens in src directory of project
        JFileChooser fileChooser = new JFileChooser("src");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            processFile(selectedFile);
        } else {
            System.out.println("No file selected.");
        }
    }

    private static void processFile(File file) {
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lineCount++;
                wordCount += line.split("\\s+").length;
                charCount += line.length();
            }
        } catch (IOException e) {
            System.err.println("error reading file: " + e.getMessage());
            return;
        }

        //summary report
        System.out.println("\nsummary report:");
        System.out.println("file name: " + file.getName());
        System.out.println("number of lines: " + lineCount);
        System.out.println("number of words: " + wordCount);
        System.out.println("number of characters: " + charCount);
    }
}