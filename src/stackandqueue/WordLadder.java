package stackandqueue;

import util.Pair;

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

    //从end推begin 稍微优化了些 726ms
    public static int ladderLengthImprove(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        List<Pair<String, Integer>> queue = new LinkedList<>();
        Set<String> visitedWord = new HashSet<>();
        queue.add(new Pair<>(endWord, 1));
        visitedWord.add(endWord);
        while (!queue.isEmpty()){
            String word = queue.get(0).getKey();
            int length = queue.get(0).getValue();
            queue.remove(0);
            if (isOnePosDiff(word, beginWord))
                return length + 1;
            for (String str : wordList){
                if (visitedWord.contains(str))
                    continue;
                if (isOnePosDiff(word, str)){
                    queue.add(new Pair<>(str, length + 1));
                    visitedWord.add(str);
                }
            }
        }
        return 0;
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

    public static void main(String[] args) {
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
        System.out.println(ladderLengthImprove(begin, end, wordList));
    }
}
/*
leetcode java时间最优解
class Solution {

    private static class WordNode {
        private String word;
        private int numSteps;
        private WordNode(String word, int numSteps) {
            this.word = word;
            this.numSteps = numSteps;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        Set<String> fromBegin = new HashSet<>();
        Set<String> fromEnd = new HashSet<>();
        fromBegin.add(beginWord);
        fromEnd.add(endWord);
        int dist = 1;

        while (!fromBegin.isEmpty() && !fromEnd.isEmpty()) {
            if (fromBegin.size() < fromEnd.size()) {
                if (convert(fromBegin, fromEnd, words)) return dist + 1;
            }else {
                if (convert(fromEnd, fromBegin, words)) return dist + 1;
            }
            dist++;
        }

        return 0;
    }

    private boolean convert(Set<String> cur, Set<String> next, Set<String> words) {
        Set<String> toAdd = new HashSet<>();
        for (String word : cur) {
            char[] chars = word.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char origin = chars[j];
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[j] = c;
                    String newStr = new String(chars);
                    if (next.contains(newStr)) return true;
                    if (words.contains(newStr)) {
                        words.remove(newStr);
                        toAdd.add(newStr);
                    }
                }
                chars[j] = origin;
            }
        }
        cur.clear();
        cur.addAll(toAdd);
        return false;
    }
}
*/