
package problem093;

import mathtools.MF;

public class Fraction {
    public long numerator;
    public long denom;
    
    public Fraction(long numerator, long denom) {
        this.numerator = numerator;
        this.denom = denom;
    }
    
    public static Fraction getReducedFraction(Fraction f) {
        long gcf = MF.gcf(f.numerator, f.denom);
        if (gcf == 0)
            return f;
        return new Fraction(f.numerator / gcf, f.denom / gcf);
    }
    
    public Number getValue() {
        if (denom == 0)
            return null;
        if (numerator % denom == 0)
            return new Long(numerator / denom);
        else
            return new Double(numerator * 1.0 / denom);
    }
    @Override
    public String toString() {
        return "" + this.getValue();
    }
}
