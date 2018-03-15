package hashtable;

import com.sun.javafx.image.IntPixelGetter;

import java.util.*;

/*
ID:219
Given an array of integers and an integer k, find out whether
there are two distinct indices i and j in the array such
that nums[i] = nums[j] and the absolute difference between
i and j is at most k.
 */
public class ContainsNearbyDuplicate {

    //利用set，滑动窗口
    public static boolean containsNearbyDuplicate(int[] nums, int k){
        if (nums.length < 2 || k < 1)
            return false;
        Set<Integer> record = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            if (record.contains(nums[i]))
                return true;
            record.add(nums[i]);
            if (record.size() > k)
                record.remove(nums[i - k]);
        }
        return false;
    }

    //利用map，存入值以及值的索引，若包含相同键值，比较索引的差值即可
    public static boolean containsNearbyDuplicate2(int[] nums, int k){
        if (nums.length < 2 || k < 1)
            return false;
        Map<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (record.containsKey(nums[i])){
                if (i - record.get(nums[i]) <= k)
                    return true;
            }
            record.put(nums[i], i);
        }
        return false;
    }

    /*
    ID:217
    Given an array of integers, find if the array contains any duplicates.
    Your function should return true if any value appears at least twice
    in the array, and it should return false if every element is distinct.
     */
    public static boolean containsDuplicate(int[] nums) {
        if (nums.length <= 1)
            return false;
        Set<Integer> record = new HashSet<>();
        for (int i : nums) {
            if (record.contains(i))
                return true;
            record.add(i);
        }
        return false;
    }

    /*
    Given an array of integers, find out whether there are two distinct
    indices i and j in the array such that the absolute difference
    between nums[i] and nums[j] is at most t and the absolute difference
    between i and j is at most k.
     */
    //利用sortedtree的函数很简单，还有一种bucket的解法更加好，待跟进
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(k<1 || t<0 || nums==null || nums.length<2) return false;

        SortedSet<Long> set = new TreeSet<Long>();

        for(int j=0; j<nums.length; j++) {
            SortedSet<Long> subSet =  set.subSet((long)nums[j]-t, (long)nums[j]+t+1);
            if(!subSet.isEmpty()) return true;

            if(j>=k) {
                set.remove((long)nums[j-k]);
            }
            set.add((long)nums[j]);
        }
        return false;
    }
    //网上bucket解法
    public static boolean containsNearbyAlmostDuplicateImprove(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket) ||
                    (map.containsKey(bucket - 1) &&
                            remappedNum - map.get(bucket - 1) <= t) ||
                    (map.containsKey(bucket + 1) &&
                            map.get(bucket + 1) - remappedNum <= t)) {
                return true;
            }
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }
}

