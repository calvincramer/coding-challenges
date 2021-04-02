package problem092;

import java.util.ArrayList;
import java.util.List;

public class Problem092 {

    /*
    A number chain is created by continuously adding the square of the digits in a number to form a new number until it has been seen before.

    For example,

    44 → 32 → 13 → 10 → 1 → 1
    85 → 89 → 145 → 42 → 20 → 4 → 16 → 37 → 58 → 89

    Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop. What is most amazing is that EVERY starting number will eventually arrive at 1 or 89.

    How many starting numbers below ten million will arrive at 89?
    */
    public static void main(String[] args) {
        
        long num89 = 0;
        
        for (long startNum = 1; startNum < 10000000; startNum++) {
            //chain num
            List<Long> chain = new ArrayList<>();
            chain.add(startNum);
            boolean wasOnChain = false;
            do {
                long numToAdd = squareDigits( chain.get(chain.size()-1) );  //iterate last number on chain
                wasOnChain = chain.contains(numToAdd);
                chain.add(numToAdd);
            } while (!wasOnChain);
            
            for (long n : chain) {
                if (n == 89) {
                    num89++;
                    break;
                }
            }
                
            /*
            System.out.print(startNum + ": \t");
            for (long n : chain)
                System.out.print(n + " -> ");
            System.out.println();
                    */
        }
        
        System.out.println("Num 89: " + num89);
        
    }
    
    public static long squareDigits(long num) {
        char[] digs = (""+num).toCharArray();
        long newNum = 0;
        for (char digit : digs) {
            long t = Character.getNumericValue(digit);
            newNum += t*t;
        }
        return newNum;
    }

}
//Num 89: 8581146
//took about 6 or 7 minutes total :)