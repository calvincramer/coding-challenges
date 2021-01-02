package problem549;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mathtools.MF;

public class Problem549 {
    
    /*
    The smallest number m such that 10 divides m! is m=5.
    The smallest number m such that 25 divides m! is m=10.

    Let s(n) be the smallest number m such that n divides m!.
    So s(10)=5 and s(25)=10.
    Let S(n) be ∑s(i) for 2 ≤ i ≤ n.
    S(100)=2012.

    Find S(10^8).
    */
    /*
    General idea-- make list of natual numbers, get prime factorization of them, divide by the 
    prime factorizations until we get to 1
    */
    
    static List<List<Long>> primeFacs = new ArrayList<>();
    static int[][] primeFacTable;
    static final int tenPow8 = 100000000;
    public static List<Integer>[] memo = (ArrayList<Integer>[]) new ArrayList[tenPow8 / 100];
    
    public static void main(String[] args) {
        
        System.out.println("Calculating table...");
        MF.startTimer();
        primeFacTable = MF.getPrimeFactorizationUnder2(tenPow8 / 100);
        System.out.println("time = " + MF.getElapsedSeconds());
        
        /*
        for (int i = 2; i < 1000; i++) {
            System.out.print(i + ": " + s(i) + "\t" + s3andAHalf(i));
            if (i % s(i) != 0)
                System.out.print("\tinteresting");
            System.out.println("");
        }
        */
        
        System.out.println(MF.getPrimeFactorizationUnder2Reconstruct(12, primeFacTable));
        
        System.out.println("s3(10): " + s3andAHalf(10));
        System.out.println("s3(25): " + s3andAHalf(25));
        System.out.println("S3(100): " + S3andAHalf(100));


        for (int size = 1000; size <= tenPow8; size *= 10) {
            //MF.startTimer();
            //System.out.println("S3(" + size + "): \t" + S3(size) 
            //        + "\ttime: " + MF.getElapsedSeconds());
            MF.startTimer();
            System.out.println("S3.5(" + size + "): \t" + S3andAHalf(size) 
                    + "\ttime: " + MF.getElapsedSeconds());
            System.out.println("");
        }
        System.out.println("");

        
        
        
        
        
        
        
        /*
        MF.startTimer();
        for (long i = 0; i <= 100000000; i++)
            primeFacs.add(MF.getPrimeFactorization(i));
        System.out.println(MF.getElapsedSeconds());
        */
        
        /*
        for (int n = 1; n < 10000; n++) {
            long sn = s(n);
            long sn2 = s2(n);
            if (sn != sn2)
                System.out.println("DIFFERENCE n= " + n + "\tsn: " + sn + "\tsn2: " + sn2);
        }
        */
        
        /*
        for (long size = 32; size <= 100000; size *= 2) {
            System.out.println("size = " + size);
            MF.startTimer();
            for (int i = 1; i <= size; i++)
                s3(i);
            System.out.println("s3: " + MF.getElapsedSeconds());
            MF.startTimer();
            for (int i = 1; i <= size; i++)
                s(i);
            System.out.println("s1: " + MF.getElapsedSeconds() + "\n");
            
        }
        */
        
        //equality
        /*
        for (long size = 32; size <= 100000; size *= 2) {
            System.out.println("size = " + size);
            MF.startTimer();
            long S3 = S3(size);
            System.out.println("S3: " + MF.getElapsedSeconds());
            long S1 = S(size);
            System.out.println("S1: " + MF.getElapsedSeconds());
            if (S3 != S1)
                System.out.println("Diff: " + S3 + "\t" + S1 + "\n");
        }
        */

    }
   
    public static long s4(long n) {
        //hypothesis : s(n) = max {each duplicate prime factor * power}
        //ie 882 = 2 * 3^2 * 7^2, s(882) = 7*7 = 14 because max{2, 9, 14} = 14
        //almost but its wrong, counter example = 810 = 2 * 3^4 * 5, s(810) = 9
        
        //hypothesis s(n) = max number where all of the prime factors occur in the 
        //prime factorizations of the numbers before it
        return 0;
        
    }
    
    public static long S4(long n) {
        long sum = 0;
        for (int i = 2; i <= n; i++)
            sum += s4(i);
        return sum;
    }
    
    
    public static long s3andAHalf(long n) {
        if (primeFacTable[(int)n][1] == -1)    //is prime
            return n;
            
        int i = 2;
        while (n != 1) {
            int walk = i;
            long div = primeFacTable[walk][0];

            while (true) {
                if (n % div == 0)
                    n /= div;
                
                if (primeFacTable[walk][1] == -1)
                    break;
                
                walk = primeFacTable[walk][1];
                div = primeFacTable[walk][0];
            }
            
            if (n != 1)
                i++;
        }
        
        return i;
    }
    
    public static long S3andAHalf(long n) {
        long sum = 0;
        for (int i = 2; i <= n; i++)
            sum += s3andAHalf(i);
        return sum;
    }
    
    
    public static long s3(long n) {
        if (primeFacTable[(int)n][1] == -1)    //is prime
            return n;
            
        int i = 2;
        while (n != 1) {
            
            List<Integer> divs;
            if (memo[i] == null) {  //memoized, still store the lists
                divs = MF.getPrimeFactorizationUnder2Reconstruct(i, primeFacTable);
                memo[i] = divs;
            }
            else
                divs = memo[i];
            
            for (long div : divs) {
                if (n % div == 0)
                    n /= div;
            }
            
            if (n != 1)
                i++;
        }
        
        return i;
    }
    
    public static long S3(long n) {
        long sum = 0;
        for (int i = 2; i <= n; i++)
            sum += s3(i);
        return sum;
    }
    
    
    //too slow with lists
    public static long s2(long n) {
        if (MF.isPrimeByPrimes2(n))
            return n;
        int i = 0;
        while (n != 1) {
            for (long div : primeFacs.get(i)) {
                if (n % div == 0)
                    n /= div;
            }
            
            if (n != 1)
                i++;
        }
        
        return i;
    }
    
    public static long S2(long n) {
        long sum = 0;
        for (int i = 2; i <= n; i++)
            sum += s2(i);
        return sum;
    }
    
    
    
    
    // dumb way
    public static long S(long n) {
        long sum = 0;
        for (int i = 2; i <= n; i++) {
            //System.out.println(i);
            sum += s(i);
        }
        return sum;
    }
    
    //dumb way
    public static long s(long n) {
        BigInteger num = new BigInteger(""+n);
        BigInteger m = BigInteger.ONE;
        BigInteger mfac = m;
        while ( !mfac.remainder(num).equals(BigInteger.ZERO) ) {
            m = m.add(BigInteger.ONE);
            mfac = mfac.multiply(m);
        }
        return m.longValue();
    }
}