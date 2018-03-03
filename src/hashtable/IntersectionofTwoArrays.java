package hashtable;

import java.util.*;


public class IntersectionofTwoArrays {
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

    /*
    ID:350
    Given two arrays, write a function to compute their intersection.

    Example:
    Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

    Note:
    Each element in the result should appear as many times as it
    shows in both arrays.
    The result can be in any order.
    Follow up:
    What if the given array is already sorted? How would you
    optimize your algorithm?
    What if nums1's size is small compared to nums2's size?
    Which algorithm is better?
    What if elements of nums2 are stored on disk, and the memory
    is limited such that you cannot load all elements into the memory at once?
     */
    //数据结构Map
    public static int[] intersectionII(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums1Map = new HashMap<>();
        for (int element : nums1) {
            if (!nums1Map.containsKey(element))
                nums1Map.put(element, 1);
            else
                nums1Map.put(element, nums1Map.get(element) + 1);
        }
        List<Integer> result = new ArrayList<>();
        for (int element : nums2) {
            if(nums1Map.containsKey(element) && nums1Map.get(element) > 0){
                result.add(element);
                nums1Map.put(element, nums1Map.get(element) - 1);
            }
        }
        int[] results = new int[result.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = result.get(i);
        }
        return results;
    }

    //利用先排序的方法
    public static int[] intersectionII2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0, index2 = 0;
        List<Integer> result = new ArrayList<>();
        while (index1 < nums1.length && 
            index2 < nums2.length){
            if(nums1[index1] < nums2[index2]){
                index1++;
            }else if (nums1[index1] == nums2[index2]) {
                result.add(nums1[index1]);
                index1++;
                index2++;
            }else {
                index2++;
            }
        }
        int[] results = new int[result.size()];
        int i = 0;
        for (int element : result) {
            results[i++] = element;
        }
        return results;
    }

}
