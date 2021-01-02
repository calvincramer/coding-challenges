package problem080;

import java.math.BigDecimal;
import mathtools.MF;


public class Problem080 {
    /*
    It is well known that if the square root of a natural number is not an integer, then it is irrational. The decimal expansion of such square roots is infinite without any repeating pattern at all.

    The square root of two is 1.41421356237309504880..., and the digital sum of the first one hundred decimal digits is 475.

    For the first one hundred natural numbers, find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots.
    */

    public static void main(String[] args) {
        long total = 0;
        for (int i = 1; i <= 100; i++) {
            if (MF.isPerfectSquare(i))
                continue;
            BigDecimal sqrt = MF.sqrt(new BigDecimal(""+i), 150);
            long tot = 0;
            String str = sqrt.toString();
            tot += Character.getNumericValue(str.charAt(0));
            str = str.substring(2);
            
            for (int r = 0; r < 99; r++)
                tot += Character.getNumericValue(str.charAt(r));
            System.out.println(i+":\t" + tot);
            total += tot;
            
        }
        System.out.println(total);
    }
    //answer: 40886
    
}
