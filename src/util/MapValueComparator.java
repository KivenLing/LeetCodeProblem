package util;

import java.util.Comparator;
import java.util.Map;
//Map value比较器
public class MapValueComparator implements Comparator<Map.Entry<Character, Integer>> {
    @Override
    public int compare(Map.Entry<Character, Integer> o1,
                       Map.Entry<Character, Integer> o2) {

        return -o1.getValue().compareTo(o2.getValue());
    }
}
