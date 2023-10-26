package beyond;

public class ObjectSample {
    public static void main(String[] args) {
        ObjectSample thisObject = new ObjectSample();
        thisObject.toStringMethod(thisObject);
    }

    public void toStringMethod(Object obj) {
        System.out.println(obj);
        System.out.println("plus " + obj);
    }
}
