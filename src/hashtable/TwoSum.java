package hashtable;

import util.KeyToValue;

import java.util.*;

/*
ID：1
Given an array of integers, return indices of the
two numbers such that they add up to a specific target.

You may assume that each input would have exactly
one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

 */
public class TwoSum {
    //利用对撞指针
    //对原有nums排序，并维护索引值
    public static int[]  twoSumByTwoIndex(int[] nums, int target) {
        int[] ans = new int[2];
        List<KeyToValue> numAndIndex = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            numAndIndex.add(new KeyToValue(nums[i], i));
        }
        Collections.sort(numAndIndex);
        int l = 0, r = nums.length - 1;
        int index = 0;
        while (l < r){
            if (numAndIndex.get(l).getKey() +
                    numAndIndex.get(r).getKey() == target){
                ans[0] = numAndIndex.get(l).getValue();
                ans[1] = numAndIndex.get(r).getValue();
                break;
            }else if (numAndIndex.get(l).getKey() +
                    numAndIndex.get(r).getKey() > target){
                r--;
            }else {
                l++;
            }
        }
        return ans;
    }

    //利用图解决
    public static int[] twoSumByMap(int[] nums, int target) {
        int[] ans = new int[2];
        Map<Integer, Integer> numAndIndex = new HashMap<>();
        numAndIndex.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            int another = target - nums[i];
            if (numAndIndex.containsKey(another)){
                ans[0] = numAndIndex.get(another);
                ans[1] = i;
                break;
            }else {
                numAndIndex.put(nums[i], i);
            }
        }
        return ans;
    }
    
}
