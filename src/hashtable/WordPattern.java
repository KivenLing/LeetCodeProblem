package hashtable;

import java.util.HashMap;

/*
ID: 290
Given a pattern and a string str, find if str
follows the same pattern.

Here follow means a full match, such that there
is a bijection between a letter in pattern and
a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and
str contains lowercase letters separated by a single space.

Credits:
Special thanks to @minglotus6 for adding this problem and
creating all test cases.
 */
public class WordPattern {
    public static boolean wordPattern(String pattern, String str) {
        String[] values = str.split(" ");
        if (pattern.length() != values.length)
            return false;
        HashMap<Character, String> func = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (!func.containsKey(pattern.charAt(i))){
                if(func.values().contains(values[i]))//这一步消耗了时间
                    return false;
                func.put(pattern.charAt(i), values[i]);
            }else{
                if (!values[i].equals(func.get(pattern.charAt(i))))
                    return false;
            }

        }
        return true;
    }
}
