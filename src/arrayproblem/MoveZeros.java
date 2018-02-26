package arrayproblem;

import java.util.ArrayList;

/*
Given an array nums, write a function to move all 0's to
the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling
 your function, nums should be [1, 3, 12, 0, 0].
 */
public class MoveZeros {
    //需要利用额外空间
    //思路：扫描数组，把非零元素保存，最后赋值，末尾元素补零
    public static void moveZeros1(int[] nums) {
        int size = nums.length;
        ArrayList<Integer> nonZeroElements = new ArrayList<Integer>(size);
        for (int i = 0; i < size; i++) {
            if (nums[i] != 0)
                nonZeroElements.add(nums[i]);
        }
        for (int i = 0; i < nonZeroElements.size(); i++)
            nums[i] = nonZeroElements.get(i);
        for (int i = nonZeroElements.size(); i < size; i++) {
            nums[i] = 0;
        }
    }

    //优化了空间，思路：记录非零元素索引
    public static void moveZeros2(int[] nums) {
        int k = 0;//[0   k)元素为nonzero
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void moveZeros3(int[] nums) {
        int k = 0;//[0   k)元素为nonzero
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != k)
                    swap(nums, i, k);
                k++;
            }
        }
    }

    private static void swap ( int[] arr, int i, int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
    }

    public static  void main(String[] args){
        int[] nums1 = {1, 0, 3, 0, 12};
        int[] nums2 = {1, 0, 3, 0, 12};
        int[] nums3 = {1, 0, 3, 0, 12};
        moveZeros1(nums1);
        moveZeros2(nums2);
        moveZeros3(nums3);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1[i] + " " + nums2[i] + " " + nums3[i]);
        }

    }

}
