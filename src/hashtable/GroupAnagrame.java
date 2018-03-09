package hashtable;

import java.util.*;

/*
ID：49
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.
 */
public class GroupAnagrame {

    //思路1：在判断anagrame上，对string先排序，然后存入map中。
    //这样只要是相同anagrame，字母出现顺序相同，时间消耗在字符串排序上
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        int length = strs.length;
        Map<String, List<String>> anaGroup = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char[] sChars = strs[i].toCharArray();
            Arrays.sort(sChars);
            String str = String.valueOf(sChars);
            if (anaGroup.containsKey(str)){
                anaGroup.get(str).add(strs[i]);
            } else {
                List<String> tempList = new ArrayList<>();
                tempList.add(strs[i]);
                anaGroup.put(str, tempList);
            }
        }
        for (Map.Entry<String, List<String>> mapEntry : anaGroup.entrySet()) {
            ans.add(mapEntry.getValue());
        }
        return ans;
    }

    //leetcode时间最短解
    //利用素数的性质，任意两组素数，组内相乘，结果相等的充分必要条件是两个素数集合相等。
    //利用上述性质，将字母映射到素数，判断是否为anagrame，非常巧妙
    public List<List<String>> groupAnagramsInSolution(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0){
            return result;
        }
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};


        Map<Integer, List<String>> map = new HashMap<Integer,List<String>>();
        for (String st: strs) {
            char[] str = st.toCharArray();
            int key = 1;
            for (int i = 0; i < str.length; i++) {

                key *= prime[str[i]-'a'];
            }
            List<String> list;
            if (map.containsKey(key)){
                list = map.get(key);
            } else {
                list = new ArrayList<String>();
                result.add(list);
                map.put(key,list);
            }
            list.add(st);

        }
        return result;
    }
}
