package hashtable;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

/*
ID：15
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
    // 找到没有过的原因，numList初始化成链表，由于下面的操作包含大量的遍历
    // LinkedList的缺点暴露无遗
    // 484ms
    public static List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Integer> record = new HashMap<>();
        List<Integer> numsList = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i : nums) {
            record.put(i, record.getOrDefault(i, 0) + 1);
            if (record.get(i) == 1)
                numsList.add(i);
        }
        if (record.containsKey(0) && record.get(0) >= 3){
            ans.add(Arrays.asList(0, 0 , 0));
        }
        Collections.sort(numsList);
        //numsList内无重复元素
        int size = numsList.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                int num1 = numsList.get(i);
                int num2 = numsList.get(j);
                if (record.get(num1) > 1 && num1 + num1 + num2 == 0){
                    ans.add(Arrays.asList(num1, num1, num2));
                }

                if (record.get(num2) > 1 && num2 + num2 + num1 == 0){
                    ans.add(Arrays.asList(num1, num2, num2));
                }

                int num3 = -num1 - num2;
                if(record.containsKey(num3) && num3 > num2){
                    ans.add(Arrays.asList(num1, num2, num3));
                }
            }
        }
        return ans;
    }

    //思路二：剔除元素重复度高(>2),
    //先排序
    //取排序后list最后一个元素，找其他元素相加与其和为0，转换成2sum问题
    //接着删除这个元素，这样解决了元素重复的问题，又使寻找更快
    //161ms,比上个方法快些，但是与leetcode时间性能更加优秀的还差了许多
    public static List<List<Integer>> threeSumImprove(int[] nums) {
        Map<Integer, Integer> record = new HashMap<>();
        List<Integer> numsList = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        //剔除元素重复度高(>2)
        for (int i : nums) {
            record.put(i, record.getOrDefault(i, 0) + 1);
            if (record.get(i) <= 2)
                numsList.add(i);
        }
        if (record.containsKey(0) && record.get(0) >= 3){
            ans.add(Arrays.asList(0, 0 , 0));
        }
        Collections.sort(numsList);
        int size = numsList.size();
        while (size >= 3) {
            int lastIndex = numsList.size() - 1;
            int num1 = numsList.remove(lastIndex--);
            int l = 0, r = lastIndex;
            int num2 = Integer.MIN_VALUE;
            int num3 = Integer.MAX_VALUE;
            //2sum问题求解，期间解决重复解问题
            while (l < r){
                int lNum = numsList.get(l);
                int rNum = numsList.get(r);
                if (lNum + rNum > -num1){
                    while (lNum + rNum > -num1){
                        if (r <= 0)
                            break;
                        rNum = numsList.get(--r);
                    }

                }else if (lNum + rNum == -num1){
                    if (lNum != num2 || rNum != num3){
                        num2 = lNum;
                        num3 = rNum;
                        ans.add(Arrays.asList(num1, num2, num3));
                    }
                    l++;
                    r--;
                }else {
                    while (lNum + rNum  < -num1) {
                        if (l >= lastIndex)
                            break;
                        lNum = numsList.get(++l);
                    }
                }
            }
            while (numsList.get(lastIndex) == num1){
                numsList.remove(lastIndex--);
            }
            size = numsList.size();
        }
        return ans;
    }
    //这是leetcode上80ms性能的代码
    //大部分性能高的在于没有用map，以及复杂的数据结构，用最原始的数组
    //思路与思路二类似
    //还有一种思路将答案分为(0, + , -) (+, +, -) (+, -, -)分别讨论
    public static List<List<Integer>> threeSumInSolution(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i>0 && nums[i] == nums[i-1]){
                continue;
            }
            result = twoSum(nums,i+1,nums.length-1,nums[i],result);
        }
        return result;
    }
    private static List<List<Integer>> twoSum(int[] nums,int start,int end,int first,List<List<Integer>> result){
        while (start<end){
            int sum = first+nums[start]+nums[end];
            if (sum == 0){
                List<Integer> item = new ArrayList<>();
                item.add(first);
                item.add(nums[start]);
                item.add(nums[end]);
                result.add(item);
                //跳过重复的数据
                while (start<end && nums[start] == nums[start+1]){
                    start++;
                }
                while (start<end && nums[end] == nums[end-1]){
                    end--;
                }
                start++;
                end--;
                continue;
            }
            if (sum<0){
                //跳过重复的数据
                while (start<end && nums[start] == nums[start+1]){
                    start++;
                }
                start++;
            }
            if (sum>0){
                while (start<end && nums[end] == nums[end-1]){
                    end--;
                }
                end--;
            }
        }
        return result;
    }
    public static void main(String[] args){
        int[] s = {-1, 1, 0, 1, 2, -1, -4};
        System.out.println(threeSumImprove(s));
    }
}