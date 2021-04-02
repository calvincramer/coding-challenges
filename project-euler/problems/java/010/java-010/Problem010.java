package problem010;

import java.util.ArrayList;
import java.util.List;

public class Problem010 {

    /*
    The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
    Find the sum of all the primes below two million.
    */
    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < 2000000; i++) {
            if (isPrimeFast(i))
                primes.add(i);
            if (i % 10000 == 0) System.out.println(i);
        }
        
        long total = 0;
        System.out.println();
        for (int i = 0; i < primes.size(); i++) {
            total += primes.get(i);
            System.out.println(primes.get(i));
        }
        System.out.println("Total: " + total);
    }//1179908154

    
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

