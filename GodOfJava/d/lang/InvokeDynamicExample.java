package d.lang;

import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class InvokeDynamicExample {
    public static void main(String [] args) throws  Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType type = MethodType.methodType(String.class, String.class, Object[].class);
        MethodHandle mh = lookup.findStatic(String.class, "format", type);

        String s = (String) mh.invokeExact("Hello, %s!", new Object[]{"World"});
        System.out.println(s);
    }
}
