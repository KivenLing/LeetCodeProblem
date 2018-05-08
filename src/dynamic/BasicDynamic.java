package dynamic;

/**
 * @author Kiven Ling
 * 2018/5/7 22:27
 *
 */
public class BasicDynamic {
    /**
     * ID: 70
     * You are climbing a stair case. It takes n steps to reach to the top.
     * Each time you can either climb 1 or 2 steps.
     * In how many distinct ways can you climb to the top?
     * Note: Given n will be a positive integer.
     */
    public int climbStairs(int n){
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[0] = 1;
        for (int i = 2; i <= n; i++)
            memo[i] = memo[i - 1] + memo[i - 2];
        return memo[n];
    }
}
