package c.string;

public class StringEquals {
    public static void main(String args[]) {
        String actualString = "baeldung";
        StringBuffer identicalStringInstance = new StringBuffer("baeldung");
        System.out.println(actualString.equals(identicalStringInstance));
        System.out.println(actualString.contentEquals(identicalStringInstance));
    }
}
