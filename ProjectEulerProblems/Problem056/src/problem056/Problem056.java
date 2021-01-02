package problem056;

import java.math.BigInteger;


public class Problem056 {

    /*
    Powerful digit sum
    Problem 56
    A googol (10^100) is a massive number: one followed by one-hundred zeros; 100^100 is almost unimaginably large: one followed by two-hundred zeros. Despite their size, the sum of the digits in each number is only 1.

    Considering natural numbers of the form, ab, where a, b < 100, what is the maximum digital sum?
    */
    public static void main(String[] args) {
        
        BigInteger hundred = new BigInteger("100");
        
        BigInteger maxNumber = BigInteger.ZERO;
        int maxA = 0;
        int maxB = 0;
        long maxDigits = 0;
        
        for (BigInteger a = BigInteger.ONE; a.compareTo(hundred) < 0; a = a.add(BigInteger.ONE)) {
            System.out.println("a="+a.toString());
            for (int b = 1; b < 100; b++) {
                BigInteger prod = a.pow(b);
                long sumOfDigits = 0;
                //System.out.print(prod);
                for (char digit : prod.toString().toCharArray())
                    sumOfDigits += Character.getNumericValue(digit);
                //System.out.println("\t" + sumOfDigits);
                
                if (sumOfDigits > maxDigits) {
                    maxDigits = sumOfDigits;
                    maxNumber = prod;
                    maxA = a.intValue();
                    maxB = b;
                }
            }
        }
        System.out.println("\nMax number: " + maxNumber);
        System.out.println("Sum of digits: " + maxDigits);
        System.out.println("("+maxA+"^"+maxB+")");
    }
    /*
    Max number: 3848960788934848611927795802824596789608451156087366034658627953530148126008534258032267383768627487094610968554286692697374726725853195657679460590239636893953692985541958490801973870359499
    Sum of digits: 972
    (99^95)
    */
    
}
