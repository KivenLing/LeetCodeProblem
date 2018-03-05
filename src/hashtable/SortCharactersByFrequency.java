package hashtable;

import java.util.*;
import util.MapValueComparator;

/*
ID: 451
Given a string, sort it in decreasing order based on the
frequency of characters.

Example:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr"
is also a valid answer.
 */
public class SortCharactersByFrequency {

    public static String frequencySort(String s) {
        if (s == null || "".equals(s))
            return "";
        char[] res = new char[s.length()];
        Map<Character, Integer> charFre = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (!charFre.containsKey(c)){
                charFre.put(c, 1);
            }else {
                charFre.put(c, charFre.get(c) + 1);
            }
        }
        charFre = sortMapByValue(charFre);
        int resIndex = 0;
        for (Map.Entry<Character, Integer> mapEntry: charFre.entrySet()) {
            for (int i = 0; i < mapEntry.getValue(); i++) {
                res[resIndex++] = mapEntry.getKey();
            }
        }
        return String.valueOf(res);
    }
    private static Map<Character, Integer> sortMapByValue(Map<Character,
            Integer> map){
        if (map == null || map.isEmpty())
            return null;
        List<Map.Entry<Character, Integer>> sortList = new
                ArrayList<>(map.entrySet());

        Collections.sort(sortList, new MapValueComparator());
        Map<Character, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> mapEntry: sortList) {
            sortedMap.put(mapEntry.getKey(), mapEntry.getValue());
        }
        return sortedMap;
    }

    public static void main(String[] args){
        String s = "";
        System.out.println(frequencySort(s));
    }
}


