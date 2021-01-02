package problem146;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Problem146 {
    
    /*
    The smallest positive integer n for which the numbers n^2+1, n^2+3, n^2+7, n^2+9, n^2+13, and n^2+27 are consecutive primes is 10. 
    The sum of all such integers n below one-million is 1242490.

    What is the sum of all such integers n below 150 million?
    */
    public static void main(String[] args) {
        //testing();
        try2();
        
    }
    
    
    public static void try1() {
        long max = 150000000;
        long total = 0;
        long[] add = new long[]{1,3,7,9,13,27};
        long[] mustNotBePrime = new long[]{5,11,15,17,19,21,23,25};
        
        int lastPercent = -1;
        
        for (long n = 0; n < max; n += 10) {     //n must be even
            int percent = (int) (100.0 * n / max);
            if (percent != lastPercent && percent % 2 == 0) {
                lastPercent = percent;
                System.out.println(percent + "%");
            }
            
            long nsqr = n*n;
            boolean passed = true;
            
            for (long b : add) {
                long temp = nsqr + b;
                if (!MF.isPrimeByPrimes4(temp)) {
                    passed = false;
                    break;
                }
            }
            if (!passed)
                continue;
            
            for (long b : mustNotBePrime) {
                long temp = nsqr + b;
                if (MF.isPrimeByPrimes4(temp)) {
                    passed = false;
                    break;
                }
            }
            
            if (passed) {
                System.out.println(n + "\t" + MF.getPrimeFactorization(n));
                total += n;
            }
            
        }
        System.out.println("total: " + total);
    }
    
    public static void try2() {
        //slow finding 16755190 at 2x
        long max = 150000000;
        long total = 10;    //skip 10
        
        long last = 315410; //dont start at 10
        
        while (last < max) {     //n must be even
            
            long offset3 = 0;    //in 10 increments
            long offset12 = 0;
            long n = last;
            long n2 = last * 2;
            long n3 = last * 3;
            
            boolean found = false;
            int count = 0;
            
            while (!found) {
                
                
                long top3 = n3 + offset3;
                long bottom3 = n3 - offset3;
                
                if (check(bottom3)) {    //check 3x
                    found = true;
                    last = bottom3;
                    System.out.println(last + "\t" + MF.getPrimeFactorization(n) + "\t3x bottom");
                }
                else if (check(top3)) {
                    found = true;
                    last = top3;
                    System.out.println(last + "\t" + MF.getPrimeFactorization(n) + "\t3x top");
                }
                
                if (count >= 50) {
                    count = 0;
                    long top2 = n2 + offset12;
                    long bottom2 = n2 - offset12;
                    if (n + offset12 != last && check(n + offset12)) {  //check above
                        found = true;
                        last = n + offset3;
                        System.out.println(last + "\t" + MF.getPrimeFactorization(n) + "\t1x");
                    }
                    else if (check(bottom2)) {    //check 2x
                        found = true;
                        last = bottom2;
                        System.out.println(last + "\t" + MF.getPrimeFactorization(n) + "\t2x bottom");
                    }
                    else if (check(top2)) {
                        found = true;
                        last = top2;
                        System.out.println(last + "\t" + MF.getPrimeFactorization(n) + "\t2x top");
                    }
                    offset12 += 10;
                }
                offset3 += 10;
            }
            
            
            
            
            
        }
        System.out.println("total: " + total);
    }
    
    private static final long[] add = new long[]{1,3,7,9,13,27};
    private static final long[] mustNotBePrime = new long[]{5,11,15,17,19,21,23,25};
    public static boolean check(long num) {
        long nsqr = num*num;
            
            for (long b : add) {
                long temp = nsqr + b;
                if (!MF.isPrimeByPrimes4(temp)) return false;
            }

            for (long b : mustNotBePrime) {
                long temp = nsqr + b;
                if (MF.isPrimeByPrimes4(temp)) return false;
            }
            return true;
    }
    
    
    public static void testing() {
        long[] answers = new long[]{10,315410,927070,2525870,8146100,16755190};
        for (long answer : answers) {
            System.out.println(answer + "\t" + MF.getPrimeFactorization(answer));
        }
        System.out.println("");
        
        for (int i = 0; i < answers.length - 1; i++) {
            System.out.println(answers[i]);
            System.out.println(" * " + (answers[i+1] * 1.0 / answers[i]));
        }
        System.out.println(answers[answers.length-1]);
        
        for (long n = 20; n <= 1000; n += 10)
            System.out.println(n + "\t" + MF.getPrimeFactorization(n));
    }
}
//n must be even, if n is odd, n^2 is odd, thus n^2 + 1 is even and never prime
//n can't end with 2,4,6 or 8, since n^2 + 1 for 2 and 8 ends with 5, not prime, n^2 + 9 ends with 5, not prime
//thus n can only end with a 0.

//it looks like the numbers are 3x or 2x the previous one

//currently found:
/*
10
315410
927070
2525870
8146100
16755190


10
 * 31541.0
315410
 * 2.939253669826575
927070
 * 2.7245731174560714
2525870
 * 3.2250670066155425
8146100
 * 2.0568357864499576
16755190


Got more than 12% done, but less than 14% of 150 million
*/