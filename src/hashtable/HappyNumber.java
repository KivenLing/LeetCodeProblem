package hashtable;

import java.util.HashSet;

/*
ID:202
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the
sum of the squares of its digits, and repeat the process until
the number equals 1 (where it will stay), or it loops endlessly
in a cycle which does not include 1. Those numbers for which this
process ends in 1 are happy numbers.

Example: 19 is a happy number

12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1
Credits:
Special thanks to @mithmatt and @ts for adding this problem and
creating all test cases.
 */
public class HappyNumber {
    public static boolean isHappy(int n) {
        if (n < 1)
            return false;
        HashSet<Integer> numSet = new HashSet<>();
        while (n != 1 && !numSet.contains(n)){
            numSet.add(n);
            n = digitSquareSum(n);
        }
        return n == 1;
    }

    private static int digitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }
    public static void main(String[] args){
        int n = 7;
        System.out.println(isHappy(n));
    }
}
