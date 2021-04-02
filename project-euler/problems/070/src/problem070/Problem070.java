package problem070;

import java.util.List;
import mathtools.MF;

public class Problem070 {

    /*
    Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of positive numbers less than or equal to n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and relatively prime to nine, φ(9)=6.
    The number 1 is considered to be relatively prime to every positive number, so φ(1)=1.

    Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.

    Find the value of n, 1 < n < 10^7, for which φ(n) is a permutation of n and the ratio n/φ(n) produces a minimum.
    */
    public static void main(String[] args) {
        
        long lowN = 0;
        long lowPhi = 1;
        double lowPer = Double.MAX_VALUE;
        
        System.out.println("n/phi");
        
        for (long n = 2; n < 10000000; n++) {
            long phi = eulersTotientFunction(n);
            if (isPermutationOf(n, phi)) {
                if (n * 1.0 / phi < lowPer) {
                    lowPer = n * 1.0 / phi;
                    lowN = n;
                    lowPhi = phi;
                    System.out.println(n + "/" + phi + "\t= " + lowPer );
                }
            }
        }
    }
    
    public static long eulersTotientFunction(long num) {
        
        if (num < 1)
            return -1;
        if (num == 1)
            return 1;
        
        List<Long> distinctPrimeFactors = MF.getPrimeFactorsOfFast(num);
        long prodTop = 1;
        long prodBottom = 1;
        for (long n : distinctPrimeFactors) {
            prodTop *= n-1;
            prodBottom *= n;
        }
        
        return num * prodTop / prodBottom;
    }
    
    public static boolean isPermutationOf(long num1, long num2) {
        
        char[] n1 = (num1+"").toCharArray();
        char[] n2 = (num2+"").toCharArray();
        
        if (n1.length != n2.length)
            return false;
        
        MF.quickSort(n1);
        MF.quickSort(n2);
        
        for (int i = 0; i < n1.length; i++)
            if (n1[i] != n2[i])
                return false;
        
        return true;
    }

}
//answer: 8319823
//run time: 830 minutes :(