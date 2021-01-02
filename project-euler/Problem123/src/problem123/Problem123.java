package problem123;

import java.math.BigInteger;
import java.util.List;
import mathtools.MF;


public class Problem123 {

   
    /*
    Let pn be the nth prime: 2, 3, 5, 7, 11, ..., and let r be the remainder when (pn−1)^n + (pn+1)^n is divided by pn^2.

    For example, when n = 3, p3 = 5, and 43 + 63 = 280 ≡ 5 mod 25.

    The least value of n for which the remainder first exceeds 109 is 7037.

    Find the least value of n for which the remainder first exceeds 1010.
    */
    public static void main(String[] args) {
        BigInteger max = BigInteger.TEN.pow(10);
        List<Long> primes = MF.getPrimesUnder(1000000);
        
        System.out.println("primes: " + primes.size());
        System.out.println("");
        
        
        for (int i = 0 ;i < primes.size(); i++) {
            BigInteger r = getR(primes.get(i), i+1);
            if (r.compareTo(max) > 0) {
                System.out.println("n="+(i+1));
                System.out.println("p="+primes.get(i));
                System.out.println("r="+r);
                break;
            }
        }
        System.out.println("Done");
    }
    
    public static BigInteger getR(long prime, int n) {
        BigInteger p = new BigInteger(""+prime);
        BigInteger psqr = p.pow(2);
        
        BigInteger res = p.subtract(BigInteger.ONE).pow(n);
        res = res.add(p.add(BigInteger.ONE).pow(n));
        
        res = res.mod(psqr);
        
        return res;
        
    }
}
/*
n=21035
p=237737
r=10001595590
*/