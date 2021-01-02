package problem073;

import java.math.BigDecimal;

public class Problem073 {

    /*
    Consider the fraction, n/d, where n and d are positive integers. If n<d and HCF(n,d)=1, it is called a reduced proper fraction.

    If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:

    1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8

    It can be seen that there are 3 fractions between 1/3 and 1/2.

    How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d ≤ 12,000?
    */
    public static void main(String[] args) {
        
        double third = 1.0/3.0;
        double half = 1.0/2.0;
        
        System.out.println("1/3= " + third);
        System.out.println("1/2= " + half);
        
        long numBetween = 0;
        
        for (int d = 2; d <= 12000; d++) {
            int startN = (int) (d / 3.0) - 1;
            int endN = (int) (d / 2.0) + 1;
            for (int n = startN; n <= endN; n++) {
                if (gcf(n,d) == 1 && n*1.0/d > third && n*1.0/d < half ) {
                    //System.out.println(n *1.0/d);
                    numBetween++;
                }
            }
        }
        
        System.out.println("Total: " + numBetween);
        
    }
    
    public static long gcf(long n1, long n2) {
        if (n1 == 0)
            return n2;

        while (n2 != 0) {
            long r = n1 % n2;
            n1 = n2;
            n2 = r;
        }
        
        return n1;
    }
    
}
//answer: 7295372
