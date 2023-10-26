public class ReferenceOverloading {
    public static void main(String args[]) {
        ReferenceOverloading reference = new ReferenceOverloading();
        reference.print(1);
        reference.print("a");
        reference.print(1,"a");
        reference.print("a",1);
    }
    public void print(int data) {

    }
    public void print(String data) {

    }
    public void print(int intData, String stringData) {

    }

    public void print(String stringData, int intData) {

    }
}
