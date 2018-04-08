package stackandqueue;

import java.util.*;
import util.Pair;

/**
 * @author Kiven Ling
 * 2018/4/8 20:36
 * ID: 347
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n),
 * where n is the array's size.
 */
public class TopKFrequentElements {
    public static List<Integer> topKFrequent(int[] nums, int k) {
    	List<Integer> ans = new ArrayList<>();
    	Map<Integer, Integer> record = new HashMap<>();
    	for (int i : nums) {
    		record.put(i, record.getOrDefault(i, 0) + 1);
    	}
    	//默认小根堆
    	Queue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>(new PairComparator());
        for (Map.Entry<Integer, Integer> entry : record.entrySet()) {
            Pair<Integer, Integer> numFre = new Pair<>(entry.getKey(), entry.getValue());
            priorityQueue.add(numFre);
            if (priorityQueue.size() > k)
                priorityQueue.poll();
        }
        for (Pair<Integer, Integer> pair : priorityQueue) {
            ans.add(pair.getKey());
        }
    	return ans;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        queue.add(3);
        queue.add(4);
        queue.add(1);
        queue.add(5);
        while (!queue.isEmpty())
            System.out.println(queue.poll());
    }
}
class PairComparator implements Comparator<Pair<Integer, Integer>>{

    @Override
    public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
        return o1.getValue() - o2.getValue();
    }
}
/*
leetcode 时间最优
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0 || k == 0) {
            return res;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        int[] buckets = new int[max - min + 1];
        for (int i = 0; i < nums.length; i++) {
            buckets[nums[i] - min]++;
        }
        List[] counting = new ArrayList[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            int sizeOfBucket = buckets[i];
            if (counting[sizeOfBucket] == null) {
                counting[sizeOfBucket] = new ArrayList<Integer>();
            }
            counting[sizeOfBucket].add(i + min);
        }
        for (int i = counting.length - 1; i >= 0; i--) {
            if (counting[i] != null) {
                for (Integer in:(ArrayList<Integer>)counting[i]) {
                    res.add(in);
                    if (res.size() == k) break;
                }
            }
            if (res.size() == k) break;
        }
        return res;
    }
}
*/