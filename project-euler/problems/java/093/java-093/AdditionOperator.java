package problem093;

public class AdditionOperator 
    extends Operator{

    @Override
    public Fraction compute(Fraction... operands) {
        if (operands.length != 2)
            return null;
        
        Fraction num1 = operands[0];
        Fraction num2 = operands[1];
        Fraction res = new Fraction( (num2.denom * num1.numerator) + (num1.denom * num2.numerator), num1.denom * num2.denom);
        return Fraction.getReducedFraction(res);
    }

    @Override
    public String getCharRepresentation() {
        return "+";
    }
}
