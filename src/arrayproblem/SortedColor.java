package arrayproblem;

import util.ArrayHelper;

/*
Given an array with n objects colored red, white or blue,
sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the
color red, white, and blue respectively.
 */
public class SortedColor {
    private SortedColor(){}

    //计数排序
    public static void sortedColor1(int[] nums){
        int[] count = new int[3];
        for(int i = 0; i < nums.length; i++){
            count[nums[i]]++;
        }
        int index = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < count[i]; j++){
                nums[index++] = i;
            }
        }
    }
    //三路快排思路
    public static void sortedColor(int[] nums){
        int i = 0;//遍历数组的索引
        int zero = -1;//表示在[0....zero]下数组都为零
        int two = nums.length;//表示在[two....n - 1]下元素为2
        while (i < two){
            if (nums[i] == 0){
                ArrayHelper.swap(nums, i, zero + 1);
                zero++;
                i++;
            }else if (nums[i] == 1){
                i++;
            }else {//nums[i] == 2
                ArrayHelper.swap(nums, i, two - 1);
                two--;
            }
        }
    }
}
