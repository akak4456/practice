package c.exception;

public class ExceptionSample {
    public static void main(String[] args) {
        ExceptionSample sample = new ExceptionSample();
        sample.arrayOutOfBounds();
    }

    public void arrayOutOfBounds() {
        try {
            int[] intArray = new int[5];
            System.out.println(intArray[5]);
        } catch (Exception e) {
            System.err.println("Exception occured.");
        }
        System.out.println("This code must run.");
    }
}
