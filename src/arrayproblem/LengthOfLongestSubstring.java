package arrayproblem;

import java.util.Arrays;

/*
Given a string, find the length of the longest
substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc",
which the length is 3.
 */
public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s){
        //初始化窗口指针
        int l = 0;
        int r = 1;
        int size = s.length();
        if (size <= 0)
            return 0;
        int maxLength = 1;
        int[][] freAndIndex = new int[256][2];//记录字符频率以及第一次出现所在索引
        freAndIndex[s.charAt(0)][0]++;
        freAndIndex[s.charAt(0)][1] = 0;
        int length;
        while(r < size){
            length = r - l + 1;
            freAndIndex[s.charAt(r)][0]++;
            if (freAndIndex[s.charAt(r)][0] > 1) {//窗口内[l....r]有重复元素
                int oldL = l;
                l =  freAndIndex[s.charAt(r)][1] + 1;//新的左索引指向重复元素的下一个位置
                //维护freAndIndex的值
                while (oldL < l)
                    freAndIndex[s.charAt(oldL++)][0]--;
                //freAndIndex[s.charAt(r)][0] = 1;//保证更新重复元素值，上面其实已经维护了，可以省略
                freAndIndex[s.charAt(r)][1] = r;
            } else{//右移没有重复元素
                freAndIndex[s.charAt(r)][1] = r;
                if (length > maxLength)
                    maxLength = length;
            }
            r++;
        }
        return maxLength;
    }

    //对窗口进一步优化，体现在统计字符次数改成记录字符出现的索引
    public static int lengthOfLongestSubstringImprove(String s){
        //初始化窗口指针
        int l = 0;
        int r = 0;
        int size = s.length();
        if (size <= 0)
            return 0;
        int maxLength = 1;
        char[] str = s.toCharArray();
        int[] charIndexOfStr = new int[256];
        Arrays.fill(charIndexOfStr, -1);//初始化值为-1
        int length;
        while(r < size){
            length = r - l + 1;
            int index = charIndexOfStr[str[r]];
            if (l <= index){//说明这个字符曾经出现过
                l = index + 1;//l指向index + 1;
            }else{
                maxLength = Math.max(maxLength, length);
            }
            charIndexOfStr[str[r]] = r;
            r++;
        }
        return maxLength;
    }

    public static void main(String[] args){
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstringImprove(s));
    }

}
