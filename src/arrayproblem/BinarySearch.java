package arrayproblem;

import util.ArrayHelper;

public class BinarySearch {
    public static int binarySearch(int[] arr, int n, int target){
        int l = 0;
        int r = n - 1;//在arr[0-n-1]区间寻找target
        while (l <= r){
            int mid = l + (r - l) / 2;//防止溢出
            if (arr[mid] == target)
                return mid;

            if(arr[mid] < target)
                l = mid + 1;
            else
                r = mid - 1;
        }
        return -1;//表示没有找到结果
    }

    public static void main(String[] args){
        int n = 10000000;
        int[] arr = ArrayHelper.generateOrderArray(n);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++){
            if (i != binarySearch(arr, n, i))
                throw new IllegalStateException("fail find i");
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println(time + "ms");
    }
}
