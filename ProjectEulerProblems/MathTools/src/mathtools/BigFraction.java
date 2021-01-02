package mathtools;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
* Represents a fraction with BigIntegers for unlimited (within reason Jim) accuracy
* Warning that numerator and denominator are assumed to be non-negative, but do not check for it
*/
public class BigFraction 
    implements Comparable<BigFraction> {
    
    public BigInteger num;        //represent the fraction with integers
    public BigInteger dnm;
    
    /**
     * Constructs a big fraction
     * @param numerator the top number
     * @param denominator the bottom number
     */
    public BigFraction(BigInteger numerator, BigInteger denominator) {
        this.num = numerator;
        this.dnm = denominator;
    }
    
    /**
     * Constructs a big fraction with the starting long values
     * @param numerator the top number
     * @param denominator the bottom number
     */
    public BigFraction(long numerator, long denominator) {
        this.num = new BigInteger(""+numerator);
        this.dnm = new BigInteger(""+denominator);
    }
    
    
    /**
     * Calculated the decimal value of the fraction
     * If the denominator is zero, Double.NaN is return.
     * @return the decimal form of the fraction
     */
    public BigDecimal getDecimalForm() {
        if (this.dnm == BigInteger.ZERO)
            return BigDecimal.ZERO;
        BigDecimal n = new BigDecimal(this.num);
        BigDecimal d = new BigDecimal(this.dnm);
        if (d.compareTo(BigDecimal.ZERO) == 0)
            return BigDecimal.ZERO;
        return n.divide(d, 10, BigDecimal.ROUND_DOWN);

    }

    /**
     * Swaps the numerator and denominator's value
     */
    public void invertFraction() {
        BigInteger temp = this.num;
        this.num = this.dnm;
        this.dnm = temp;
    }

    /**
     * Swaps the numerator and denominator's value
     */
    public void invert() {
        this.invertFraction();
    }

    /**
     * Formats the fraction to a string
     * @return a string representation of the fraction in the form of "numer/denom".
     */
    @Override 
    public String toString() {
        return "" + this.num + "/" + this.dnm;
    }

    /**
     * Reduces the fraction such that the numerator and denominator are relatively prime.
     */
    public void reduceFraction() {
        BigInteger gcd = MF.gcf(num, dnm);
        this.num = this.num.divide(gcd);
        this.dnm = this.dnm.divide(gcd);
    }
    
    /**
     * Reduces the fraction such that the numerator and denominator are relatively prime.
     */
    public void reduce() {
        this.reduceFraction();
    }

    /**
     * Compares the value of this fraction to another fraction
     * @param other - the other fraction
     * @return a positive number if this &gt; other, negative if this &lt; other, and 0 if this == other
     */
    @Override
    public int compareTo(BigFraction other) {
        if (this.num.multiply(other.dnm).equals(this.dnm.multiply(other.num)))
            return 0;
        else if (this.num.multiply(other.dnm).compareTo(this.dnm.multiply(other.num)) > 0)
            return 1;
        else
            return -1;
    }

    /**
     * Determines whether a fraction has the save value as this
     * @param other - the other fraction
     * @return - whether or not they are equal
     */
    @Override 
    public boolean equals(Object other) {
        if (other == this)
            return true;
        else if (!(other instanceof BigFraction))
            return false;
        else {
            BigFraction o = (BigFraction) other;
            return o.compareTo(this) == 0;
        }

    }
    
    /**
     * Adds this fraction to another fraction
     * @param other - the other fraction
     * @return - the sum of this + other
     */
    public BigFraction add(BigFraction other) {
        BigFraction result = new BigFraction(this.num.multiply(other.dnm).add(other.num.multiply(this.dnm)), this.dnm.multiply(other.dnm));
        result.reduceFraction();
        return result;
    }
    
    /**
     * Subtracts this fraction from another fraction
     * @param other - the other fraction
     * @return - the result of this - other
     */
    public BigFraction sub(BigFraction other) {
        BigFraction result = new BigFraction(this.num.multiply(other.dnm).subtract(other.num.multiply(this.dnm)), this.dnm.multiply(other.dnm));
        result.reduceFraction();
        return result;
    }
    
    /**
     * Subtracts this fraction from another fraction
     * @param other - the other fraction
     * @return - the result of this - other
     */
    public BigFraction subtract(BigFraction other) {
        return this.sub(other);
    }
    
    /**
     * Multiplies this by another fraction
     * @param other - the other fraction
     * @return - the result of this * other
     */
    public BigFraction mult(BigFraction other) {
        BigFraction result = new BigFraction(this.num.multiply(other.num), this.dnm.multiply(other.dnm));
        result.reduceFraction();
        return result;
    }
    
    /**
     * Multiplies this by another fraction
     * @param other - the other fraction
     * @return - the result of this * other
     */
    public BigFraction multiply(BigFraction other) {
        return this.mult(other);
    }
    
    /**
     * Divides this by another fraction
     * @param other - the other fraction
     * @return - the result of this / other
     */
    public BigFraction div(BigFraction other) {
        BigFraction result = new BigFraction(this.num.multiply(other.dnm), this.dnm.multiply(other.num));
        result.reduceFraction();
        return result;
    }
    
    /**
     * Divides this by another fraction
     * @param other - the other fraction
     * @return - the result of this / other
     */
    public BigFraction divide(BigFraction other) {
        return this.div(other);
    }
    
    /**
     * Gets the absolute value of this fraction
     * @return the absolute fraction
     */
    public BigFraction abs() {
        return new BigFraction(num.abs(), dnm.abs());
    }
    
    /**
     * For testing purposes
     * @param args is not used
     */
    public static void main(String[] args) {
        BigFraction f1 = new BigFraction(23985447, 12481204);
        BigFraction f2 = new BigFraction(new BigInteger("1387451389754103845974"), new BigInteger("33483740193845710396"));
        
        System.out.println(f1 + "\t = " + f1.getDecimalForm());
        System.out.println(f2 + "\t = " + f2.getDecimalForm());
        System.out.println(f1.add(f2) + "\t = " + (f1.add(f2)).getDecimalForm());
        System.out.println(f1.sub(f2) + "\t = " + (f1.sub(f2)).getDecimalForm());
        System.out.println(f1.mult(f2) + "\t = " + (f1.mult(f2)).getDecimalForm());
        System.out.println(f1.div(f2) + "\t = " + (f1.div(f2)).getDecimalForm());
    }
}