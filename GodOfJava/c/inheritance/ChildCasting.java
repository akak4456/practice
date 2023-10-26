package c.inheritance;

public class ChildCasting extends ParentCasting{
    public ChildCasting() {

    }
    public ChildCasting(String name) {

    }
    public void printName() {
        System.out.println("printName() - Child");
    }
    public void printAge() {
        int a = 18;
        System.out.println("printAge() - "+a+" month");
    }
}
