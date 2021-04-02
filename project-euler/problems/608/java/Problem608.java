package problem608;

import java.util.List;
import mathtools.MF;

public class Problem608 {

    /*
    Let D(m,n)=∑d|m∑k=1nσ0(kd)D(m,n)=∑d|m∑k=1nσ0(kd) where dd runs through all divisors of mm and σ0(n)σ0(n) is the number of divisors of nn.
    You are given D(3!,102)=3398D(3!,102)=3398 and D(4!,106)=268882292D(4!,106)=268882292.

    Find D(200!,1012) mod (109+7)D(200!,1012) mod (109+7).
    */

    public static void main(String[] args) {

        for (long n = 0; n < 100; n++) {
            if (sigma0(n) != sigma02(n))
                System.out.println(n + "\t" + sigma0(n) + "\t" + sigma02(n));
        }
        
        //MF.printList(MF.getFactorsOfFaster(24L, true, true));
        //System.out.println("D(3!,10^2) = " + D(6,100));
        //System.out.println("D(4!,10^6) = " + D(4*3*2,(long)Math.pow(10, 6)));
    }
    
    public static long D(long m, long n) {
        long total = 0;
        List<Long> divisors = MF.getFactorsOfFaster(m, true, true);
        for (long d : divisors) {
            for (long k = 1; k <= n; k++) {
                total += sigma0(k*d);
            }
        }
        return total;
    }
    
    
    public static long sigma0(long n) {
        return MF.getFactorsOfFaster(n, true, true).size();
    }
    
    public static long sigma02(long n) {
        long factors = 0;
        if (n == 0)
            return 0;
        if (n < 0)
            n *= -1;
        
        while (n != 1) {
            for (long prime : MF.primes2) {
                if (n % prime == 0) {
                    factors += 2;
                    n /= prime;
                    break;
                }
            }
        }
        return factors;
        
    }

}
