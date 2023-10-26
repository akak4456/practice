public class ReferenceReturn {
    public static void main(String args[]) {
        ReferenceReturn reference = new ReferenceReturn();
        System.out.println(reference.intReturn());
        System.out.println(reference.intArrayReturn());
        System.out.println(reference.stringReturn());
        reference.wantToStopInTheMiddle(false);
    }
    public int intReturn() {
        int returnInt = 0;
        return returnInt;
    }
    public int[] intArrayReturn() {
        int returnArray[] = new int[10];
        return returnArray;
    }

    public String stringReturn() {
        String returnString = "Return value";
        return returnString;
    }

    public void wantToStopInTheMiddle(boolean flag){
        if(flag) return;
        System.out.println("ABC");
    }
}
