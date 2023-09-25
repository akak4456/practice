package d.collection.practice;

import java.util.ArrayList;
public class ManageHeight {
    ArrayList<ArrayList<Integer>> gradeHeights;
    public static void main(String[] args) {
        ManageHeight manage = new ManageHeight();
        manage.setData();
        for(int i=1;i<=5;i++) {
            System.out.println("Class No.:"+i);
            manage.printAverage(i);
        }
    }

    public void setData() {
        gradeHeights = new ArrayList<>();
        for(int i=0;i<5;i++) {
            gradeHeights.add(new ArrayList<>());
        }
        gradeHeights.get(0).add(170);
        gradeHeights.get(0).add(180);
        gradeHeights.get(0).add(173);
        gradeHeights.get(0).add(175);
        gradeHeights.get(0).add(177);

        gradeHeights.get(1).add(160);
        gradeHeights.get(1).add(165);
        gradeHeights.get(1).add(167);
        gradeHeights.get(1).add(186);

        gradeHeights.get(2).add(158);
        gradeHeights.get(2).add(177);
        gradeHeights.get(2).add(187);
        gradeHeights.get(2).add(176);

        gradeHeights.get(3).add(173);
        gradeHeights.get(3).add(182);
        gradeHeights.get(3).add(181);

        gradeHeights.get(4).add(170);
        gradeHeights.get(4).add(180);
        gradeHeights.get(4).add(165);
        gradeHeights.get(4).add(177);
        gradeHeights.get(4).add(172);
    }

    public void printHeight(int classNo) {
        for(int height : gradeHeights.get(classNo - 1)) {
            System.out.println(height);
        }
    }

    public void printAverage(int classNo) {
        double sum = 0.0;
        for(int height : gradeHeights.get(classNo - 1)){
            sum += height;
        }
        System.out.println("Height average:" + (sum / gradeHeights.get(classNo - 1).size()));
    }
}