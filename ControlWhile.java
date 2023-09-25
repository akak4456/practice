public class ControlWhile {
    public static void main(String args[]) {
        ControlWhile control = new ControlWhile();
        control.whileLoop1();
    }

    public void whileLoop1() {
        int loop = 0;
        do {
            loop++;
            System.out.println(loop);
        } while (loop < 12);
    }
}
