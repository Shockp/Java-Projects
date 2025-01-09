package PracticeExceptions;

public class MultipleCatch {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]);
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}