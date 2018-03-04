package hashtable;
/*
ID：242
Given two strings s and t, write a function
to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you
adapt your solution to such case?
 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t){
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int[] sCounts = new int[256];
        for (char c : sChars) {
            sCounts[c]++;
        }
        int count = s.length();
        for (char c : tChars) {
            if (sCounts[c] <= 0)
                return false;
            sCounts[c]--;
            count--;
        }
        return count == 0;
    }

    public static void main(String[] args){
        String s = "a";
        String t = "a";
        System.out.println(isAnagram(s, t));
    }
}
