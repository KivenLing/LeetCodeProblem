package arrayproblem;
/*
ID:76
Given a string S and a string T, find the
minimum window in S which will contain all
the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        int minLength = s.length() + 1;
        int minL = 0, minR = s.length();
        int l = 0, r = 0;
        int count = t.length();
        String minWindows = null;
        char[] tCounts = new char[128];
        for (char c : t.toCharArray()) {
            tCounts[c]++;
        }
        char[] sCounts = new char[128];
        final char[] sChars = s.toCharArray();
        //find first start position
        while (l < s.length()){
            if (tCounts[sChars[l]] == 0){
                l++;
            }else {
                break;
            }
        }
        r = l;
        //此时， l指向的是属于s的字母
        for (; r < sChars.length && l < sChars.length; r++){
            sCounts[sChars[r]]++;
            //说明r指向的字母是t的字符集且字符次数符合
            if (sCounts[sChars[r]] <= tCounts[sChars[r]]) {
                count--;
                while (count == 0) {
                    if (minLength > r - l + 1) {
                        minLength = r - l + 1;
                        minL = l;
                        minR = r;
                    }
                    sCounts[sChars[l]]--;
                    //对于窗口内指向l的元素个数没有t内指向l的个数多
                    //count才可以发生变化
                    if (sCounts[sChars[l]] < tCounts[sChars[l]])
                        count++;
                    l++;
                    //寻找l位置
                    while (l < sChars.length &&
                            sCounts[sChars[l]] > tCounts[sChars[l]]) {
                        sCounts[sChars[l++]]--;
                    }
                }
            }
        }
        if (minLength == s.length() + 1)
            return "";
        return s.substring(minL, minR + 1);
    }

    public static void main(String[] args){
        String s = "bdab";
        String t = "ab";
        System.out.println(minWindow(s,t));
    }
}
