package problem051;

import java.util.ArrayList;
import java.util.List;

public class Problem051 {

    /*
    By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values: 13, 23, 43, 53, 73, and 83, are all prime.

    By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example having seven primes among the ten generated numbers, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773, and 56993. 
    Consequently 56003, being the first member of this family, is the smallest prime with this property.

    Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the same digit, is part of an eight prime value family.
    */
    public static void main(String[] args) {
        
        long number = 0;
        char[] md;          //masked digits will have an '*', and space otherwise
        
        long greatestPrimes = 0;
        
        while (true) {
            
            md = new char[(number+"").length()];
            for (int r = 0; r < md.length; r++) //fill with spaces
                md[r] = ' ';
            boolean reachedEnd = false;
            
            //if (number % 10000 == 0)
            //    System.out.println("Testing: " + number);
            //System.out.println(number);
            
            while (!reachedEnd) {   //loop through all possible wild card masks
                //increment mask
                int k = md.length - 1;
                while (md[k] == '*' && k > 0) {
                    md[k] = ' ';
                    k--;
                }
                md[k] = '*';
                
                //print stuff
                /*
                System.out.print("Masking [");
                for (char c : md)
                    System.out.print(c);
                System.out.println("]\t");
                */
                
                //mask the number with 0 - 9 (and 1 - 9 if the leftmost digit is wild)
                char[] maskedNumDigits = (number + "").toCharArray();
                int numPrimes = 0;
                for (int wild = (md[0] == '*') ? 1 : 0; wild <= 9; wild++) {
                    
                    for (int i = 0; i < md.length; i++)     //set all wild cards to digit
                        if (md[i] == '*')
                            maskedNumDigits[i] = (char) (wild + 48);
                    long maskedNum = Integer.parseInt(String.valueOf(maskedNumDigits));

                    if (isPrimeFast(maskedNum))
                        numPrimes++;
                }
                
                    
                //System.out.println(number + " has " + numPrimes + " primes in " + strToMask);
                
                //check for greatest number of primes
                if (numPrimes > greatestPrimes) {
                    greatestPrimes = numPrimes;
                    System.out.print(number + " has " + numPrimes + " primes in [");
                    for (char c : md) 
                        System.out.print(c);
                    System.out.println("]");
                    
                    //print all nums in primes family
                    maskedNumDigits = (number + "").toCharArray();
                    for (int wild = (md[0] == '*') ? 1 : 0; wild <= 9; wild++) {
                        for (int i = 0; i < md.length; i++)
                            if (md[i] == '*')
                                maskedNumDigits[i] = (char) (wild + 48);
                        long maskedNum = Integer.parseInt(String.valueOf(maskedNumDigits));
                        if (isPrimeFast(maskedNum))
                            System.out.println(maskedNum);
                    }
                    
                    //if (numPrimes >= 8)
                    //    break;
                }
                
                
                //check for end (find at least one non wild card
                boolean allWild = true;
                for (int i = 0 ; i < md.length; i++) {
                    if (md[i] == ' ') {
                        allWild = false;
                        break;
                    }
                }
                if (allWild)
                    reachedEnd = true;
                
                
                
                
            }

            number++;
        }
                
        //System.out.println(number);
    }
    /*
    answer:
    120303 has 8 primes in [* * * ]
    121313
    222323
    323333
    424343
    525353
    626363
    828383
    929393
    */

    public static boolean isPrimeFast(long num) {
        if (num < 2) {
            return false;
        }
        for (int n = 2; n <= (int) Math.sqrt(num); n++) {
            if (num % n == 0) {
                return false;
            }
        }

        return true;
    }
    
}
