package f.switchcase;

public class JDK7Switch {
    public static void main(String args[]) {
        JDK7Switch switchSample = new JDK7Switch();
        System.out.println(switchSample.salaryIncreaseAmount("Engineer"));
    }

    public double salaryIncreaseAmount(String employeeLevel) {
        switch (employeeLevel) {
            case "CEO":
                return 10.0;
            case "Manager":
                return 15.0;
            case "Engineer":
            case "Developer":
                return 100.0;
            case "Designer":
            case "Planner":
                return 20.0;
        }
        return 0.0;
    }
}
