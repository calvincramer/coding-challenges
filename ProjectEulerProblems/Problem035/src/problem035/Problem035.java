package problem035;

import java.util.ArrayList;
import java.util.List;

public class Problem035 {

    /*
    The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

    There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

    How many circular primes are there below one million?
    */
    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>();
        for (int n = 2; n < 1000000; n++) {
            if (isCircularPrime(n)) {
                primes.add(n);
                System.out.println(n);
            }
        }
        
        System.out.println("\nTotal: " + primes.size());
    }
    //answer: 55
    
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
    
    public static boolean isCircularPrime(int number) {
        if (!isPrimeFast(number))
            return false;
        
        if (number < 10)
            return isPrimeFast(number);
        
        List<Integer> digits = new ArrayList<>();
        int n = number;
        while (n > 0) {
            digits.add(0, n%10);
            n /= 10;
        }
        
        //cycle the number
        for (int i = 1; i <= digits.size(); i++) {
            int dig = digits.remove(0);
            digits.add(dig);    //to end
            
            int cycledNum = 0;
            int scale = 1;
            for (int k = digits.size()-1; k >= 0; k--) {
                cycledNum += scale * digits.get(k);
                scale *= 10;
            }
            
            if (!isPrimeFast(cycledNum))
                return false;
            
        }
        
        return true;
    }
    
    public static int getFirstDigit(int number) {
        String s = String.valueOf(Math.abs(number));
        char c = s.toCharArray()[0];
        return Integer.valueOf(c + "");
    }

}
