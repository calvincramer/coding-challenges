package problem087;

import java.math.BigInteger;
import java.util.List;
import mathtools.MF;

/*
The smallest number expressible as the sum of a prime square, prime cube, and prime fourth power is 28. In fact, there are exactly four numbers below fifty that can be expressed in such a way:

2^8 = 2^2 + 2^3 + 2^4
3^3 = 3^2 + 2^3 + 2^4
4^9 = 5^2 + 2^3 + 2^4
4^7 = 2^2 + 3^3 + 2^4

How many numbers below fifty million can be expressed as the sum of a prime square, prime cube, and prime fourth power?
*/
public class Problem087 {

    public static void main(String[] args) {
        
        List<Long> primes = MF.getPrimesUnder( (int)Math.ceil(Math.sqrt(50000000)) );
        boolean[] numsExprs = new boolean[50000000];
        BigInteger intmax = new BigInteger(""+Integer.MAX_VALUE);
        
        for (long p1 : primes) {
        for (long p2 : primes) {
        for (long p3 : primes) {
                    
            BigInteger num = new BigInteger(""+ (p1*p1 + p2*p2*p2 + p3*p3*p3*p3));
            if (num.compareTo(intmax) < 0) {
                int n = num.intValue();
                if (n >= 0 && n < 50000000)
                    numsExprs[n] = true;
            }
                    
        }}}
        
        int total = 0;
        for (boolean b : numsExprs)
            if (b)
                total++;
        
        System.out.println("total: " + total);
    }

}
//1097343