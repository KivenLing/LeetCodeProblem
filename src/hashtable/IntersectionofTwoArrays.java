package hashtable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
ID：349
date:2018.03.03

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.
 */
public class IntersectionofTwoArrays {
    //利用数据结构set
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        for (int element : nums1) {
            nums1Set.add(element);
        }
        List<Integer> result = new ArrayList<>();
        for (int element : nums2) {
           if (nums1Set.contains(element)){
               result.add(element);
               nums1Set.remove(element);
           }
        }
        int[] results = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            results[i] = result.get(i);
        }
        return results;
    }
}
