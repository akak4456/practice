package d.collection;

import java.util.*;

public class TreeMapSample {
    public static void main(String args[]) {
        TreeMapSample sample = new TreeMapSample();
        sample.checkTreeMap();
    }

    public void checkTreeMap() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("A", "a");
        map.put("1", "f");
        map.put("a", "g");
        map.remove("A");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for(Map.Entry<String, String> tempEntry: entries) {
            System.out.println(tempEntry.getKey() + "=" + tempEntry.getValue());
        }
    }
}
