package problem065;

import java.math.BigInteger;
import mathtools.MF;

public class Problem065 {

    public static void main(String[] args) {

        int[] nums = new int[1 + (3*100)];
        nums[0] = 2;
        int k = 2;
        for (int i = 1; i < nums.length; i += 3) {
            nums[i] = 1;
            nums[i+1] = k;
            nums[i+2] = 1;
            k += 2;
        }
        
        for (int start = 0; start < 100; start++) {
            MF.BigFraction efrac = new MF.BigFraction(BigInteger.ONE, new BigInteger(""+nums[start]));
            for (int i = start - 1; i >= 0; i--) {
                //efrac.numerator = (nums[i] * efrac.denominator) + efrac.numerator;
                efrac.numerator = efrac.denomonator.multiply(new BigInteger(""+nums[i])).add(efrac.numerator);
                if (start > 0)
                    efrac.invertFraction();
            }
            efrac.invertFraction();
            System.out.println((start+1) + ":\t" + efrac + "\t\t" + efrac.getDecimalForm());
            if (start + 1 == 100) {
                long sum = 0;
                String s = efrac.numerator.toString();
                for (char c : s.toCharArray())
                    sum += Character.getNumericValue(c);
                System.out.println("Sum: " + sum);
            }
            
        }
    }
    
    public static class LFraction {
        public long numerator;
        public long denominator;
        public LFraction(long numerator, long denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        public double getDecimalForm() {
            if (this.denominator == 0)
                return Double.NaN;
            return this.numerator * 1.0 / this.denominator;
        }
        public void invert() {
            long temp = this.numerator;
            this.numerator = this.denominator;
            this.denominator = temp;
        }
        
        @Override public String toString() {
            return "" + this.numerator + "/" + this.denominator;
        }
    }

}
//answer: Sum: 272
