package beyond;

import java.sql.DriverManager;
import java.util.ArrayList;

public class ClassLoaderSample {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoaderSample sample = new ClassLoaderSample();
        sample.printClassLoaders();
    }
    public void printClassLoaders() throws ClassNotFoundException {

        System.out.println("Classloader of this class:"
                + ClassLoaderSample.class.getClassLoader());

        System.out.println("Classloader of DriverManager:"
                + DriverManager.class.getClassLoader());

        System.out.println("Classloader of ArrayList:"
                + ArrayList.class.getClassLoader());

        ClassLoader.getSystemClassLoader().loadClass()
    }
}
