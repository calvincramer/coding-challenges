package problem032;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Problem032 {

    /*
    We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once.
    For example, the 5-digit number, 15234, is 1 through 5 pandigital.

    The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

    Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.
    HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
    */
    public static void main(String[] args) {
        
        MF.startTimer();
        
        List<MMP> pandigitals = new ArrayList<>();
        
        for (long m1 = 1; m1 <= 9999; m1++) {   //numbers up to 4 digits
            for (long m2 = 1; m2 <= 9999; m2++) {

                long prod = m1*m2;
                
                String m1Str = String.valueOf(m1);
                String m2Str = String.valueOf(m2);
                String prodStr = String.valueOf(prod);
                
                if (m1Str.length() + m2Str.length() + prodStr.length() > 9)
                    break;
                
                boolean pandig = true;
                
                //look for any zeroes
                for (char c : m1Str.toCharArray())
                    if (c == '0')
                        pandig = false;
                for (char c : m2Str.toCharArray())
                    if (c == '0')
                        pandig = false;
                for (char c : prodStr.toCharArray())
                    if (c == '0')
                        pandig = false;
                
                if (pandig) {   //if there werent any zeroes
                    for(int i = 1; i <= 9; i++) { //for 1 : 9
                        char t = (char)(i+'0');
                        int numOccur = 0;

                        for (char c : m1Str.toCharArray())
                            if (t == c)
                                numOccur++;
                        for (char c : m2Str.toCharArray())
                            if (t == c)
                                numOccur++;
                        for (char c : prodStr.toCharArray())
                            if (t == c)
                                numOccur++;

                        if (numOccur != 1) {
                            pandig = false;
                            break;
                        }
                    }
                }
                
                if (pandig) {
                    System.out.println(m1 + "x" + m2 + " = " + prod + "\t pandigital");
                    MMP toAdd = new MMP();
                    toAdd.multiplicand = m1;
                    toAdd.multiplier = m2;
                    toAdd.product = prod;
                    pandigitals.add(toAdd);
                }
                
            }
        }
        
        long sumOfAllPanProds = 0;
        for (int i = 0; i < pandigitals.size(); i++) 
            sumOfAllPanProds += pandigitals.get(i).product;
        System.out.println("Sum of all products: " + sumOfAllPanProds);
        
        List<Long> uniqueProds = new ArrayList<>();
        for (int i = 0; i < pandigitals.size(); i++) {
            boolean alreadyIn = false;
            for (int j = 0; j < uniqueProds.size(); j++) {
                if (uniqueProds.get(j) == pandigitals.get(i).product) {
                    alreadyIn = true;
                    break;
                }
            }
            if (!alreadyIn) {
                uniqueProds.add(pandigitals.get(i).product);
            }
                    
        }
        System.out.println();
        System.out.println("Unique prods:");
        for (long n : uniqueProds)
            System.out.println(n);
        long sumUnique = 0;
        for (int i = 0; i < uniqueProds.size(); i++) 
            sumUnique += uniqueProds.get(i);
        System.out.println("Sum of all products: " + sumUnique);
        System.out.println(MF.getElapsedSeconds());
        
    }
    //answer: 45228
    
    private static class MMP {
        public long multiplicand;
        public long multiplier;
        public long product;
    }

}
