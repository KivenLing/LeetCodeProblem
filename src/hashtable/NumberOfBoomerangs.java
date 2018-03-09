package hashtable;

import java.util.HashMap;
import java.util.Map;

/*
ID:447
Given n points in the plane that are all pairwise distinct,
a "boomerang" is a tuple of points (i, j, k) such that the
distance between i and j equals the distance between i and
k (the order of the tuple matters).

Find the number of boomerangs. You may assume that n will \
be at most 500 and coordinates of points are all in the
range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]

Output:
2

Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */
public class NumberOfBoomerangs {
    public static int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        int length =  points.length;
        Map<Integer, Integer> dis = new HashMap<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j == i)
                    continue;
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int disTo = dx * dx + dy * dy;
                dis.put(disTo, dis.getOrDefault(disTo, 0) + 1);
            }
            //下面操作可以在上述循环完成，减少一次遍历操作，优化时间
            for (int count : dis.values())
                ans += count * (count - 1);
            dis.clear();
        }
        return ans;
    }
}
