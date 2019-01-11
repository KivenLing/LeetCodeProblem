package recall;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiven Ling
 * 2019/1/11 11:29
 * ID: 46 47
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
            res.add(store);
        }
        for (int i = 0; i < numLen; i++) {
            if (!visited[i]){
                visited[i] = true;
                List<Integer> nextStore = new ArrayList<>(store);
                nextStore.add(nums[i]);
                findPermute(res, storeLen + 1, nums, visited, nextStore);
                visited[i] = false;
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
        //todo
        return null;
    }
}
