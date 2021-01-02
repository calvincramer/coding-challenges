package problem069;

import java.util.List;
import mathtools.MF;

public class Problem069 {

    /*
    Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of numbers less than n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6.

    n	Relatively Prime	φ(n)	n/φ(n)
    2	1                       1	2
    3	1,2                     2	1.5
    4	1,3                     2	2
    5	1,2,3,4                 4	1.25
    6	1,5                     2	3
    7	1,2,3,4,5,6             6	1.1666...
    8	1,3,5,7                 4       2
    9	1,2,4,5,7,8             6	1.5
    10	1,3,7,9                 4       2.5
    It can be seen that n=6 produces a maximum n/φ(n) for n ≤ 10.

    Find the value of n ≤ 1,000,000 for which n/φ(n) is a maximum.
    */
    public static void main(String[] args) {
        
        long gn = 0;
        double gper = 0;
        long gphi = 0;

        for (long n = 2; n <= 1000000; n++) {
            long phi = eulersTotientFunction(n);
            double perc = n * 1.0 / phi;
            
            if (perc > gper) {
                gper = perc;
                gn = n;
                gphi = phi;
                
                System.out.println(gn + "\t" + gphi + "\t" + gper);
            }
            
        }
        
    }
    
    
    public static long eulersTotientFunction(long num) {
        
        List<Long> distinctPrimeFactors = MF.getPrimeFactorsOfFast(num);
        long prodTop = 1;
        long prodBottom = 1;
        for (long n : distinctPrimeFactors) {
            prodTop *= n-1;
            prodBottom *= n;
        }
        
        return num * prodTop / prodBottom;
    }
    
    public static boolean areRelativelyPrime(long n1, long n2) {
        return gcf(n1,n2) == 1;
    }
    
    public static long gcf(long n1, long n2) {
        if (n1 == 0)
            return n2;

        while (n2 != 0) {
            long r = n1 % n2;
            n1 = n2;
            n2 = r;
        }
        
        return n1;
    }
}

//answer: 510510
