public class ReferenceStaticVariable {
    static String name;
    String name2;
    public ReferenceStaticVariable() {}
    public ReferenceStaticVariable(String name) {
        this.name = name;
    }
    public static void main(String args[]) {
        ReferenceStaticVariable reference = new ReferenceStaticVariable();
        reference.checkName();
    }
    public void checkName() {
        ReferenceStaticVariable reference1 = new ReferenceStaticVariable("Sangmin");
        String tmp = reference1.name;
        ReferenceStaticVariable reference2 = new ReferenceStaticVariable("Sungchoon");
        System.out.println(reference1.name);
    }
}
