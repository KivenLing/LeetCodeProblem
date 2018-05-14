package recall;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiven Ling
 * 2018/5/14 14:58
 */
public class LetterCombinations {
    private static final String[] DIGIT_TO_LETTER = {
            " ", "", "abc", "def", "ghi", "jkl", "mno",
            "pqrs", "tuv", "wxyz"
    };
    /**
     * ID: 17
     * Given a string containing digits from 2-9 inclusive,
     * return all possible letter combinations that the number
     * could represent.
     * A mapping of digit to letters (just like on the telephone buttons)
     * is given below. Note that 1 does not map to any letters.
     */
    //迭代方法
    public static List<String> letterCombinations(String digits){
        if (digits == null || digits.length() <= 0){
            return null;
        }
        List<String> letters = new ArrayList<>();
        for (char digit : digits.toCharArray()) {
            String toLetter = DIGIT_TO_LETTER[digit - '1'];
        }
        //todo
        return null;
    }

    //递归
    public static List<String> letterCombinationsII(String digits){
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() <= 0){
            return res;
        }
        findCombination(res, digits, 0, "");
        return res;
    }

    /**
     *  pre中保存了此时从digits[0...index-1]翻译得到的一个字母字符串
     *  寻找和digits[index]匹配的字母, 获得digits[0...index]翻译得到的解
     */
    private static void findCombination(List<String> res, String digits,
                                            int index, String pre){
        if (index == digits.length()){
            res.add(pre);
            return;
        }
        String letters = DIGIT_TO_LETTER[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            findCombination(res, digits, index + 1, pre + letters.charAt(i));
        }
    }
}
