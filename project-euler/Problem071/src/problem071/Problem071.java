package problem071;

import java.math.BigDecimal;

public class Problem071 {

    /*
    Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.

    If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

    1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

    It can be seen that 2/5 is the fraction immediately to the left of 3/7.

    By listing the set of reduced proper fractions for d ≤ 1,000,000 in ascending order of size, find the numerator of the fraction immediately to the left of 3/7.
    */
    public static void main(String[] args) {
        final int scale = 100;
        BigDecimal three = new BigDecimal("3");
        BigDecimal seven = new BigDecimal("7");
        BigDecimal threeSevenths = three.divide(seven, scale, BigDecimal.ROUND_DOWN);
        BigDecimal num = new BigDecimal("3");
        BigDecimal denom = new BigDecimal("7");
        BigDecimal curFrac = num.divide(denom, scale, BigDecimal.ROUND_DOWN);
        BigDecimal million = new BigDecimal("1000000");
        
        System.out.println("3/7\t= " + threeSevenths);
        
        while (denom.compareTo(million) < 0) {
            curFrac = num.divide(denom, scale, BigDecimal.ROUND_DOWN);
            
            if (curFrac.compareTo(threeSevenths) >= 0)
                denom = denom.add(BigDecimal.ONE);
            else
                num = num.add(BigDecimal.ONE);
            //System.out.println(num + "/" + denom + "\t=" + curFrac);
        }
        
        System.out.println("Done");
        System.out.println(num + "/" + denom + "\t=" + curFrac);
        System.out.println("3/7\t\t=" + threeSevenths);
    }
    
}
//answer: 428571/1000000
//which is exactly equal to 3/7 (I think)
//so 428571 - 1 is closest to the left of 3/7 = 428570