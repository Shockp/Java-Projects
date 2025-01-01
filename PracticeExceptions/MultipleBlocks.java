package PracticeExceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultipleBlocks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter an integer: ");
            int number = scanner.nextInt();
            int result = 20 / number;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Not possible to divide by zero.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter an integer.");
        } finally {
            System.out.println("Execution completed.");
        }
        scanner.close();
    }
}
