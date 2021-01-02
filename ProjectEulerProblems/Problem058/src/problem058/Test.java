/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem058;

/**
 *
 * @author Violin
 */
public class Test {
    
    public static void main(String[] args) {
        /*
        for (long i = 2; i < 1000000000; i++) {
            if (isPrime(i) != isPrimeFast(i))
                System.out.println(i);
        }
        */
        final long MAX = 100000;
        MathFunctions.startTimer();
        for (long i = 0; i < MAX; i++) {
            isPrime(i);
        }
        System.out.println("Old: " + MathFunctions.getElapledTime());
        
        MathFunctions.startTimer();
        for (long i = 0; i < MAX; i++) {
            isPrimeFast(i);
        }
        System.out.println("Fast: " + MathFunctions.getElapledTime());
    }
    
    public static boolean isPrime(long num) {
        if (num < 2) 
            return false;
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) return false;
        }
        return true;
    }
    public static boolean isPrimeFast(long num) {
        if (num < 2) 
            return false;
        for (int n = 2; n <= (int) Math.sqrt(num); n++) {
            if (num % n == 0) return false;
        }
        
        return true;
    }
    
}
