package problem020;

import java.math.BigInteger;

public class Problem020 {

    /*
    n! means n × (n − 1) × ... × 3 × 2 × 1

    For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
    and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

    Find the sum of the digits in the number 100!
    */
    public static void main(String[] args) {
        BigInteger fact100 = new BigInteger("1");
        for (int i = 1; i <= 100; i++) 
            fact100 = fact100.multiply(new BigInteger("" + i));
        
        System.out.println("100!:\n" + fact100 + "\n");
        
        String factStr = fact100.toString();
        long totalOfDigits = 0;
        for (int i = 0; i < factStr.length(); i++)
            totalOfDigits += Character.getNumericValue(factStr.charAt(i));
            
        System.out.println("Total of digits: " + totalOfDigits);
    }
    //answer: 648

}
