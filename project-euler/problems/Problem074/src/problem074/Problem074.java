package problem074;

import java.util.ArrayList;
import java.util.List;

/*
The number 145 is well known for the property that the sum of the factorial of its digits is equal to 145:

1! + 4! + 5! = 1 + 24 + 120 = 145

Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to 169; it turns out that there are only three such loops that exist:

169 → 363601 → 1454 → 169
871 → 45361 → 871
872 → 45362 → 872

It is not difficult to prove that EVERY starting number will eventually get stuck in a loop. For example,

69 → 363600 → 1454 → 169 → 363601 (→ 1454)
78 → 45360 → 871 → 45361 (→ 871)
540 → 145 (→ 145)

Starting with 69 produces a chain of five non-repeating terms, but the longest non-repeating chain with a starting number below one million is sixty terms.

How many chains, with a starting number below one million, contain exactly sixty non-repeating terms?
*/
public class Problem074 {

    public static void main(String[] args) {
        int[] factorals = new int[10];
        for (int i = 0; i < factorals.length; i++)
            factorals[i] = factoral(i);
        int numEqual60 = 0;
        
        
        for (int start = 0; start < 1000000; start++) {
            List<Integer> chain = new ArrayList<>();
            chain.add(start);
            boolean alreadyInChain = false;
            do {
                int nextNumber = loopNumber(chain.get(chain.size()-1));
                alreadyInChain = chain.contains(nextNumber);
                chain.add(nextNumber);
                    
            } while (!alreadyInChain);     //while last element unique in chain
            
            //print chain
            //for (int num : chain)
            //    System.out.print(num + " -> ");
            //System.out.println("");
            //System.out.println(start + " : size: " + chain.size());
            if (chain.size() - 1 == 60) {
                numEqual60++;
                System.out.println(start + " : size: " + (chain.size() - 1));
            }
            
        }
        System.out.println("Total:\t" + numEqual60);
        
        
    }
    
    public static int loopNumber(int n) {
        char[] digits = (n+"").toCharArray();
        int total = 0;
        for (char digit : digits)
            total += factoral(Character.getNumericValue(digit));
        
        return total;
    }
    
    public static int factoral(int n) {
        if (n <= 1)
            return 1;
        return factoral(n-1) * n;
    }
    
}
//answer : 402