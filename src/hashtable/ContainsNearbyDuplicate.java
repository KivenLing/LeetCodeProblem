package hashtable;

import com.sun.javafx.image.IntPixelGetter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
}
