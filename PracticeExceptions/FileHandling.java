package PracticeExceptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandling {
    public static void main(String[] args) {
        Scanner file = null;
        try {
            file = new Scanner(new File("file.txt"));
            while (file.hasNextLine()) {
                System.out.println(file.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        } finally {
            if (file != null) {
                file.close();
                System.out.println("File closed successfully.");
            }
        }
    }
}