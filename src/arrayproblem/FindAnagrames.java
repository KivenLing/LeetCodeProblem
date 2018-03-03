package arrayproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
ID:438
Given a string s and a non-empty string p,
find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters
only and the length of both strings s and p will
not be larger than 20,100.

The order of output does not matter.
Example:
Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba",
which is an anagram of "abc".

The substring with start index = 6 is "bac",
which is an anagram of "abc".
 */
public class FindAnagrames {

    public static List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length())
            return new ArrayList<Integer>();
        int[] letterFreq = new int[26];//统计字母出现频率
        Arrays.fill(letterFreq, -1);
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        for (int i = 0; i < pChar.length; i++){
            if (letterFreq[pChar[i] - 'a'] == -1)
                letterFreq[pChar[i] - 'a'] = 1;
            else
                letterFreq[pChar[i] - 'a']++;
        }
        int[] viceLetterFreq = Arrays.copyOf(letterFreq, letterFreq.length);
        List<Integer> pos = new ArrayList<Integer>(s.length());
        int l = 0, r = 0;
        int count = 0;
        while (r < sChar.length && l < sChar.length) {
            if (letterFreq[sChar[r] - 'a'] == -1){//当r指向的字母不是p中的字母
                letterFreq = Arrays.copyOf(viceLetterFreq,
                        viceLetterFreq.length);
                while(r < sChar.length && letterFreq[sChar[r] - 'a'] == -1)
                    r++;
                l = r;//此时l，r指向s末尾或者之后第一次包含p中字母的位置
                count = 0;
            }else if (letterFreq[sChar[r] - 'a'] > 0){
                //当r指向的字母是p中的字母,且未重复
                letterFreq[sChar[r] - 'a']--;
                count++;
                if(count == pChar.length){//符合题目条件
                    pos.add(l);
                    count = 0;
                    letterFreq = Arrays.copyOf(viceLetterFreq,
                            viceLetterFreq.length);
                    r = ++l;
                }else{//count < length,说明字母不足
                    r++;
                }
            }else{//letterFreq[sChar[r] - 'a'] == 0
                //说明r指向的字母在p中但是已经频率多了
                while (l < r && sChar[l] != sChar[r]){
                    //寻找重复字母位置
                    //并维护letterFreq, count
                    letterFreq[sChar[l++] - 'a']++;
                    count--;
                }
                //此时l指向重复元素位置
                l++;
                r++;
            }
        }
        return pos;
    }

    //leetcode时间最短解
    public static  List<Integer> findAnagramsImprove(String s, String p) {
        List<Integer> result = new ArrayList();

        int[] counts = new int[128];
        for (char c : p.toCharArray()) {
            counts[c]++;
        }
        int count = p.length();
        int[] sCounts = new int[128];

        final char[] sChars = s.toCharArray();
        int start = 0;
        for (int i = 0; i < sChars.length; i++) {
            final char c = sChars[i];
            sCounts[c]++;
            count--;
            while (sCounts[c] > counts[c]) {
                sCounts[sChars[start++]]--;
                count++;
            }
            if (count == 0) {
                result.add(start);
                sCounts[sChars[start++]]--;
                count++;
            }
        }
        return result;
    }

    public static void main(String[] args){
        String s = "abacbabc";
        String p = "abc";
        for (int i: findAnagrams(s, p)) {
            System.out.println(i);
        }
        for (int i: findAnagramsImprove(s, p)) {
            System.out.println(i);
        }
    }
}
