package problem053;

import java.math.BigInteger;

public class Problem053 {

    /*
    Combinatoric selections
    Problem 53
    There are exactly ten ways of selecting three from five, 12345:

    123, 124, 125, 134, 135, 145, 234, 235, 245, and 345

    In combinatorics, we use the notation, 5C3 = 10.

    In general,

    nCr = n!/(r!(n−r)!) ,where r ≤ n, n! = n×(n−1)×...×3×2×1, and 0! = 1.
    It is not until n = 23, that a value exceeds one-million: 23C10 = 1144066.

    How many, not necessarily distinct, values of  nCr, for 1 ≤ n ≤ 100, are greater than one-million?
    */
    public static void main(String[] args) {
        
        BigInteger million = new BigInteger("1000000");
        
        int gtMill = 0;
        
        for (int n = 1; n <= 100; n++) {
            for (int r = 0; r <= n; r++) {
                BigInteger comb = nCr(n,r);
                if (comb.compareTo(million) > 0) {
                    System.out.println(n+"C"+r+" =\t" + comb);
                    gtMill++;
                }
            }
        }
        
        System.out.println("\nNumber greater than one million: " + gtMill);
    }
    //Answer: 4075
    
    
    /**
     * Calculates n!
     * @param n
     * @return 
     */
    public static BigInteger factorial(int n) {
        if (n < 0) return BigInteger.ZERO;
        if (n == 0) return BigInteger.ONE;
        
        BigInteger num = new BigInteger(n+"");
        BigInteger res = BigInteger.ONE;
        
        for (BigInteger i = BigInteger.ONE; i.compareTo(num) <= 0; i = i.add(BigInteger.ONE)) {
            res = res.multiply(i);
        }
        
        return res;
        
    }
    
    /**
     * Calculates nCr = n!/(r!(n−r)!)
     * @param n
     * @param r
     * @return 
     */
    public static BigInteger nCr(int n, int r) {
        
        if (r > n) return BigInteger.ZERO;
        
        BigInteger nfact = factorial(n);
        BigInteger rfact = factorial(r);
        BigInteger nMinusrfact = factorial(n-r);
        
        BigInteger res = rfact.multiply(nMinusrfact);
        res = nfact.divide(res);
        
        return res;
    }
    
}
