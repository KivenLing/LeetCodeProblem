package hashtable;

import java.util.*;

/*
Given an array S of n integers, are there elements
 a, b, c in S such that a + b + c = 0? Find all
 unique triplets in the array which gives the sum of zero.

Note: The solution set must not contain duplicate triplets.

For example, given array S = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
public class ThreeSum {
    // 思路一：遍历数组非重复元素，设为a，通过在数组中除此元素外
    // 其他元素寻找两数和为0 - a的值，找到解
    // 暂时没有规避含有相同解的情况，效率也比较慢，失败
    // 找到回避相同解，但算法复杂度O(n^2);
    public static List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Integer> record = new HashMap<>();
        List<Integer> numsList = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i : nums) {
            if (record.containsKey(i)){
                record.put(i, record.get(i) + 1);
            } else {
                record.put(i, 1);
                numsList.add(i);
            }
        }
        if (record.containsKey(0) && record.get(0) >= 3){
            ans.add(Arrays.asList(0, 0 , 0));
        }
        Collections.sort(numsList);
        //numsList内重复元素除0外最多重复两次，0最多重复三次
        int size = numsList.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                int num1 = numsList.get(i);
                int num2 = numsList.get(j);
                if (record.get(num1) > 1 && num1 + num1 + num2 == 0){
                    ans.add(new ArrayList<>(Arrays.asList(num1, num1, num2)));
                }

                if (record.get(num2) > 1 && num2 + num2 + num1 == 0){
                    ans.add(new ArrayList<>(Arrays.asList(num1, num2, num2)));
                }

                int num3 = -num1 - num2;
                if(record.containsKey(num3) && num3 > num2){
                    ans.add(new ArrayList<>(Arrays.asList(num1, num2, num3)));
                }
            }
        }
        return ans;
    }

    //思路二：剔除元素重复度高(>2),排序利用三个索引来解题
    public static List<List<Integer>> threeSumByThreeIndex(int[] nums) {
        Map<Integer, Integer> record = new HashMap<>();
        List<Integer> numsList = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i : nums) {
            if (record.containsKey(i)){
                record.put(i, record.get(i) + 1);
            } else {
                record.put(i, 1);
                numsList.add(i);
            }
        }
        if (record.containsKey(0) && record.get(0) >= 3){
            ans.add(Arrays.asList(0, 0 , 0));
        }
        Collections.sort(numsList);
        int size = numsList.size();
        int l = 0;
        int r = size - 1;
        return ans;
    }
    public static void main(String[] args){
        int[] s = {-1, 1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(s));
    }
}