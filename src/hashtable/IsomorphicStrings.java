package hashtable;

/*
ID : 205
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character
while preserving the order of characters. No two characters may map to
the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
 */
public class IsomorphicStrings {
    public static boolean isIsomorphic(String s, String t) {
        char[] map = new char[128];
        boolean[] visited = new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)] == '\0'){
                if (visited[t.charAt(i)])
                    return false;
                map[s.charAt(i)] = t.charAt(i);
                visited[t.charAt(i)] = true;
            }else {
                if (map[s.charAt(i)] != t.charAt(i))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "title";
        String t = "paper";
        System.out.println(isIsomorphic(s, t));
    }
}
