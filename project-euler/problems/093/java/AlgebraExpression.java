
package problem093;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgebraExpression {
    
    public List<Object> expression;     //may contain fractions, operators, and pointers to other AlgebraExpression(s)
    private static final String DELIMITERS = "+-[*]/()";
    private static final String REGEX_SPLITTER = "((?<=[" + DELIMITERS + "])|(?=[" + DELIMITERS + "]))";
    private static final Operator[] ORDER_OF_OPERATIONS = {new MultiplicationOperator(), new DivisionOperator(), new AdditionOperator(), new SubtractionOperator()};
    
    public AlgebraExpression(String str) {
        expression = new ArrayList<>();
        //remove all whitespace
        str = str.replaceAll("\\s", "");
        
        try {
            createExpression(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public AlgebraExpression(String[] tokens) {
        expression = new ArrayList<>();
        try {
            addTokensToExpression(tokens);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * () parenthesis
     * numbers -- integers -- floating point (not implemented yet
     * + - * /
     */
    private void createExpression(String str) throws Exception {
        String[] tokens = str.split(AlgebraExpression.REGEX_SPLITTER);
        addTokensToExpression(tokens);
    }
    
    
    private void addTokensToExpression(String[] tokens) throws Exception {
        //every token must match an operator or be a number, otherwise error
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            
            if (token.equals("+"))
                expression.add(new AdditionOperator());
            else if (token.equals("-"))
                expression.add(new SubtractionOperator());
            else if (token.equals("*"))
                expression.add(new MultiplicationOperator());
            else if (token.equals("/"))
                expression.add(new DivisionOperator());
            else if (isInteger(token))
                expression.add(new Fraction(Integer.parseInt(token), 1));
            
            else if (token.equals("(")) {
                //find index of matching brace
                int firstBraceIndex = i;
                int localLevel = 1; //went up one level from first brace
                i++;
                while (i < tokens.length) {
                    if (tokens[i].equals("("))
                        localLevel++;
                    else if (tokens[i].equals(")"))
                        localLevel--;
                    
                    i++;
                    if (localLevel == 0)
                        break;
                }
                if (localLevel != 0)
                    throw new Exception("could not find matching brace");
                
                i--;    //we are now at the index of the closing brace of the same level as the openning brace
                
                //get stuff inside braces
                String[] tokensInside = Arrays.copyOfRange(tokens, firstBraceIndex+1, i);
                AlgebraExpression subExpr = new AlgebraExpression(tokensInside);
                //add pointer to new expression
                expression.add(subExpr);
            }
            else if (token.equals(")"))
                throw new Exception("found mismatched brace: " + token);
            else
                throw new Exception("invalid token: " + token);
        }
    }
    
    
    
    
    public Fraction computeExpression() throws Exception {
        
        for (int i = 0; i < this.expression.size(); i++) {
            if (this.expression.get(i) instanceof AlgebraExpression) {
                AlgebraExpression subExpr = (AlgebraExpression) this.expression.get(i);
                this.expression.set(i, subExpr.computeExpression());
            }
        }
        //now there are no sub expressions
        //evaluate based on PEMDAS
        for (Operator op : ORDER_OF_OPERATIONS) {
            for (int i = 0; i < this.expression.size(); i++) {
                if (op.getClass().isInstance(this.expression.get(i))) {
                    //check for operators on either side
                    if (i < 1 || i >= this.expression.size() - 1)
                        throw new Exception("operator does not have two operands");
                    if (! (this.expression.get(i-1) instanceof Fraction))
                        throw new Exception("operator has not-number left operand");
                    if (! (this.expression.get(i+1) instanceof Fraction))
                        throw new Exception("operator has not-number right operand");
                    
                    Fraction result = op.compute((Fraction) expression.get(i-1), (Fraction) expression.get(i+1));
                    
                    this.expression.remove(i-1);    //left op
                    this.expression.remove(i-1);    //op
                    this.expression.remove(i-1);    //right op
                    this.expression.add(i-1, result);
                    
                    i--; //new op here
                }
            }
        }
        
        return (Fraction) this.expression.get(0);
    }
    

    
    /**
     * Credit: https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-to-see-if-a-string-represents-an-integer-in-java
     * @param str
     * @return 
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    
    @Override public String toString() {
        String str = "";
        for (Object obj : this.expression) {
            if (obj instanceof AlgebraExpression) {
                str += "(";
                str += obj.toString();
                str += ")";
            }
            else
                str += obj.toString();
        }
        return str;
    }
    
}