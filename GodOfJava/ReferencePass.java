public class ReferencePass {
    public static void main(String args[]) {
        ReferencePass reference = new ReferencePass();
        reference.callPassByValue();
    }

    public void callPassByValue() {
        int a = 10;
        String b = "b";
        passByValue(a, b);
    }
    public void passByValue(int a, String b) {
        a = 20;
        b = "z";
    }
}
