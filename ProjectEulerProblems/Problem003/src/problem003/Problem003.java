package problem003;

import java.util.ArrayList;
import java.util.List;

public class Problem003 {

    /*
    The prime factors of 13195 are 5, 7, 13 and 29.
    What is the largest prime factor of the number 600851475143 ?
    */
    public static void main(String[] args) {
        
        long prime = 600851475143L; //600 billion and change
        List<Long> factors = new ArrayList<>();
        
        for (long i = 2; i < prime; i++) {
            
            if (prime % i == 0)
            {
                factors.add(i);
                System.out.print("Factor: " + i);
                if (isPrimeFast(i)) 
                    System.out.println(" and is prime");
                else
                    System.out.println();
            }
            /*
            if (i % 1000000000 == 0) //ever 1 billion print i
                System.out.println("i= " + i);
                    */
        }
        
        System.out.println("\nALL FACTORS:");
        for (int i = 0; i < factors.size(); i++) 
        {
            System.out.print(factors.get(i));
            if (isPrimeFast(factors.get(i))) 
                System.out.println(" and is prime");
            else
                System.out.println();
        }


    }
    //Takes a really long time
    
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
    
    //answer: 6857
    //got lucky because program only got to 8462696833 factor (1.4% the way there)
    //stopped after 65 minutes
}