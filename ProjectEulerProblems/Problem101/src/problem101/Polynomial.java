package problem101;

public class Polynomial {

    public long[] coefficients; //represents a + bx + cx^2 + ...
    
    public Polynomial(long[] coefficients) {
        this.coefficients = coefficients;
    }
    
    public long calculate(long x) {
        if (coefficients == null || coefficients.length == 0)
            return 0;
        
        long result = 0;
        long mult = 1;
        for (long coef : coefficients) {
            result += coef * mult;
            mult *= x;
        }
        
        return result;
    }
    
    
    public static void main(String[] args) {
        long[] coefs = new long[]{1,1,1};
        Polynomial test = new Polynomial(coefs);
        for (long x = 0; x < 10; x++) {
            System.out.println(x + ": 1 + x + x^2 = " + test.calculate(x));
        }
    }
}
