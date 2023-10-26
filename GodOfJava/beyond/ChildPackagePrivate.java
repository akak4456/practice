package beyond;

import beyond.ParentPackagePrivate;
import c.inheritance.Child;

public class ChildPackagePrivate extends ParentPackagePrivate {
    public ChildPackagePrivate() {
        System.out.println("Child called");
    }
}
