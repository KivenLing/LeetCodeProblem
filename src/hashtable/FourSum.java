package hashtable;

import java.util.*;

/*
4 sum problem I II
 */
public class FourSum {
/*
ID: 454
Given four lists A, B, C, D of integer values,
compute how many tuples (i, j, k, l) there are
such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have
same length of N where 0 ≤ N ≤ 500. All integers
are in the range of -228 to 228 - 1 and the result
is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] =
1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] =
2 + (-1) + (-1) + 0 = 0
 */

    public  static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int arrLength = A.length;
        Map<Integer, Integer> sumOfCD = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < arrLength; i++) {
            for (int j = 0; j < arrLength; j++) {
                int sum = C[i] + D[j];
                sumOfCD.put(sum, sumOfCD.getOrDefault(sum, 0) + 1);
            }
        }

        for (int i = 0; i < arrLength; i++) {
            for (int j = 0; j < arrLength; j++) {
                int target = -A[i] - B[j];
                ans += sumOfCD.getOrDefault(target, 0);
            }
        }
        return ans;
    }
/*
ID:18
Given an array S of n integers, are there elements
a, b, c, and d in S such that a + b + c + d = target?
Find all unique quadruplets in the array which gives
the sum of target.

Note: The solution set must not contain duplicate quadruplets.

For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int i = 0;
        int size = nums.length;
        //长度不够
        while (i < size - 3){
            int num1 = nums[i];
            //first candidate too large, search finished
            if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target)
                break;
            //first candidate too small
            if(nums[i]+nums[size-1]+nums[size-2]+nums[size-3]<target) {
                do {
                    i++;
                }while (i < size && nums[i] == nums[i - 1]);
                continue;
            }
            int j = i + 1;
            //3sum
            while(j < size - 2) {
                if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target)
                    break;
                if(nums[i]+nums[j]+nums[size-1]+nums[size-2]<target){
                    do {
                        j++;
                    }while (j < size && nums[j] == nums[j - 1]);
                    continue;
                }

                int num2 = nums[j];
                int target2 = target - num1 - num2;
                int l = j + 1;
                int r = size - 1;
                //2sum
                while (l < r) {
                    //移动索引，使和<=target2
                    while (l < r && nums[l] + nums[r] > target2)
                        r--;
                    //移动l索引，使和>= target2
                    while (l < r && nums[l] + nums[r] < target2)
                        l++;
                    if (l < r && nums[l] + nums[r] == target2) {
                        ans.add(Arrays.asList(num1, num2, nums[l++], nums[r--]));
                        while (l < size && nums[l] == nums[l - 1])
                            l++;
                        while (r >= 0 && nums[r] == nums[r + 1])
                            r--;
                    }
                }
                //跳过重复元素
                do {
                    j++;
                }while (j < size && nums[j] == nums[j - 1]);
            }
            //跳过重复元素
            do {
                i++;
            }while (i < size && nums[i] == nums[i - 1]);
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums = {0, 0, 0, 0};
        int target = 1;
        List<List<Integer>> ans = fourSum(nums, target);
        System.out.println(ans);
    }
}
