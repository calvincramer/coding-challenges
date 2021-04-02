package problem119;

import java.util.Random;
import mathtools.MF;

public class Problem119 {

    /*
    The number 512 is interesting because it is equal to the sum of its digits raised to some power: 5 + 1 + 2 = 8, and 8^3 = 512. Another example of a number with this property is 614656 = 284.

    We shall define an to be the nth term of this sequence and insist that a number must contain at least two digits to have a sum.

    You are given that a2 = 512 and a10 = 614656.

    Find a30.
    */
    public static void main(String[] args) {
        /*
        Random rng = new Random(System.currentTimeMillis());
        for (long MAX = 1000; MAX <= 100000000; MAX *= 10) {
            
            System.out.println("MAX= " + MAX);
            
            MF.startTimer();
            for (int i = 1; i <= MAX; i++) {
                sumOfDigits(rng.nextLong());
            }        
            System.out.println("1: " + MF.getElapsedSeconds());
            MF.startTimer();
            for (int i = 1; i <= MAX; i++) {
                sumOfDigits2(rng.nextLong());
            }        
            System.out.println("2: " + MF.getElapsedSeconds());
        
            System.out.println();
        }
        
        */
        long n = 10;
        int i = 0;
        
        while (true) {
            long digSum = sumOfDigits2(n);
            long powerOf = findPowerOf(n, digSum);
            if (powerOf != -1) {
                i++;
                System.out.println(i + "\t" + n + "\t=" + digSum + " ^ " + powerOf);
                //System.out.println("dig sum= " + digSum);
                //System.out.println("power: " + powerOf);
                //System.out.println("");
            }
            
            n++;
        }
    }
    
    /**
     * Sums the digits of a number
     * sumOfDigits2 is faster.
     * @param n
     * @return 
     */
    public static long sumOfDigits(long n) {
        if (n < 0)
            n *= -1;
        char[] digits = (""+n).toCharArray();
        long sum = 0;
        for (char d : digits) {
            sum += Character.getNumericValue(d);
        }
        return sum;
    }
    
    /**
     * Sums the digits of a number.
     * About 2x faster than sumOfDigits
     * @param n
     * @return 
     */
    public static long sumOfDigits2(long n) {
        if (n < 0)
            n *= -1;
        
        long sum = 0;
        
        do {
            sum += n%10;
            n /= 10;
        } while (n >= 10);
        sum += n;
        
        return sum;
    }

    /**
     * Tries to find an integer a s.t. x=y^a
     * @param x
     * @param y
     * @return 
     */
    public static long findPowerOf(long x, long y) {
        //
        double aGuess = Math.log(x) / Math.log(y);
        int low = (int) aGuess;
        int high = low+1;
        
        if ((long)Math.pow(y, low) == x)
            return low;
        if ((long)Math.pow(y, high) == x)
            return high;
        
        return -1;
    }
}
/*
1	81	=9 ^ 2
2	512	=8 ^ 3
3	2401	=7 ^ 4
4	4913	=17 ^ 3
5	5832	=18 ^ 3
6	17576	=26 ^ 3
7	19683	=27 ^ 3
8	234256	=22 ^ 4
9	390625	=25 ^ 4
10	614656	=28 ^ 4
11	1679616	=36 ^ 4
12	17210368	=28 ^ 5
13	34012224	=18 ^ 6
14	52521875	=35 ^ 5
15	60466176	=36 ^ 5
16	205962976	=46 ^ 5
17	612220032	=18 ^ 7


*/
