package arrayproblem;
/*
给定一个整型数组和一个数字s，找到数组中最短的连续子数组
是的元素之和大于等于s，返回这个连续子数组的长度值
Given an array of n positive integers and a
positive integer s, find the minimal length
of a contiguous subarray of which the sum ≥ s.
If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinimumSizeSubarraySum {
    //暴力O(n^3 || n^2)
    //优化sum和的计算
    public static int minSubArrayLen(int s, int[] nums){
        int size = nums.length;
        if (size < 1)//防止传入空数组
            return 0;
        int[] beforeSum = new int[size];//记录nums[start ... end]的和
        beforeSum[0] = nums[0];
        int minLength = 0;//初始化返回长度
        int start = 0;
        int end = 0;
        for ( ;start < size; start++){
            if (start > 0)//维护前几项和
                for (int i = 0; i < size; i++)
                    beforeSum[i] -= nums[start - 1];
            for(end = start; end < size; end++){
                 if (end > 0)
                     beforeSum[end] = beforeSum[end - 1] + nums[end];
                 int length = end - start + 1;
                 if (beforeSum[end] >= s &&
                         (minLength == 0 || end - start + 1 < minLength)) {
                     minLength = end - start + 1;
                     break;
                 }
            }
        }
        return minLength;
    }

    //滑动窗口
    public static int minSubArrayLenImprove(int s, int[] nums){
        int size = nums.length;
        if (size < 1)//防止传入空数组
            return 0;
        //这里可以值用一个值记录，不用数组记录的。
        int[] beforeSum = new int[size];//记录nums[start ... end]的和
        beforeSum[0] = nums[0];
        int minLength = 0;//初始化返回长度
        int start = 0;
        int end = 0;
        while (end >= start && end < nums.length){
            int length = end - start + 1;
            if (beforeSum[end] >= s) {//nums[start ... end] >= s
                if (minLength == 0 || length < minLength)
                    minLength = length;
                beforeSum[end] -= nums[start];//更新beforeSum的值
                start++;
            }else{//nums[start ... end] < s,此时end指针向前移动
                end++;
                if (end >= nums.length)
                    break;
                beforeSum[end] = beforeSum[end - 1] + nums[end];
            }
        }
        return minLength;

    }
    public static void main(String[] args){
        int s = 7;
        int[] nums = {1, 3, 4, 4, 3, 7};
        int length1 = minSubArrayLen(s, nums);
        int length2 = minSubArrayLenImprove(s, nums);
        System.out.println(length1 + " " + length2);
    }
}
