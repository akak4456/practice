package beyond;

public class ParentPackagePrivate {
    public ParentPackagePrivate() {
        System.out.println("Parent called");
    }
    protected int value;
    protected int getValue() {
        return value;
    }
}
