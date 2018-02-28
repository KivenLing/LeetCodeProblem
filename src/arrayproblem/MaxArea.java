package arrayproblem;
/*
ID:11
Given n non-negative integers a1, a2, ..., an,
where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints
of line i is at (i, ai) and (i, 0). Find two lines,
which together with x-axis forms a container, such
that the container contains the most water.

Note: You may not slant the container and n is at least 2.
 */
public class MaxArea {
    public static int maxArea(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int maxArea = 0;
        while (l < r){
            /*
            此种对于l， r的移动速度慢，改变移动条件可以加速
           int area;
           if (height[l] < height[r])
               area = (r - l) * height[l++];
           else//height[l] >= height[r]
               area = (r - l) * height[r--];
           maxArea = Math.max(area, maxArea);
           */
            int h = Math.min(height[l], height[r]);
            maxArea = Math.max((r - l) * h, maxArea);
            while (l < r && height[l] <= h)
                l++;
            while (l < r && height[r] <= h)
                r--;
        }
        return maxArea;
    }
}
