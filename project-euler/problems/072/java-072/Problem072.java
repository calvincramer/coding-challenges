package problem072;

import java.util.List;
import mathtools.MF;

public class Problem072 {

    /*
    Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.

    If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

    1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

    It can be seen that there are 21 elements in this set.

    How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?
    */
    
    //public static List<Long> primes = MF.getPrimesUnder(1000000);
    
    public static void main(String[] args) {
        
        long numReducedFractions = 0;
        
        for (int d = 2; d <= 1000000; d++) {
            if (d % 1000 == 0)
                System.out.println("d:"+ d);
            //number of reduced fractions for certain d is equal to eulers totient function 
            // ie number of numerators that are coprime to denom
            numReducedFractions += eulersTotientFunction(d);
        }
        System.out.println("Total: " + numReducedFractions);
        
        
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
//answer: 303963552391