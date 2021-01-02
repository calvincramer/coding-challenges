package mathtools;

/**
 * Represents a fraction with 64 bit signed integers
 * Warning that numerator and denominator are assumed to be non-negative, but do not check for it
 */
public class Fraction 
    implements Comparable<Fraction> {
    
    public long num;      //represents the fraction
    public long dnm;
    
    /**
     * Construct a new fraction
     * @param numerator the top number
     * @param denominator the bottom number
     */
    public Fraction(long numerator, long denominator) {
        this.num = numerator;
        this.dnm = denominator;
    }
    
    /**
     * Calculated the decimal value of the fraction
     * If the denominator is zero, Double.NaN is return.
     * @return the decimal form of the fraction
     */
    public double getDecimalForm() {
        if (this.dnm == 0)
            return Double.NaN;
        return this.num * 1.0 / this.dnm;
    }
    
    /**
     * Swaps the numerator and denominator's value
     */
    public void invertFraction() {
        long temp = this.num;
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
     * @return a string representation of the fraction in the form of "numerator/denominator".
     */
    @Override 
    public String toString() {
        return "" + this.num + "/" + this.dnm;
    }

    /**
     * Reduces the fraction such that the numerator and denominator are relatively prime.
     */
    public void reduceFraction() {
        long gcd = MF.gcf(num, dnm);
        this.num /= gcd;
        this.dnm /= gcd;
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
    public int compareTo(Fraction other) {
        if (this.num * other.dnm == this.dnm * other.num)
            return 0;
        else if (this.num * other.dnm > this.dnm * other.num)
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
        else if (!(other instanceof Fraction))
            return false;
        else {
            Fraction o = (Fraction) other;
            return o.compareTo(this) == 0;
        }

    }
    
    /**
     * Adds this fraction to another fraction
     * @param other - the other fraction
     * @return - the sum of this + other
     */
    public Fraction add(Fraction other) {
        Fraction result = new Fraction(this.num * other.dnm + other.num * this.dnm, this.dnm * other.dnm);
        result.reduceFraction();
        return result;
    }
    
    /**
     * Subtracts this fraction from another fraction
     * @param other - the other fraction
     * @return - the result of this - other
     */
    public Fraction sub(Fraction other) {
        Fraction result = new Fraction(this.num * other.dnm - other.num * this.dnm, this.dnm * other.dnm);
        result.reduceFraction();
        return result;
    }
    
    /**
     * Subtracts this fraction from another fraction
     * @param other - the other fraction
     * @return - the result of this - other
     */
    public Fraction subtract(Fraction other) {
        return this.sub(other);
    }
    
    /**
     * Multiplies this by another fraction
     * @param other - the other fraction
     * @return - the result of this * other
     */
    public Fraction mult(Fraction other) {
        Fraction result = new Fraction(this.num * other.num, this.dnm * other.dnm);
        result.reduceFraction();
        return result;
    }
    
    /**
     * Multiplies this by another fraction
     * @param other - the other fraction
     * @return - the result of this * other
     */
    public Fraction multiply(Fraction other) {
        return this.mult(other);
    }
    
    /**
     * Divides this by another fraction
     * @param other - the other fraction
     * @return - the result of this / other
     */
    public Fraction div(Fraction other) {
        Fraction result = new Fraction(this.num * other.dnm, this.dnm * other.num);
        result.reduceFraction();
        return result;
    }
    
    /**
     * Divides this by another fraction
     * @param other - the other fraction
     * @return - the result of this / other
     */
    public Fraction divide(Fraction other) {
        return this.div(other);
    }
    
    /**
     * Gets the absolute value of this fraction
     * @return the absolute fraction
     */
    public Fraction abs() {
        return new Fraction(MF.abs(num), MF.abs(dnm));
    }
    

    /**
     * For testing purposes
     * @param args not used
     */
    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 4);
        System.out.println(f1.compareTo(f2));
        
        Fraction res = f1.add(f2);
        System.out.println(res);
        
        
        Fraction f3 = new Fraction(1, 8);
        res = f1.add(f3);
        System.out.println(res);
        
        res = f1.sub(f3);
        System.out.println(res);
        
        Fraction f6 = new Fraction(5, 31);
        Fraction f7 = new Fraction(5, 3);
        res = f6.mult(f7);
        System.out.println(res);
        
        res = f6.div(f7);
        System.out.println(res);
        
    }
}
