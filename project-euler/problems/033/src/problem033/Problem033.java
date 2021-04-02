package problem033;

import java.util.ArrayList;
import java.util.List;

public class Problem033 {

    /*
    The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 
    49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

    We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

    There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.

    If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
    */
    public static void main(String[] args) {
        
        List<Integer> numerators = new ArrayList<>();
        List<Integer> denominators = new ArrayList<>();
        
        for (int num = 10; num <= 99; num++) {  //two digit numbers
        for (int den = 10; den <= 99; den++) {
            String numStr = String.valueOf(num);
            String denStr = String.valueOf(den);
            if (numStr.charAt(0) == numStr.charAt(1) && denStr.charAt(0) == denStr.charAt(1))
                continue;
            
            int d1 = num / 10;  //10's digit of numerator
            int d2 = den % 10;  //1's digit of denominator
            
            if (numStr.charAt(1) != denStr.charAt(0))
                continue;
            
            double actual = num * 1.0 / den;
            double newAns = d1 * 1.0 / d2;
            
            if (Math.abs(actual - newAns) < 0.0000001 && actual < 1.0) {
                numerators.add(num);
                denominators.add(den);
                
                System.out.println(num + "/" + den + " = " + d1 + "/" + d2);
            }
            
        }}
        
        /*
        for(int i = 0; i < numerators.size(); i++) {
            System.out.println(numerators.get(i) + "/" + denominators.get(i));
        }
                */
        
        int prodNums = 1;
        int prodDenoms = 1;
        for (int i = 1; i < numerators.size(); i++) {
            prodNums *= numerators.get(i);
            prodDenoms *= denominators.get(i);
        }
        System.out.println("\nProduct of all fractions:");
        System.out.println(prodNums + "/" + prodDenoms);
        
        System.out.println("\nReduced form:");
        Integer num = prodNums;
        Integer den = prodDenoms;
        NumberPair np = reduceFraction(num, den);
        System.out.println(np.n1 + "/" + np.n2);
    }
    //answer = 100 (1/100 is reduced form of product of the reduced fractions)
    
    public static NumberPair reduceFraction(Integer num, Integer denom) {
        for (int n = 2; n <= num; n++) {
            if (num % n == 0 && denom % n == 0) {
                num /= n;
                denom /= n;
                n--;
            }
            
        }
        NumberPair toReturn = new NumberPair();
        toReturn.n1 = num;
        toReturn.n2 = denom;
        return toReturn;
    }
    
    private static class NumberPair {
        public int n1;
        public int n2;
    }

}
