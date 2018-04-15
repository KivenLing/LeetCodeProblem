package stackandqueue;

import util.Pair;
import util.PrintUtil;
import util.ReadCase;

import java.util.*;

/**
 * @author Kiven Ling
 * 2018/4/8 0:31
 *
 * ID 127 126
 */
public class WordLadder {
    /*
     * ID:127
     * Given two words (beginWord and endWord), and a dictionary's word list,
     * find the length of shortest transformation sequence from beginWord to endWord, such that:

     * Only one letter can be changed at a time.
     * Each transformed word must exist in the word list. Note that beginWord
     * is not a transformed word.
     * For example,

     * Given:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * return its length 5.

     * Note:
     * Return 0 if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * You may assume no duplicates in the word list.
     * You may assume beginWord and endWord are non-empty and are not the same.
     * UPDATE (2017/1/20):
     * The wordList parameter had been changed to a list of strings
     * (instead of a set of strings). Please reload the code definition to get
     * the latest changes.
     */

    /**
     * @return no solution return 0
     * 1024ms 算法效率低
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        List<Pair<String, Integer>> queue = new LinkedList<>();
        Set<String> visitedWord = new HashSet<>();
        queue.add(new Pair<>(beginWord, 1));
        visitedWord.add(beginWord);
        while (!queue.isEmpty()){
            String word = queue.get(0).getKey();
            int length = queue.get(0).getValue();
            queue.remove(0);
            for (String str : wordList){
                if (visitedWord.contains(str))
                    continue;
                if (isOnePosDiff(word, str)){
                    if (str.equals(endWord))
                        return length + 1;
                    queue.add(new Pair<>(str, length + 1));
                    visitedWord.add(str);
                }
            }
        }
        return 0;
    }

    //从end推begin 稍微优化了些 488ms
    public static int ladderLengthImprove(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord))
            return 0;
        List<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(endWord, 1));
        while (!queue.isEmpty()){
            String word = queue.get(0).getKey();
            int length = queue.get(0).getValue();
            queue.remove(0);
            if (isOnePosDiff(word, beginWord))
                return length + 1;
            Set<String> removeSet = new HashSet<>();
            for (String str : words){
                if (isOnePosDiff(word, str)){
                    queue.add(new Pair<>(str, length + 1));
                    removeSet.add(str);
                }
            }
            words.removeAll(removeSet);
        }
        return 0;
    }

    //在寻找下一条路径进行了优化，对每个word，对他每个位置进行a-z的替换
    //时间效率96ms
    public static int ladderLengthImproveII(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord))
            return 0;
        words.add(beginWord);
        Set<String> fromEnd = new HashSet<>();
        fromEnd.add(endWord);
        int step = 1;
        Set<String> nextStep = new HashSet<>();
        while (!fromEnd.isEmpty()){
            nextStep.clear();
            for (String word : fromEnd) {
                char[] origin = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    char c = origin[i];
                    for (char diff = 'a'; diff <= 'z'; diff++){
                        if (diff == c)
                            continue;
                        origin[i] = diff;
                        String nextStr = String.valueOf(origin);
                        if (words.contains(nextStr)){
                            if (nextStr.equals(beginWord))
                                return step + 1;
                            words.remove(nextStr);
                            nextStep.add(nextStr);
                        }
                        origin[i] = c;
                    }
                }
            }
            fromEnd.clear();
            fromEnd.addAll(nextStep);
            step++;
        }
        return 0;
    }

    //对上一个方法进行优化
    //从头尾两边进行层序，哪边一层节点数少，就从哪边层序遍历一层，
    // 这样就减少了许多不必要遍历的开支
    public static int ladderLengthImproveIII(String beginWord, String endWord, List<String> wordList){
        Set<String> words = new HashSet<>(wordList);
        words.add(beginWord);
        if (!words.contains(endWord)) {
            return 0;
        }
        Set<String> fromBegin = new HashSet<>();
        Set<String> fromEnd = new HashSet<>();
        //两个set只保留这一层的节点
        fromBegin.add(beginWord);
        fromEnd.add(endWord);
        int length = 1;
        while(!fromBegin.isEmpty() && !fromEnd.isEmpty()) {
            //判断size大小，让节点数少的进行层序遍历一层，这样就减少了不必要的层序遍历
            if (fromBegin.size() < fromEnd.size()) {
                if (convert(fromBegin, fromEnd, words)) {
                    return length + 1;
                }
            }else {
                if (convert(fromEnd, fromBegin, words)) {
                    return length + 1;
                }
            }
            length++;
        }
        return 0;
    }

    //对begin进行层序遍历一层，并且将遍历的节点替换上一层
    private static boolean convert(Set<String> begin, Set<String> next, Set<String> words){
        Set<String> toAdd = new HashSet<>();
        for (String word : begin) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char origin = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == origin) {
                        continue;
                    }
                    chars[i] = c;
                    String nextStr = new String(chars);
                    if (next.contains(nextStr)) {
                        return true;
                    }
                    if (words.contains(nextStr)) {
                        words.remove(nextStr);
                        toAdd.add(nextStr);
                    }
                    chars[i] = origin;
                }
            }
        }
        begin.clear();
        begin.addAll(toAdd);
        return false;
    }


    //只有一个字母不同，但是顺序可以不同，与题目不符合
    private static boolean isOneDiff(String word1, String word2){
        int count = 0;
        int[] counters = new int[128];
        for (char c : word1.toCharArray()) {
            counters[c]++;
        }

        for (char c : word2.toCharArray()) {
            counters[c]--;
        }
        for (int i : counters) {
            count += Math.abs(i);
        }
        return count == 2;
    }

    //只有一个位置字母不同
    private static boolean isOnePosDiff(String word1, String word2){
        int diff = 0;
        int size = word1.length();
        for (int i = 0; i < size; i++){
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
                if (diff > 1)
                    return false;
            }
        }
        return diff == 1;
    }
    //bfs 解法可以参考：
    //https://github.com/liuyubobobo/Play-Leetcode/tree/master/0127-Word-Ladder/java-0127/src

    /**
     * ID 126
     * the same to 127
     *
     * For example,
     *
     * Given:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * Return
     * [
     * ["hit","hot","dot","dog","cog"],
     * ["hit","hot","lot","log","cog"]
     * ]
     */
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord))
            return ans;
        words.remove(endWord);
        if (words.contains(beginWord))
            words.remove(beginWord);
        Map<String, List<String>> fromBegin = new HashMap<>();
        Map<String, List<String>> fromEnd = new HashMap<>();
        List<String> beginPath = new ArrayList<>();
        beginPath.add(beginWord);
        List<String> endPath = new ArrayList<>();
        endPath.add(endWord);
        fromBegin.put(beginWord, beginPath);
        fromEnd.put(endWord, endPath);
        //bug map键值重复，覆盖原来路径
        while (!fromBegin.isEmpty() && !fromEnd.isEmpty()){
            if (fromBegin.size() < fromEnd.size()){
                if (convert(fromBegin, fromEnd, words, ans, true))
                    return ans;
            }else {
                if (convert(fromEnd, fromBegin, words, ans, false))
                    return ans;
            }
        }
        return ans;
    }

    private static boolean convert(Map<String, List<String>> from, Map<String, List<String>> to, Set<String> words, List<List<String>> ans, boolean isOrder){
        List<String> nodes = new ArrayList<>(from.keySet());
        //最后需要在另外一段删除的节点，
        //       List<String> dest = new LinkedList<>();
        //遍历节点
        for (String node : nodes) {
            char[] nodeChars = node.toCharArray();
            for (int i = 0; i < nodeChars.length; i++){
                char origin = nodeChars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == origin)
                        continue;
                    nodeChars[i] = c;
                    String next = new String(nodeChars);
                    //连接成功
                    if (to.containsKey(next)){
                        List<String> path1 = from.get(node);
                        List<String> path2 = to.get(next);
                        //dest.add(next);
                        ans.add(getPath(path1, path2, isOrder));
                        continue;
                    }
                    //下一个节点
                    if (words.contains(next)){
                        List<String> path = copyStringList(from.get(node));
                        path.add(next);
                        from.put(next, path);
                        words.remove(next);
                    }
                }
                nodeChars[i] = origin;
            }
            from.remove(node);
        }
