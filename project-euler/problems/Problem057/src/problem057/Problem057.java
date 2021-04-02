package problem057;

import java.math.BigInteger;
import java.math.BigDecimal;

public class Problem057 {

    /*
    It is possible to show that the square root of two can be expressed as an infinite continued fraction.

    √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...

    By expanding this for the first four iterations, we get:

    1 + 1/2 = 3/2 = 1.5
    1 + 1/(2 + 1/2) = 7/5 = 1.4
    1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
    1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...

    The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985, is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.

    In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
    */
    public static void main(String[] args) {
        int numMoreDigits = 0;
        for (int i = 1; i <= Integer.MAX_VALUE; i++) {
            BigFraction est = estimateSqrt2Large(i);
            
            System.out.print(i + "\t" + est.toString() + "\t" + est.getDecimalForm() + "\t");
            if (est.numerator.toString().length() > est.denomonator.toString().length()) {
                System.out.println("NUM GREATER");
                numMoreDigits++;
            }
            else
                System.out.println();
        }
        System.out.println("\tNum numerators greater than denoms: " + numMoreDigits);
    }
    //answer: 153
    
    public static LFraction estimateSqrt2(int accuracy) {
        if (accuracy < 1)
            accuracy = 1;
        
        LFraction esti = new LFraction(1, 2);
        
        for (int n = 1; n < accuracy; n++) {    //do accuracy minus 1 times
            //2 + fraction
            //invert
            esti.numerator += 2 * esti.denomonator;
            esti.invertFraction();
        }
        //add 1
        esti.numerator += 1 * esti.denomonator;
        
        return esti;
    }
    
    public static BigFraction estimateSqrt2Large(int accuracy) {
        if (accuracy < 1)
            accuracy = 1;
        
        BigFraction esti = new BigFraction(BigInteger.ONE, new BigInteger("2"));
        BigInteger two = new BigInteger("2");
        
        for (int n = 1; n < accuracy; n++) {    //do accuracy minus 1 times
            //2 + fraction
            //invert
            esti.numerator = esti.numerator.add(two.multiply(esti.denomonator));
            esti.invertFraction();
        }
        //add 1
        esti.numerator = esti.numerator.add(esti.denomonator);
        
        return esti;
    }
    
    public static int getNumDigits(BigInteger num) {
        return num.toString().length();
    }
    
    public static class LFraction {
        public long numerator;
        public long denomonator;
        public LFraction(long numerator, long denomonator) {
            this.numerator = numerator;
            this.denomonator = denomonator;
        }
        public double getDecimalForm() {
            if (this.denomonator == 0)
                return Double.NaN;
            return this.numerator * 1.0 / this.denomonator;
        }
        public void invertFraction() {
            long temp = this.numerator;
            this.numerator = this.denomonator;
            this.denomonator = temp;
        }
        
        @Override public String toString() {
            return "" + this.numerator + "/" + this.denomonator;
        }
    }
    
    public static class BigFraction {
        public BigInteger numerator;
        public BigInteger denomonator;
        public BigFraction(BigInteger numerator, BigInteger denomonator) {
            this.numerator = numerator;
            this.denomonator = denomonator;
        }
        public BigDecimal getDecimalForm() {
            if (this.denomonator == BigInteger.ZERO)
                return BigDecimal.ZERO;
            BigDecimal n = new BigDecimal(this.numerator);
            BigDecimal d = new BigDecimal(this.denomonator);
            if (d.compareTo(BigDecimal.ZERO) == 0)
                return BigDecimal.ZERO;
            return n.divide(d, 10, BigDecimal.ROUND_DOWN);
            
        }
        
        public void invertFraction() {
            BigInteger temp = this.numerator;
            this.numerator = this.denomonator;
            this.denomonator = temp;
        }
        
        
        @Override public String toString() {
            String str = "";
            if (this.numerator.toString().length() > 10)
                str += this.numerator.toString().substring(0, 10) + "...";
            else 
                str += this.numerator.toString();
            
            str += "/";
            
            if (this.denomonator.toString().length() > 10)
                str += this.denomonator.toString().substring(0, 10) + "...";
            else
                str += this.denomonator.toString();

            return str;
        }


    }
}
