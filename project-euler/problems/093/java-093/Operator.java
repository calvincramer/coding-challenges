
package problem093;

public abstract class Operator {
    
    public abstract Fraction compute(Fraction... operands);
    public abstract String getCharRepresentation();
    
    @Override public String toString() {
        return getCharRepresentation();
    }
}
