package d.collection;

import java.util.*;

public class MapSample {
    public static void main(String[] args) {
        MapSample sample = new MapSample();
//        sample.checkHashMap();
//        sample.checkKeySet();
//        sample.checkValues();
//        sample.checkHashMapEntry();
        // sample.checkContains();
        sample.checkLinkedHashMap();
    }
    public void checkHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "a");
        System.out.println(map.get("A"));
        System.out.println(map.get("B"));
    }

    public void checkLinkedHashMap() {

        HashMap<String, Integer> linkedHashMap = new HashMap<>();


        linkedHashMap.put("one", 1);
        linkedHashMap.put("three", 3);
        linkedHashMap.put("five", 5);
        linkedHashMap.put("two", 2);
        linkedHashMap.put("six", 2);


        for (Map.Entry<String, Integer> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void checkKeySet() {
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("C", "c");
        map.put("D", "d");
        Set<String> keySet = map.keySet();
        for(String tempKey: keySet) {
            System.out.println(tempKey+ "=" + map.get(tempKey));
        }
    }

    public void checkValues() {
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("C", "c");
        map.put("D", "d");

        Collection<String> values = map.values();
        for(String tempValue: values) {
            System.out.println(tempValue);
        }
    }

    public void checkHashMapEntry() {
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        map.put("D", "d");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for(Map.Entry<String, String> tempEntry: entries) {
            System.out.println(tempEntry.getKey() + "=" + tempEntry.getValue());
        }
    }

    public void checkContains() {
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        map.put("D", "d");

        System.out.println(map.containsKey("A"));
        System.out.println(map.containsKey("Z"));
        System.out.println(map.containsValue("a"));
        System.out.println(map.containsValue("z"));
    }
}
