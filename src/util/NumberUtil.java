package util;

import java.util.Set;

/*
some util to solve number problem
 */
public class NumberUtil {
    private NumberUtil() {
    }

    //判断素数，并保存在集合中，使用于处理判断大量的数字，含有递归，自动更新primeSet
    public static boolean isPrime(int num, Set<Integer> primeSet) {
        if (primeSet.contains(num))
            return true;
        int end = (int) Math.sqrt(num);
        int lastPrime = 2;
        for (int tempPrime : primeSet) {
            if (tempPrime > end)
                break;
            if (num % tempPrime == 0)
                return false;
            lastPrime = tempPrime;
        }
        //update primeSet
        for (int i = lastPrime + 2; i <= end; i += 2) {
            if (isPrime(i, primeSet)) {
                if (num % i == 0)
                    return false;
            }
        }
        primeSet.add(num);
        return true;
    }
}