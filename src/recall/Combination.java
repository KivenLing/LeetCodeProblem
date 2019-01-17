package recall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kiven Ling
 * 2019/1/16 11:33
 * ID: 77, 39, 40, 216
 */
public class Combination {
    /**
     * ID: 77
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
     * Example:
     * Input: n = 4, k = 2
     * Output:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0 || k > n) return res;
        generateCombination(n, k, 1, new ArrayList<>(), res);
        return res;
    }

    private static void generateCombination(int n, int k, int start,
                                            List<Integer> store, List<List<Integer>> res){
        int len = store.size();
        if (len == k){
            res.add(new ArrayList<Integer>(store));
            return;
        }

        for (int i = start; i <= n - (k - len) + 1; i++) {
            store.add(i);
            generateCombination(n, k, i + 1, store, res);
            store.remove(len);//len == store.size() - 1
        }
    }

    /**
     * ID: 39
     * Given a set of candidate numbers (candidates) (without duplicates)
     * and a target number (target), find all unique combinations
     * in candidates where the candidate numbers sums to target.
     * The same repeated number may be chosen from candidates unlimited
     * number of times.
     *
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     *
     * Example 1:
     * Input: candidates = [2,3,6,7], target = 7,
     * A solution set is:
     * [
     * [7],
     * [2,2,3]
     * ]
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        findTarget(candidates, target, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private static void findTarget(int[] candidates, int target, int start,
                                   List<Integer> store, List<List<Integer>> res){
        if (target == 0){
            res.add(new ArrayList<>(store));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int digit = candidates[i];
            if (digit <= target){
                store.add(digit);
                findTarget(candidates, target - digit, i, store, res);
                store.remove(store.size() - 1);
            }
        }
    }

    /**
     * ID: 40
     * Given a collection of candidate numbers (candidates)
     * and a target number (target), find all unique combinations
     * in candidates where the candidate numbers sums to target.
     * Each number in candidates may only be used once in the combination.
     *
     * Note:
     * All numbers (including target) will be positive integers.
     * The solution set must not contain duplicate combinations.
     *
     * Example 1:
     * Input: candidates = [10,1,2,7,6,1,5], target = 8,
     * A solution set is:
     * [
     * [1, 7],
     * [1, 2, 5],
     * [2, 6],
     * [1, 1, 6]
     * ]
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        findTarget2(candidates, target, 0, new ArrayList<Integer>(), res);
        return res;
    }

    private static void findTarget2(int[] candidates, int target, int start,
                                    List<Integer> store, List<List<Integer>> res){
        if (target == 0){
            res.add(new ArrayList<>(store));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 跳过重复选择，例如[1, 2, 2, 4]中，第二次选中2，有两种情况：
            // 当已经选了2，应该继续选择，不应该跳过
            // 当第一次选2已经递归结束，选择第二个2时，那么应该跳过(因为有2的答案已经全部选择了)
            // i > start说明在递归树在同一层
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            int digit = candidates[i];
            if (digit <= target){
                store.add(digit);
                findTarget2(candidates, target - digit, i + 1, store, res);
                store.remove(store.size() - 1);
            }
        }
    }

    /**
     * ID: 216
     * Find all possible combinations of k numbers that add up to a number n,
     * given that only numbers from 1 to 9 can be used and each combination
     * should be a unique set of numbers.
     * Note:
     * All numbers will be positive integers.
     * The solution set must not contain duplicate combinations.
     *
     * Example 1:
     * Input: k = 3, n = 7
     * Output: [[1,2,4]]
     *
     * Example 2:
     * Input: k = 3, n = 9
     * Output: [[1,2,6], [1,3,5], [2,3,4]]
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        assert (k > 0 && k < 10);
        List<List<Integer>> res = new ArrayList<>();
        int max = (19 - k) * k / 2;//从1-9选k个数最大和值
        if (n > max) return res;
        findTarget3(k, n, 1, new ArrayList<Integer>(), res);
        return res;
    }

    private static void findTarget3(int k, int n, int start, ArrayList<Integer> store, List<List<Integer>> res) {
        if (n < 0) return;
        if (k > 0){
            for (int i = start; i < 10; i++) {
                store.add(i);
                findTarget3(k - 1, n - i, i + 1 , store, res);
                store.remove(store.size() - 1);
            }
        }else {//k <= 0
            if (n == 0)
                res.add(new ArrayList<>(store));
        }
    }
}
