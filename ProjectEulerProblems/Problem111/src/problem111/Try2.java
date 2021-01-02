package problem111;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;
import mathtools.PrimePrecomputer2;

public class Try2 {
    
    /*
    Considering 4-digit primes containing repeated digits it is clear that they cannot all be the same: 
        1111 is divisible by 11, 2222 is divisible by 22, and so on. But there are nine 4-digit primes containing three ones:

    1117, 1151, 1171, 1181, 1511, 1811, 2111, 4111, 8111

    We shall say that M(n, d) represents the maximum number of repeated digits for an n-digit prime where d is the repeated digit, 
        N(n, d) represents the number of such primes, and S(n, d) represents the sum of these primes.

    So M(4, 1) = 3 is the maximum number of repeated digits for a 4-digit prime where one is the repeated digit, 
        there are N(4, 1) = 9 such primes, and the sum of these primes is S(4, 1) = 22275. It turns out that for d = 0, it is only possible to have M(4, 0) = 2 repeated digits, but there are N(4, 0) = 13 such cases.

    In the same way we obtain the following results for 4-digit primes.

    Digit, d	M(4, d)	N(4, d)	S(4, d)
        0	2	13	67061
        1	3	9	22275
        2	3	1	2221
        3	3	12	46214
        4	3	2	8888
        5	3	1	5557
        6	3	1	6661
        7	3	9	57863
        8	3	1	8887
        9	3	7	48073
    For d = 0 to 9, the sum of all S(4, d) is 273700.

    Find the sum of all S(10, d).
    */
    
    public static final int MAX_LENGTH = 10;
    public static final int START_LENGTH = 8;
    public static int[][] ms = new int[MAX_LENGTH+1][10];
    public static boolean[][] msFilledIn = new boolean[MAX_LENGTH + 1][10];
    
    public static void main(String[] args) {
        /* sieve of eratothenes (much memory!)
        List<Long> primes = MF.getPrimesUnder((int)Math.pow(10, MAX_LENGTH));
        for (Long prime : primes) {
            int length = (""+prime).length();
            primesByLength.get(length).add(prime);
        }
        */
        
        long start = (long) Math.pow(10, START_LENGTH - 1);
        long stop = (long) Math.pow(10, START_LENGTH);
        while (stop <= (long)Math.pow(10, MAX_LENGTH)) {
            int length = (start+"").length();
            
            System.out.println("building: " + length);
            
            MF.startTimer();
            List<Long> primes = PrimePrecomputer2.getPrimesInRange(start, stop);
            System.out.println("\tdone reading\t" + MF.getElapsedSeconds());
            
            
            Long[] toUse = primes.toArray(new Long[primes.size()]);
            System.out.println("S("+length+") = " + S(toUse));
            
            
            
            start *= 10;
            stop *= 10;
        }

    }
    
    /**
     * Maximum number of digits (of digit digitRepeat) for the primes in a list
     * @param primes
     * @param digitRepeat
     * @return 
     */
    public static int m(Long[] primes, int digitRepeat) {
        
        if (digitRepeat < 0 || digitRepeat > 9)
            return 0;
        
        int maxRun = 0;
        for (long prime : primes) {
            int tempRun = numDigitsIn(prime, digitRepeat);
            if (tempRun > maxRun)
                maxRun = tempRun;
        }
        
        return maxRun;
    }
    
    /**
     * Number of primes that have the greatest amount of digitRepeat
     * @param primes
     * @param digitRepeat
     * @return 
     */
    public static int n(Long[] primes, int digitRepeat) {
        
        if (digitRepeat < 0 || digitRepeat > 9)
            return 0;
        
        int m = m(primes, digitRepeat);
        
        int total = 0;
        for (long prime : primes) {
            if (numDigitsIn(prime, digitRepeat) == m)
                total++;
        }
        
        return total;
    }
    
    /**
     * Sum of primes from n(primes, digitRepeat)
     * @param primes
     * @param digitRepeat
     * @return 
     */
    public static long s(Long[] primes, int digitRepeat) {
        
        if (digitRepeat < 0 || digitRepeat > 9)
            return 0;
        
        //int m = m(nDigits, digitRepeat);
        
        int m = m(primes, digitRepeat);
        
        long total = 0;
        for (long prime : primes) {
            if (numDigitsIn(prime, digitRepeat) == m)
                total += prime;
            //if (numDigitsIn(prime, digitRepeat) == m)
            //    total += prime;
        }
        
        return total;
    }
    
    /**
     * Sum of s(primes) for all digits
     * @param primes
     * @return 
     */
    public static long S(Long[] primes) {
        long total = 0;
        for (int d = 0; d <= 9; d++)
            total += s(primes, d);
        return total;
    }
    
    public static int numDigitsIn(long num, int digit) {
        String str = ""+num;
        int digs = 0;
        for (char c : str.toCharArray()) {
            if (Character.getNumericValue(c) == digit)
                digs++;
        }
        return digs;
    }
}
/*
S(0) = 0
S(1) = 119
S(2) = 2865
S(3) = 31283
S(4) = 273700
S(5) = 6045857
S(6) = 43552775
S(7) = 1266769793       //7 second
S(8) = 4060851254       //137 seconds OwO
S(9) = 45609504098
*/