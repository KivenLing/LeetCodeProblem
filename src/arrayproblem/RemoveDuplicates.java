package arrayproblem;
/*
Given a sorted array, remove the duplicates in-place such that
each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this
by modifying the input array in-place with O(1) extra memory.
 */
public class RemoveDuplicates {

    private RemoveDuplicates(){}
    public static int removeDuplicates(int[] nums){
        int distinctIndex = 1;//[0....disdistinctIndex)符合题目要求
        int next = 0;//指向下一个不同元素索引
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] != nums[next]){
                nums[distinctIndex] = nums[i];
                distinctIndex++;
                next = i;
            }
        }
        return distinctIndex;
    }
}
