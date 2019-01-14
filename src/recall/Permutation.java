package recall;

import util.ArrayHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Kiven Ling
 * 2019/1/11 11:29
 * ID: 46 47 31
 */
public class Permutation {
    /**
     * ID: 46
     * Given a collection of distinct integers, return all possible permutations.
     * Example:
     * Input: [1,2,3]
     * Output:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            visited[i] = false;
        }
        findPermute(res, 0, nums, visited, new ArrayList<Integer>());
        return res;
    }

    /**
     *
     * @param storeLen 记录保存数字排列的长度
     * @param visited 记录数字有没有被使用
     * @param store 记录已经有的数字排列
     */
    private static void findPermute(List<List<Integer>> res, int storeLen, int[] nums, boolean[] visited,List<Integer> store){
        int numLen = nums.length;
        if (storeLen == numLen){
            res.add(new ArrayList<>(store));
        }
        for (int i = 0; i < numLen; i++) {
            if (!visited[i]){
                visited[i] = true;
                store.add(nums[i]);
                findPermute(res, storeLen + 1, nums, visited, store);
                visited[i] = false;
                store.remove(store.size() - 1);
            }
        }
    }

    /**
     * ID: 47
     * @param nums Given a collection of numbers that might contain duplicates,
     * @return return all possible unique permutations.
     * Example:
     * Input: [1,1,2]
     * Output:
     * [
     * [1,1,2],
     * [1,2,1],
     * [2,1,1]
     * ]
     */
    public static List<List<Integer>> permuteUnique(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        Arrays.fill(visited, false);
        findUniquePermute(res, 0, nums, visited, new ArrayList<>());
        return res;
    }

    private static void findUniquePermute(List<List<Integer>> res, int storeLen, int[] nums,
                                          boolean[] visited, List<Integer> store){
        int len = nums.length;
        if (storeLen == len){
            res.add(new ArrayList<>(store));
            return;
        }
        for (int i = 0; i < len; i++) {
            //确定跳过重复条件
            if (visited[i] || (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1])){
                continue;
            }
            visited[i] = true;
            store.add(nums[i]);
            findUniquePermute(res, storeLen + 1 ,nums, visited, store);
            //回溯，条件重置
            visited[i] = false;
            store.remove(store.size() - 1);
        }
    }

    /**
     * ID: 31
     * Implement next permutation, which rearranges numbers into
     * the lexicographically next greater permutation of numbers.
     * If such arrangement is not possible, it must rearrange it
     * as the lowest possible order (ie, sorted in ascending order).
     * The replacement must be in-place and use only constant extra memory.
     * Here are some examples. Inputs are in the left-hand column and
     * its corresponding outputs are in the right-hand column.
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     * https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
     */
    public static void nextPermutation(int[] nums){
        // https://en.wikipedia.org/wiki/Permutation#Generation_in_lexicographic_order
        // 1.Find the largest index k such that a[k] < a[k + 1].
        // If no such index exists, the permutation is the last permutation.
        // 2.Find the largest index l greater than k such that a[k] < a[l].
        // 3.Swap the value of a[k] with that of a[l].
        // 4.Reverse the sequence from a[k + 1] up to and including the final
        // element a[n].
        int len = nums.length;
        int k = len - 2;
        while (k >=0 && nums[k] >= nums[k + 1]) k--;
        if (k < 0){// no such k
            ArrayHelper.reverse(nums, 0, len - 1);
            return;
        }
        int l = len - 1;
        while (nums[l] <= nums[k]) l--;
        ArrayHelper.swap(nums, k, l);
        ArrayHelper.reverse(nums, k + 1, len - 1);
    }
}
