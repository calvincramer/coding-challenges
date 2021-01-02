package problem351;

import java.math.BigDecimal;
import java.math.BigInteger;


public class Attempt5 {
    
    public static void main(String[] args) {
        BigInteger hmil = new BigInteger("100000000");
        
        BigInteger total = hmil.multiply(hmil).subtract(hmil).multiply(new BigInteger("3")).add(BigInteger.ONE);
        
        BigDecimal numerator = new BigDecimal("11762395476");
        BigDecimal denom = new BigDecimal("29999700001");
        BigDecimal perc = numerator.divide(denom, 200, BigDecimal.ROUND_DOWN);
        
        
        BigDecimal guess = perc.multiply(new BigDecimal(total));
        
        
        System.out.println(total);
        System.out.println(perc);
        System.out.println();
        System.out.println(guess);
    }
}
/*
guessed (incorrectly) 

11762512983113793 from H(100000) = 11762395476
11765684811617128 from H(10000)  = 117645084


statistcal approach
percentage hidden approaches 39.208%
*/
