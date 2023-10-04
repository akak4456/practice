package beyond;

public class SwitchSample {
    public static void main(String[] args) {
        SwitchSample sample = new SwitchSample();
        sample.intSwitch(1);
        sample.intSwitch2(1);
        sample.stringSwitch("Hello");
    }
    public void intSwitch(int value) {
        switch (value) {
            case 1:
                System.out.println("intValue 1");
                break;
            case 2:
                System.out.println("intValue 2");
                break;
            case 3:
                System.out.println("intValue 3");
                break;
            default:
                System.out.println("intValue default");
        }
    }

    public void intSwitch2(int value) {
        switch (value) {
            case 10:
                System.out.println("intValue2 10");
                break;
            case 1:
                System.out.println("intValue2 1");
                break;
            case 100:
                System.out.println("intValue2 100");
                break;
            case 1000:
                System.out.println("intValue2 1000");
                break;
            default:
                System.out.println("intValue2 default");
        }
    }

    public void stringSwitch(String value) {
        switch (value) {
            case "Hello":
                System.out.println("hello");
                break;
            case "World":
                System.out.println("world");
                break;
            case "Java":
                System.out.println("java");
                break;
            default:
                System.out.println("default");
        }
    }
}
