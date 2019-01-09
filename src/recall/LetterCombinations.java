package recall;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiven Ling
 * 2018/5/14 14:58
 * ID: 17 93
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

    /**ID: 93
     * Given a string containing only digits, restore it by returning
     * all possible valid IP address combinations.
     *
     * Example:
     * Input: "25525511135"
     * Output: ["255.255.11.135", "255.255.111.35"]
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0 || s.length() > 12)
            return res;
        findIp(res, s, 0, 1, new StringBuffer());
        return res;
    }

    private static void findIp(List<String> res, String ips, int index, int size,StringBuffer preIp){
        //可用ips[index .. end]
        int ipLen = ips.length();
        if (size == 4){
            String ipPart = ips.substring(index, ipLen);
            if (isLegalIpAddrPart(ipPart)){
                preIp.append(ipPart);
                res.add(preIp.toString());
            }
            return;
        }
        //ip 字段不能大于3，且不能到末尾
        //i 表示截断ips，ips[index .. i)
        for (int i = index + 1; i <= index + 3 && i < ipLen; i++) {
            //若果截断后，ips长度大于不可能构成ip地址
            if ((ipLen - i) > (4 - size) * 3) continue;
            String ipPart = ips.substring(index, i);//ips[index .. i)
            if (isLegalIpAddrPart(ipPart)){
                StringBuffer preIpAppend = new StringBuffer(preIp);
                preIpAppend.append(ipPart).append('.');
                findIp(res, ips, i, size + 1, preIpAppend);
            }
        }
    }

    //验证单个ip片段是否合理
    private static boolean isLegalIpAddrPart(String ipPart){
        //ip片段不能已零开头的多位数
        if (ipPart.charAt(0) == '0' && ipPart.length() > 1) return false;
        int ipPartInt = Integer.parseInt(ipPart);
        return ipPartInt >= 0 && ipPartInt <= 255;
    }

    public static void main(String[] args) {
        String s = "0000";
        List<String> ips = restoreIpAddresses(s);
        for (String ip : ips) {
            System.out.println(ip);
        }
    }
}
