package problem007;

import java.util.ArrayList;
import java.util.List;

public class Problem007 {

    /*
    By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
    What is the 10 001st prime number?
    */
    public static void main(String[] args) {
        
        List<Integer> primes = new ArrayList<>();
        int n = 2;
        while(primes.size() < 11000)
        {
            if (isPrime(n))
                primes.add(n);
            n++;
        }
        
        for (int i = 0; i < primes.size(); i++)
            System.out.println(primes.get(i) + " : " + i + " st");
        
        System.out.println("10001st: " + primes.get(10000));  //10001st b/c it starts at 0
    }
    
    private static boolean isPrime(int num) {
        if (num < 2) return false;
        
        for (int t = 2; t < num; t++)
        {
            if (num % t == 0) return false;
        }
        return true;
    }

}