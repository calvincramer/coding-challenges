package problem052;

import java.util.ArrayList;
import java.util.List;

public class Problem052 {


    /*
    Permuted multiples
Problem 52
It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits, but in a different order.

Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
    */
    public static void main(String[] args) {
        
        final int MAX_MULT = 6;
        
        int n = 1;
        
        
        while (true) {
            
            char[] nDigits = (n+"").toCharArray();
            
            //System.out.println(n);
            
            boolean sameDigits = true;
            for (int mult = 2; mult <= MAX_MULT; mult++) {
                char[] mDigits = ((n * mult) + "").toCharArray();
                
                for (char md : mDigits) {
                    boolean foundDigit = false;
                    for (int i = 0; i < nDigits.length; i++) {
                        if (nDigits[i] == md) {
                            foundDigit = true;
                            break;
                        }
                    }
                    if (!foundDigit) {
                        sameDigits = false;
                        break;
                    }
                }
                
            }
            if (sameDigits)
                break;
            
            n++;
            
        }
        System.out.println("\nAnswer:");
        System.out.println(n);
        System.out.println("2n: " + (2*n));
        System.out.println("3n: " + (3*n));
        System.out.println("4n: " + (4*n));
        System.out.println("5n: " + (5*n));
        System.out.println("6n: " + (6*n));
    }
    /*
    Answer:
    142857
    2n: 285714
    3n: 428571
    4n: 571428
    5n: 714285
    6n: 857142
    */
    
}
