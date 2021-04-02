package problem047;

import java.util.ArrayList;
import java.util.List;

public class Problem047 {

    /*
    The first two consecutive numbers to have two distinct prime factors are:

    14 = 2 × 7
    15 = 3 × 5

    The first three consecutive numbers to have three distinct prime factors are:

    644 = 2² × 7 × 23
    645 = 3 × 5 × 43
    646 = 2 × 17 × 19.

    Find the first four consecutive integers to have four distinct prime factors each. What is the first of these numbers?
    */
    public static void main(String[] args) {
        final int length = 4;
        int n = 0;
        while (true) {
            //System.out.println(n);
            
            boolean quals = true;
            
            for (int off = 0; off < length; off++) {
                int num = n + off;
                List<Integer> primeFactors = getPrimeFactorsOf(num);
                                     
                if (primeFactors.size() != length) {
                    quals = false;
                    break;
                }
                
            }
            
            if (quals) {
                List<Integer> factors = getPrimeFactorsOf(n);
                System.out.print(n + ":\t");
                for (int factor : factors)
                    System.out.print(factor + ", ");
                System.out.println();
                factors = getPrimeFactorsOf(n+1);
                System.out.print((n+1) + ":\t");
                for (int factor : factors)
                    System.out.print(factor + ", ");
                System.out.println();
                factors = getPrimeFactorsOf(n+2);
                System.out.print((n+2) + ":\t");
                for (int factor : factors)
                    System.out.print(factor + ", ");
                System.out.println();
                factors = getPrimeFactorsOf(n+3);
                System.out.print((n+3) + ":\t");
                for (int factor : factors)
                    System.out.print(factor + ", ");
                System.out.println();
                break;
                
            }
            
            n++;
        }
    }
    //answer: 134043
    /*
    134043:	3, 7, 13, 491, 
    134044:	2, 23, 31, 47, 
    134045:	5, 17, 19, 83, 
    134046:	2, 3, 11, 677, 
    */

    public static List<Integer> getFactorsOf(Integer n, boolean includeOne, boolean includeSelf) {
        List<Integer> factors = new ArrayList<>();
        
        int start = includeOne ? 1 : 2;
        
        for (int i = start; i <= n/2; i++) {
            if (n % i == 0)
                factors.add(i);
        }
        if (includeSelf)
            factors.add(n);
        
        return factors;
    }
    
    public static boolean isPrime(int num) {
        if (num < 2) 
            return false;
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) return false;
        }
        return true;
    }
    
    public static List<Integer> getPrimeFactorsOf(Integer n) {
        List<Integer> factors = new ArrayList<>();
        
        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0 && isPrime(i))
                factors.add(i);
        }

        
        return factors;
    }
}
