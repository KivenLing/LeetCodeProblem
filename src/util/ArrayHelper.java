package util;

public class ArrayHelper {
    public static int[] generateOrderArray(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i;
        return arr;
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] generateRandomArray(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random() * n);
        }
        return arr;
    }

    /**
     * 对arr[l...r]进行转置
     */
    public static void reverse(int[] arr, int l, int r){
        while (l < r){
            swap(arr, l, r);
            l++;
            r--;
        }
    }
}
