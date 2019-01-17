package recall;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiven Ling
 * 2019/1/17 16:57
 * ID: 78 90
 */
public class Subsets {
    /**
     * ID: 78
     * Given a set of distinct integers, nums, return all possible
     * subsets (the power set).
     * Note: The solution set must not contain duplicate subsets.
     *
     * Example:
     * Input: nums = [1,2,3]
     * Output:
     * [
     * [3],
     * [1],
     * [2],
     * [1,2,3],
     * [1,3],
     * [2,3],
     * [1,2],
     * []
     * ]
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        findSubsets(nums, 0, new ArrayList<>(), res);
        return res;
     }

    private static void findSubsets(int[] nums, int start,
                                    List<Integer> subset, List<List<Integer>> res) {
        res.add(subset);
        for (int i = start; i < nums.length; i++) {
            List<Integer> newSubset = new ArrayList<>(subset);
            newSubset.add(nums[i]);
            findSubsets(nums, i + 1, newSubset, res);
        }
    }

    /**
     * ID: 90
     * nums Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
     * Note: The solution set must not contain duplicate subsets.
     * Example:
     *
     * Input: [1,2,2]
     * Output:
     * [
     * [2],
     * [1],
     * [1,2,2],
     * [2,2],
     * [1,2],
     * []
     * ]
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //todo
        return res;
    }
}
