package hashtable;

import java.util.Arrays;

/*
ID: 16
Given an array S of n integers, find three integers in S
such that the sum is closest to a given number, target.
Return the sum of the three integers. You may assume
that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    //我的解答，20ms
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int size = nums.length;
        int i = 0;
        int minDis = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        while (i < size - 2){
            int l = i + 1;
            int r = size - 1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                int dis = Math.abs(sum - target);
                if (sum > target){
                    do {
                        r--;
                    }
                    while (r >= 0 && nums[r] == nums[r + 1]);
                }else if (sum < target){
                    do {
                        l++;
                    }while (l < size && nums[l] == nums[l - 1]);
                }else
                    return target;
                if (dis < minDis){
                    minDis = dis;
                    ans = sum;
                }

            }
            do {
                i++;
            }while (i < size && nums[i] == nums[i - 1]);
        }
        return ans;
    }

    //leetcode上13ms的解
    public static int threeSumClosestInSolution(int[] nums, int target) {
        int result = Integer.MAX_VALUE, n = nums.length;
        if (n > 2){
            // #0-数组排序
            Arrays.sort(nums);
            // #1-计算所有组合中的全局最小值与全局最大值
            int less = nums[0] + nums[1] + nums[2];
            int more = nums[n-3] + nums[n-2] + nums[n-1];
            // #1.1-如果全局最小值比target大
            if (less >= target)
                return less;
            // #1.2-如果全局最大值比target小
            if (more <= target)
                return more;
            // #2-计算以nums[i]开头的组合
            for (int i = 0; i < n - 2; i++){
                // #2.1-计算局部组合之和的最小值与最大值
                int min = nums[i] + nums[i+1] + nums[i+2];
                int max = nums[i] + nums[n-2] + nums[n-1];
                // #2.2-如果最小值比target大，则更新全局最大值
                if (min > target){
                    more = Math.min(more, min);
                    continue;
                }
                // #2.3-如果最大值比target小，则更新全局最小值
                if (max < target){
                    less = Math.max(less, max);
                    continue;
                }
                // #2.4-如果min<=target<=max
                int j = i + 1, k = n - 1;
                while (j < k){
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum > target){
                        more = Math.min(more, sum);
                        while (j < --k && nums[k] == nums[k+1]);
                    } else if (sum < target){
                        less = Math.max(less, sum);
                        while (++j < k && nums[j] == nums[j-1]);
                    } else {
                        return target;
                    }
                }
            }
            if (less == target || more == target)
                return target;
            result = more - target > target - less ? less : more;
        }
        return result;
    }
    public static void main(String[] args){
        int[] nums = {0, 0 , 0};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }
}
