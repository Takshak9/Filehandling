package file_handlling_utility;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileHandlingDemo {
    private static final String FILE_PATH = "example.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("File Handling Program");
        System.out.println("1. Write to File");
        System.out.println("2. Read File");
        System.out.println("3. Modify File");
        System.out.println("Enter your choice:");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> writeFile(scanner);
            case 2 -> readFile();
            case 3 -> modifyFile(scanner);
            default -> System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }

    private static void writeFile(Scanner scanner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            System.out.println("Enter text to write to file:");
            String text = scanner.nextLine();
            writer.write(text);
            writer.newLine();
            System.out.println("Text written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("File Contents:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void modifyFile(Scanner scanner) {
        try {
            System.out.println("Enter text to replace:");
            String oldText = scanner.nextLine();

            System.out.println("Enter new text:");
            String newText = scanner.nextLine();

            String content = Files.readString(Paths.get(FILE_PATH));
            content = content.replaceAll(oldText, newText);
            Files.writeString(Paths.get(FILE_PATH), content);

            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }
}
