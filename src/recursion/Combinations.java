package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kiven Ling
 * 2018/4/9 21:28
 * 输出数组所有n个元素的组合
 */
public class Combinations {
    /**
     * list all combinations of nums , the length of combination is n
     * @param nums some numbers
     * @param n the count of combination
     * @return all the combinations
     */
    public static List<List<Integer>> combinations(List<Integer> nums, int n){
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.size() <= n || nums.isEmpty()){
            ans.add(nums);
            return ans;
        }
        if (n == 0){
            return ans;
        }
        List<Integer> selected = new LinkedList<>();
        //选择这0号元素,在剩下元素选n - 1个
        selected.add(nums.get(0));
        combination(ans, selected, nums.subList(1, nums.size()), n - 1);

        //不选择0号元素，剩下选n个
        selected.remove(selected.size() - 1);
        combination(ans, selected, nums.subList(1, nums.size()), n);
        return ans;
    }

    private static void combination(List<List<Integer>> ans, List<Integer> selected,
                                    List<Integer> nums, int n){
        if (n == 0) {
            ans.add(new ArrayList<>(selected));
            return;
        }
        if (nums.isEmpty())
            return;
        //选择这0号元素,在剩下元素选n - 1个
        selected.add(nums.get(0));
        combination(ans, selected, nums.subList(1, nums.size()), n - 1);

        //不选择0号元素，剩下选n个
        selected.remove(selected.size() - 1);
        combination(ans, selected, nums.subList(1, nums.size()), n);
    }

    public static void main(String[] args) {
        List<List<Integer>> ans = combinations(Arrays.asList(1, 2, 3, 4, 5, 6), 4);
        for (List<Integer> list : ans) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
