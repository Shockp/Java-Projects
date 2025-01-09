package PracticeExceptions;

import java.io.IOException;

public class RethrowingException {
    public static void method() throws IOException {
        throw new IOException("Error while reading the file.");
    }

    public static void main(String[] args) {
        try {
            method();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
