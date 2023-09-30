package d.collection;

import java.util.ArrayList;

public class ListSample {
    public static void main(String[] args) {
        ListSample sample = new ListSample();
        //sample.checkArrayList6();
        //sample.checkArrayList7();
        sample.checkArrayList8();
    }
    public void checkArrayList6() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        String[] strList = list.toArray(new String[0]);
        System.out.println(strList[0]);
    }

    public void checkArrayList7() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        String[] tempArray = new String[2];
        String[] strList = list.toArray(tempArray);
        for(String temp: strList) {
            System.out.println(temp);
        }
    }

    public void checkArrayList8() {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");
        list2.add("C");
        list.remove(0);
        list.removeAll(list2);
        System.out.println(list.size());
        list.trimToSize();
    }
}