//        for (String node : dest) {
//            to.remove(node);
//        }
        return ans.size() > 0;
    }

    private static List<String> getPath(List<String> path1, List<String> path2, boolean isOrder){
        List<String> path = new ArrayList<>();
        if (isOrder){
            path.addAll(path1);
            for (int i = path2.size() - 1; i >= 0 ; i--) {
                path.add(path2.get(i));
            }
        }else {
            path.addAll(path2);
            for (int i = path1.size() - 1; i >= 0 ; i--) {
                path.add(path1.get(i));
            }
        }
        return path;
    }

    private static List<String> copyStringList(List<String> src){
        List<String> dest = new ArrayList<>();
        dest.addAll(src);
        return dest;
    }

    public static void main(String[] args) {
        String b = "magic";
        String e = "pearl";
        String testPos = "testcase.txt";
        List<String> words = ReadCase.readTestCase(testPos);
        List<List<String>> ans = findLadders(b, e, words);
        PrintUtil.printListInList(ans);
        String begin = "hit";
        String end = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        wordList.add("los");
        System.out.println(ladderLengthImproveII(begin, end, wordList));
    }
    /*
    magic manic mania maria maris marks parks perks peaks pears pearl
    magic manic mania maria maris paris parks perks peaks pears pearl
    magic manic mania maria marta marty marry parry perry peary pearl
    magic manic mania maria marta marty party parry perry peary pearl

    magic,manic,mania,maria,marta,marty,marry,merry,perry,peary,pearl  */
}