package problem093;

import problem093.Operator;

public class DivisionOperator 
    extends Operator{

    @Override
    public Fraction compute(Fraction... operands) {
        if (operands.length != 2)
            return null;
        
        Fraction num1 = operands[0];
        Fraction num2 = operands[1];
        Fraction res = new Fraction( num1.numerator * num2.denom, num1.denom * num2.numerator);
        return Fraction.getReducedFraction(res);
    }

    @Override
    public String getCharRepresentation() {
        return "/";
    }
    
}
