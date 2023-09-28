package c.javapackage;

// import c.javapackage.sub.SubStatic;
import static c.javapackage.sub.SubStatic.subStaticMethod;
import static c.javapackage.sub.SubStatic.CLASS_NAME;
public class PackageStatic {
    public static void main(String[] args) {
        subStaticMethod();
        System.out.println(CLASS_NAME);
    }

    public static void subStaticMethod() {
        System.out.println("ABC");
    }
}
