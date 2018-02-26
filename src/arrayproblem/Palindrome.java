package arrayproblem;

import java.util.ArrayList;

/*
Given a string, determine if it is a palindrome,
considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.
 */
public class Palindrome {
    public static boolean isPalindrome(String s){
        char[] sentence = s.toLowerCase().toCharArray();
        ArrayList<Character> sentenceIgnoreChar =
                new ArrayList<Character>(sentence.length);
        for (char c: sentence) {
            if (isAlphanumericCharacters(c))
                sentenceIgnoreChar.add(c);
        }
        int l = 0, r = sentenceIgnoreChar.size() - 1;
        while (l < r){
            if (sentenceIgnoreChar.get(l).equals(sentenceIgnoreChar.get(r))){
                l++;
                r--;
            }else
                return false;
        }
        return true;
        /*
        性能不够好，表现在依赖了java自带的转换函数，多次扫描了s，其实扫描一遍即可
         */
    }

    private static boolean isAlphanumericCharacters(char c){
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))
            return true;
        return false;
    }
}
