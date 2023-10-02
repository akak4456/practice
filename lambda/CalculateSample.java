package lambda;

public class CalculateSample {
    public static void main(String[] args) {
        CalculateSample sample = new CalculateSample();
        sample.calculateLambda();
    }

    private void calculateLambda() {
        Calculate calculateAdd = (a, b) -> {
            System.out.println(this);
            return a+b;
        };
        System.out.println(calculateAdd.operation(1,2));
    }
}
