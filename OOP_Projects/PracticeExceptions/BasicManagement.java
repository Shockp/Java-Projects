package PracticeExceptions;

public class BasicManagement {
    public static void main(String[] args) {
        try {
            int divisor = 0;
            int result = 10 / divisor;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("This always runs");
        }
    }
}
