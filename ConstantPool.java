import java.lang.invoke.StringConcatFactory;

public class ConstantPool {
    public void sayHello() {
        String str1 = "HELLO";
        String str2 = "WORLD";
        String str3 = str1 + str2;
        String str4 = str1.concat(str2);
    }
}
