package arrayproblem;

public class Rorate {
    //with 0(n) time O(1) space
    //ID : 189
    //example : 1 2 3 4 5 6 7 8   k = 3
    //then    : 6 7 8 1 2 3 4 5
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        k = k % nums.length;
        reverse(nums, 0, nums.length-k-1);
        reverse(nums, nums.length-k, nums.length-1);
        reverse(nums, 0, nums.length-1);
    }
    private static void reverse(int[] num, int left, int right) {
        while (left < right) {
            int t = num[left];
            num[left] = num[right];
            num[right] = t;
            left++;
            right--;
        }
    }
}
