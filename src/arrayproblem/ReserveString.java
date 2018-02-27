package arrayproblem;

/*
first function:
Write a function that takes a string as
input and returns the string reversed.

Example:
Given s = "hello", return "olleh".

second function
Write a function that takes a string as
input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y".
 */
public class ReserveString {
    public static String reverseString(String s) {
        char[] reserveChar = new char[s.length()];
        for (int i = 0; i < reserveChar.length; i++) {
            reserveChar[i] = s.charAt(s.length() - 1 - i);
        }
        return String.valueOf(reserveChar);
    }

    public static String reverseVowels(String s) {
        int l = 0;
        int r = s.length() - 1;
        char[] reserveStr = new char[s.length()];
        while(l <= r){
            while (l < s.length() && !isVowels(s.charAt(l)))
                reserveStr[l] = s.charAt(l++);

            while (r >= 0 && !isVowels(s.charAt(r)))
                reserveStr[r] = s.charAt(r--);

            //此时l, r指向元音
            if (l > r)
                break;
            reserveStr[l] = s.charAt(r);
            reserveStr[r] = s.charAt(l);
            l++;
            r--;
        }
        return String.valueOf(reserveStr);
    }

    private static boolean isVowels(char ch){
        if(ch == 'a' || ch == 'e' || ch == 'i' ||
                ch == 'o' || ch == 'u' || ch == 'A' ||
                ch == 'E' || ch == 'I' || ch == 'O' ||
                ch == 'U')
            return true;
        return false;
    }

    public static void main(String[] args){
        String s = "HelloO";
        System.out.println(reverseVowels(s));
    }
}
