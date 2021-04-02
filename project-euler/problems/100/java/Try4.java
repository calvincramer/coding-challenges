package problem100;

import java.math.BigDecimal;
import java.math.BigInteger;
import mathtools.MF;
import static problem100.Problem100.two;


public class Try4 {
    
    public static void main(String[] args) {
        //based on the observation that as n goes to infinity, the the ratio between the nth total and the nth minus one total converges to 3 + 2*sqrt(2)
        //a downside to this method is that you must know the previous term to find the next (cant just start at 10^12)
        BigDecimal root2 = MF.sqrt(new BigDecimal(2), 4000);
        BigDecimal mult = root2.multiply(new BigDecimal(2)).add(new BigDecimal(3));
        
        BigInteger end = BigInteger.TEN.pow(13);
        BigInteger startT = BigInteger.ONE;
        MF.startTimer();
        while (startT.compareTo(end) < 0) {
            startT = findTAround(startT);
            System.out.println("t= " + startT + "\t\tb= " + getB(startT));
            //next approx t
            BigDecimal t = new BigDecimal(startT);
            t = t.multiply(mult);
            startT = t.toBigInteger();
        }
        
        System.out.println(MF.getElapsedSeconds());
        
    }
    
    public static BigInteger findTAround(BigInteger guess) {
        if (getB(guess) != null)
            return guess;
        
        BigInteger step = BigInteger.ONE;
        while (true) {
            BigInteger low = guess.subtract(step);
            if (getB(low) != null)
                return low;
            
            BigInteger high = guess.add(step);
            if (getB(high) != null)
                return high;
            
            step = step.add(BigInteger.ONE);
            
        }
    }
    
    public static BigInteger getB(BigInteger t) {
        BigInteger sqr = BigInteger.ONE.subtract(two.multiply(t)).add(two.multiply(t.pow(2)));
        if (MF.isPerfectSquare(sqr)) {
            sqr = MF.bigIntSqRootFloor(sqr);
            BigInteger b = (BigInteger.ONE.add(sqr)).divide(two);
            return b;
        }
        return null;
    }
}
/*
t= 1		b= 1
t= 4		b= 3
t= 21		b= 15
t= 120		b= 85
t= 697		b= 493
t= 4060		b= 2871
t= 23661		b= 16731
t= 137904		b= 97513
t= 803761		b= 568345
t= 4684660		b= 3312555
t= 27304197		b= 19306983
t= 159140520		b= 112529341
t= 927538921		b= 655869061
t= 5406093004		b= 3822685023
t= 31509019101		b= 22280241075
t= 183648021600		b= 129858761425
t= 1070379110497		b= 756872327473     //answer
t= 6238626641380		b= 4411375203411
0.020844327
*/