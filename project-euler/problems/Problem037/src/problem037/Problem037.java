package problem037;

import java.util.ArrayList;
import java.util.List;

public class Problem037 {

    /*
    The number 3797 has an interesting property. 
    Being prime itself, it is possible to continuously remove digits from left to right, and remain prime at each stage: 
    3797, 797, 97, and 7.
    Similarly we can work from right to left: 
    3797, 379, 37, and 3.

    Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

    NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
    */
    public static void main(String[] args) {
        
        List<Integer> truncPrimes = new ArrayList<>();
        
        for (int n = 10; n < 1000000; n++) {
            if (isPrimeFast(n)) {
                
                boolean isTruncNumber = true;
                
                String numStr = "" + n;
                while (numStr.length() >= 2) {  //truncate from left side
                    numStr = numStr.substring(1);
                    int truncNumber = Integer.parseInt(numStr);
                    if ( !isPrimeFast(truncNumber)) {
                        isTruncNumber = false;
                        break;
                    }
                        
                }
                
                numStr = "" + n;
                while (numStr.length() >= 2) {  //truncate from right side
                    numStr = numStr.substring(0, numStr.length()-1);
                    int truncNumber = Integer.parseInt(numStr);
                    if ( !isPrimeFast(truncNumber)) {
                        isTruncNumber = false;
                        break;
                    }
                }
                
                if (isTruncNumber)
                    truncPrimes.add(n);
            }
        }
        
        int total = 0;
        for (int n : truncPrimes) {
            System.out.println(n);
            total += n;
        }
        
        System.out.println("\nTotal: " + total);
        
    }
    /*
    answer:
    Total: 748317
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
