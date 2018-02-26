package arrayproblem;

public class Merge {
    private Merge(){}

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;//nums(k.....m+n-1]有序
        int index1 = m - 1;
        int index2 = n - 1;
        while (index1 >= 0 || index2 >= 0) {
            if (index1 < 0 || (index2 >= 0 && nums2[index2] > nums1[index1])) {
                nums1[k] = nums2[index2];
                k--;
                index2--;
            } else {
                nums1[k] = nums1[index1];
                k--;
                index1--;
            }
        }
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n){
        int k = m + n - 1;//nums(k.....m+n-1]有序
        int index1 = m - 1;
        int index2 = n - 1;
        while (index1 >= 0 && index2 >= 0){
            if (nums1[index1] < nums2[index2]){
                nums1[k--] = nums2[index2--];
            }else
                nums1[k--] = nums1[index1--];
        }

    }
    public static void main(String[] args){
        int[] nums1 = new int[5];
        int[] nums2 = new int[3];
        nums1[0] = 0;
        nums1[1] = 1;
        nums2[0] = 2;
        nums2[1] = 3;
        nums2[2] = 4;
        merge(nums1, 2, nums2, 3);
        for (int i : nums1) {
            System.out.print(i + " ");
        }
    }
}
