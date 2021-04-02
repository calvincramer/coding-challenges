package problem046;

import java.util.ArrayList;
import java.util.List;

public class Problem046 {

    /*
    It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.

    9 = 7 + 2×1^2
    15 = 7 + 2×2^2
    21 = 3 + 2×3^2
    25 = 7 + 2×3^2
    27 = 19 + 2×2^2
    33 = 31 + 2×1^2

    It turns out that the conjecture was false.

    What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
    */
    public static void main(String[] args) {

        List<Integer> oddCompNums = new ArrayList<>();
        int n = 0;
        while (oddCompNums.size() < 10000) {
            if (isCompositeNumber(n) && n % 2 == 1)
                oddCompNums.add(n);
            n++;
        }
        
        int highestComp = oddCompNums.get(oddCompNums.size()-1);
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        n = 3;
        
        while(primes.get(primes.size()-1) <= highestComp) {
            //add another prime
            if (isPrime(n))
                primes.add(n);
            n++;
        }
        
        
        for (int oddComp : oddCompNums) {
            //search for combination
            int maxSqaure = (int) Math.sqrt(oddComp);
            
            boolean foundMatch = false;
            
            for (int prime : primes) {
                for (int sq = 1; sq <= maxSqaure; sq++) {
                    int total = prime + (2 * sq * sq);
                    if (total == oddComp) {
                        foundMatch = true;
                        break;
                    }
                }
                if (foundMatch)
                    break;
            }
            
            if (!foundMatch)
                System.out.println(oddComp);
        }
        
        
    }
    //answer : 5777
    
    public static boolean isCompositeNumber(int n) {
        
        for (int i = 2; i < n; i++) 
            if (n % i == 0)
                return true;        
        
        return false;
    }
    
    public static boolean isPrime(int num) {
        if (num < 2) 
            return false;
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) return false;
        }
        return true;
    }

}
