package problem211;

import java.util.List;
import mathtools.MF;

public class Problem211 {
    
    /*
    For a positive integer n, let σ2(n) be the sum of the squares of its divisors. For example,

    σ2(10) = 1 + 4 + 25 + 100 = 130.
    Find the sum of all n, 0 < n < 64,000,000 such that σ2(n) is a perfect square.
    */
    public static void main(String[] args) {
        long[] sizes = {1000000, 2000000, 4000000, 8000000, 16000000, 32000000, 64000000};
        for (long max : sizes) {
            MF.startTimer();
            long sum = 0;
            System.out.print(max + ":\t");
            for (long n = 1; n < max; n++) {
                if (MF.isPerfectSquare(sigma2(n)))
                    sum += n;
            }
            System.out.println(sum + "\t\tt= " + MF.getElapsedSeconds());
            
        }
    }
    
    public static long sigma2(long n) {
        List<Long> divisors = MF.getFactorsOfFaster(n, true, true);
        long sumSquares = 0;
        for (Long div : divisors)
            sumSquares += div * div;
        return sumSquares;
    }
}

/*
1000000:            9890738		t= 8.32461474
2000000:            19188103		t= 22.77340271
4000000:            48307997		t= 63.217406354
8000000:            158222609		t= 178.291614725
16000000:           355907527		t= 501.669363996
*/