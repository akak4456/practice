package c.inheritance;

public class ParentOverridingPrivate {
    public ParentOverridingPrivate() {
        System.out.println("ParentOverridingPrivateConstructor");
    }
    private void printName() {
        System.out.println("ParentOverridingPrivate - printName()");
    }
}
