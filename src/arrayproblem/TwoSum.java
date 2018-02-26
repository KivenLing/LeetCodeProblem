package arrayproblem;
/*
Given an array of integers that is already sorted in
ascending order, find two numbers such that they add
up to a specific target number.

The function twoSum should return indices of the two
numbers such that they add up to the target, where
index1 must be less than index2. Please note that
your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution
and you may not use the same element twice.
 */
public class TwoSum {
    private TwoSum(){}
    //暴力解法 O(n*n)
    public static int[] twoSum(int[] numbers, int target) {
        int index1 = 0;
        int index2 = 1;
        int[] index = new int[2];
        for( ; index1 < numbers.length - 1; index1++){
            for( ; index2 < numbers.length ; index2++){
                if (numbers[index1] + numbers[index2] == target){
                    index[0] = index1 + 1;
                    index[1] = index2 + 1;
                    return  index;
                }else if (numbers[index1] + numbers[index2] > target)
                    break;
            }
        }
        return index;
    }

    //利用二分搜索O(nlogn)
    public static int[] twoSum2(int[] numbers, int target){
        int index1 = 0;
        int index2 = 1;
        int[] index = new int[2];
        for ( ; index1 < numbers.length - 1; index1++) {
            int l = index1 + 1;
            int r = numbers.length - 1;
            while (l <= r){
                int mid = l + (r - l) / 2;
                if (target - numbers[index1] == numbers[mid]){
                    index[0] = index1 + 1;
                    index[1] = mid + 1;
                    return index;
                }
                if (target - numbers[index1] > numbers[mid])
                    l = mid + 1;
                else
                    r = r = mid - 1;
            }
        }
        return index;
    }

    //对撞指针，O(n)
    public static int[] twoSum3(int[] numbers, int target){
        int index1 = 0;
        int index2 = numbers.length - 1;
        int[] index = new int[2];
        //对于index1，index2的移动还可以进行优化
        while (index1 < index2){
            if (numbers[index1] + numbers[index2] > target)
                index2--;
            else if (numbers[index1] + numbers[index2] < target)
                index1++;
            else{
                index[0] = index1 + 1;
                index[1] = index2 + 1;
                return index;
            }
        }
        return index;
    }

    public static void main(String[] args){
        int[] numbers = {2, 3, 4};
        int target = 6;
        int[] ret = twoSum2(numbers, target);
        System.out.print(ret[0] + "  " + ret[1]);
    }
}
