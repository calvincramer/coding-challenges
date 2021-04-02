package problem100;

import java.math.BigInteger;
import mathtools.MF;

public class Problem100 {
    
    public static BigInteger two = new BigInteger("2");
    
    public static void main(String[] args) {
        /*
        for (long total = 1; total <= 2000000000000L; total++) {
            long sqr = 1 - (2 * total) + (2 * total * total);
            if (MF.isPerfectSquare(sqr)) {
                sqr = (int) Math.sqrt(sqr);
                long b = (1 + sqr) / 2;
                System.out.println("total = " + total + "\t\tnum blue = " + b);
                
                boolean check = b * (b-1) == (total * (total-1)) / 2;
                System.out.println((check) ? "good" : "bad");
            }
        }
        */
        
        BigInteger total = new BigInteger("1000000000000");
        while (true) {
            //System.out.println(total);
            BigInteger b = getB(total);
            if (b != null) {
                System.out.println("done");
                System.out.println("total = " + total);
                System.out.println("blue = " + b);
                
                break;
            }
            total = total.add(BigInteger.ONE);
        }
    }
    
    public static BigInteger getB(BigInteger t) {
        /*
        long sqr = 1 - (2 * t) + (2 * t * t);
        if (MF.isPerfectSquare(sqr)) {
            sqr = (int) Math.sqrt(sqr);
            long b = (1 + sqr) / 2;
            System.out.println("total = " + t + "\t\tnum blue = " + b);

            boolean check = b * (b-1) == (t * (t-1)) / 2;
            System.out.println((check) ? "good" : "bad");
        }
        */
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
total = 1		num blue = 1
good
total = 4		num blue = 3
good
total = 21		num blue = 15
good
total = 120		num blue = 85
good
total = 697		num blue = 493
good
total = 4060		num blue = 2871
good
total = 23661		num blue = 16731
good
total = 137904		num blue = 97513
good
total = 803761		num blue = 568345
good
total = 4684660		num blue = 3312555
good
total = 27304197		num blue = 19306983
good
total = 159140520		num blue = 112529341
good
total = 927538921		num blue = 655869061
good

*/