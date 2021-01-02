package problem357;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Problem357 {
    
    /*
    Consider the divisors of 30: 1,2,3,5,6,10,15,30.
    It can be seen that for every divisor d of 30, d+30/d is prime.

    Find the sum of all positive integers n not exceeding 100 000 000
    such that for every divisor d of n, d+n/d is prime.
    */
    static final int stop = 100000000;
    static boolean[] primes = getPrimesUnder2(stop+1);
    
    public static void main(String[] args) {
        
        
        
        MF.startTimer();
        BigInteger sum1 = BigInteger.ZERO;
        for (int i = 1; i < stop; i++) {
            if (test(i)) {
                //System.out.println(i);
                sum1 = sum1.add(new BigInteger(""+i)); 
            }
        }
        double time1 = MF.getElapsedSeconds();

        System.out.println("test1: " + sum1);
        System.out.println("time1: " + time1);

        
        
        
    }
    
    //can do this in two ways:
    //1: compute all divisors for the number first then short circuit if there are any primes
    //2: while computing divisors, check each divisor for primality.
    //also d+(n/d) is symmetrical up through the sqrt of the number
    public static boolean test(int num) {
        List<Integer> divisors = getDivisorsOf(num);
        for (int div : divisors) {
            int dnd = div + (num / div);
            if (dnd > stop) {
                if (!MF.isPrimeFaster(dnd))
                    return false;
            }
            else {
                if (! primes[dnd])
                    return false;
            }
        }
        return true;
    }
    
    public static List<Integer> getDivisorsOf(int num) {
        
        List<Integer> divs = new ArrayList<>();
        
        if (num == 0) {
            divs.add(0);
            return divs;
        }
        else if (num < 0)
            num *= -1;
        
        divs.add(1);
        
        if (num == 1)
            return divs;
        
        for (int d = 2; d < Math.sqrt(num); d++) {
            if (num % d == 0)
                divs.add(d);
        }
        
        boolean isSquare = MF.isPerfectSquare(num);
        if (isSquare)
            divs.add((int)Math.sqrt(num));
        
        int start = isSquare ? divs.size() - 2 : divs.size() - 1;
        for (int i = start; i > 0; i--) {
            divs.add(num / divs.get(i));
        }
        
        divs.add(num);
        
        return divs;
    }
    
    public static long[] getPrimesUnder(int number) {
        if (number < 2)
            return null;
        
        //sieve of eratothenes 
        long[] naturals = new long[(int) number];
        for (int i = 0; i < naturals.length; i++)
            naturals[i] = i;
        naturals[1] = 0;    //1 isn't prime
        
        for (int i = 2; i < naturals.length; i++) {
            if (naturals[i] == 0)
                continue;
            int r = i + i;
            while (r < naturals.length) {
                naturals[r] = 0;
                r += i;
            }
            
        }
        
        return naturals;
    }
    
    public static boolean[] getPrimesUnder2(int number) {
        if (number < 2)
            return null;
        
        //sieve of eratothenes 
        boolean[] naturals = new boolean[(int) number];
        for (int i = 0; i < naturals.length; i++)
            naturals[i] = true;
        naturals[0] = false;    //0 isn't prime
        naturals[1] = false;    //1 isn't prime
        
        for (int i = 2; i < naturals.length; i++) {
            if (naturals[i] == false)
                continue;
            int r = i + i;
            while (r < naturals.length) {
                naturals[r] = false;
                r += i;
            }
            
        }
        
        return naturals;
    }
}