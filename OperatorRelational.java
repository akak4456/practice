public class OperatorRelational {
    public static void main(String args[]) {
        OperatorRelational operator = new OperatorRelational();
        operator.relational();
    }

    public void relational() {
        boolean doBlindDateFlag = false;
        int point = 95;
        doBlindDateFlag = (point >= 80) ? true : false;
    }
}
