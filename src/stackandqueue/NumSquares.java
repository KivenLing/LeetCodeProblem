package stackandqueue;

import util.Pair;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kiven Ling
 * @date 2018/4/1 23:31
 * @description ID 279
 *
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4;
 * given n = 13, return 2 because 13 = 4 + 9.
 */
public class NumSquares {
    //solution1 层序遍历图 65ms
    public static int numSquares1(int n){
        List<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(n, 0));
        //关键的一行，对访问重复节点的情况进行排除
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()){
            Pair font = queue.get(0);
            int num = (Integer) font.getKey();
            int step = (Integer)font.getValue();
            queue.remove(0);
            if (num == 0)
                return step;
            for (int i = 1; num - i * i >= 0; i++) {
                if (!visited[num - i * i]) {
                    queue.add(new Pair<>(num - i * i, step + 1));
                    visited[num - i * i] = true;
                }
            }
        }
        throw new IllegalStateException("no solution");
    }

    //solution2 优化，提前判断数字 35ms
    public static int numSquares2(int n){
        List<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(n, 0));
        boolean[] visited = new boolean[n];
        while (!queue.isEmpty()){
            Pair font = queue.get(0);
            int num = (Integer) font.getKey();
            int step = (Integer)font.getValue();
            queue.remove(0);
            /**
             * 将nextNum = num - i * i提出来，减少重复计算
             * 提前判断下个数字，减少不必要判断
             */
            for (int i = 1;; i++) {
                int nextNum = num - i * i;
                if (nextNum < 0)
                    break;
                if (nextNum == 0)
                    return step + 1;
                if (!visited[nextNum]) {
                    queue.add(new Pair<>(nextNum, step + 1));
                    visited[nextNum] = true;
                }
            }
        }
        throw new IllegalStateException("no solution");
    }

    //solution3 DP 47ms
    //数学归纳：x = a + i * i
    public static int numSquares3(int n){
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 1; i * i <= n; i++){
            dp[i * i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0;; j++) {
                int num =  i + j * j;
                if (num > n)
                    break;
                dp[num] = Math.min(dp[i] + 1, dp[num]);
            }
        }
        return dp[n];
    }

    //solution4 四平方定理
    //我还不是很理解
    public static int numSquares4(int n){
        while (n % 4 == 0)
            n = n / 4;
        if (n % 8 == 7)
            return 4;
        for (int i = 0; i * i <= n; i++) {
            int j = (int)Math.sqrt(n - i * i);
            if (i * i + j * j == n)
                return (i == 0 ? 0 : 1) + (j == 0 ? 0 : 1);
        }
        return 3;
    }


    public static void main(String[] args) {
        System.out.println(numSquares1(2));
    }
}

