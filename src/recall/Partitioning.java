package recall;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiven Ling
 * 2019/1/10 11:49
 * ID: 131
 */

/**
 * ID: 131
 * Given a string s, partition s such that every substring of the partition is
 * a palindrome.Return all possible palindrome partitioning of s.
 * Example:
 * Input: "aab"
 * Output:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 */
public class Partitioning {
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return  res;
        findPartition(res, 0,  s, new ArrayList<String>());
        return res;
    }

    /**
     *
     * @param index 从s[index]开始找回文串
     * @param s 被查找的对象
     * @param combination 存储已经找到的回文串
     */
    private static void findPartition(List<List<String>> res, int index, String s, List<String > combination){
        int sLen = s.length();
        if (index == sLen){
            res.add(combination);
            return;
        }
        //寻找s[index ... i)是否为回文串
        for (int i = index + 1; i <= sLen; i++) {
            String part = s.substring(index, i);
            if (isPalindrome(part)){
                List<String> newCombination = new ArrayList<>(combination);
                newCombination.add(part);
                findPartition(res, i, s, newCombination);
            }
        }
    }
    //查看s是否是回文字符串
    private static boolean isPalindrome(String s){
        int font = 0;
        int rear = s.length() - 1;
        while (font < rear){
            if (s.charAt(font) != s.charAt(rear))
                return false;
            font++;
            rear--;
        }
        return true;
    }
}
