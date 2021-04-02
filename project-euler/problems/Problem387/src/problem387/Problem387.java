package problem387;

import mathtools.MF;

public class Problem387 {
    
    /*
    A Harshad or Niven number is a number that is divisible by the sum of its digits. 
    201 is a Harshad number because it is divisible by 3 (the sum of its digits.) 
    When we truncate the last digit from 201, we get 20, which is a Harshad number. 
    When we truncate the last digit from 20, we get 2, which is also a Harshad number. 
    Let's call a Harshad number that, while recursively truncating the last digit, always results in a Harshad number a right truncatable Harshad number.

    Also: 
    201/3=67 which is prime. 
    Let's call a Harshad number that, when divided by the sum of its digits, results in a prime a strong Harshad number.

    Now take the number 2011 which is prime. 
    When we truncate the last digit from it we get 201, a strong Harshad number that is also right truncatable. 
    Let's call such primes strong, right truncatable Harshad primes.

    You are given that the sum of the strong, right truncatable Harshad primes less than 10000 is 90619.

    Find the sum of the strong, right truncatable Harshad primes less than 10^14.
    */
    public static void main(String[] args) {
        
        for (long max = 10000; max <= 100000000000000L; max *= 10) {
            MF.startTimer();
            System.out.print("max= " + max);
            long sum = 0;
            for (long n = 0; n < max; n++) {
                if (qualifies(n)) {
                    //System.out.println(n + " \tsrthp");
                    sum += n;
                }
            }
            System.out.println("\t" + sum + "\tt= " + MF.getElapsedSeconds());
        }

        
    }
    
    public static boolean qualifies(long num) {
        if (!MF.isPrimeFaster(num))
            return false;
        
        num /= 10;
        return isRightTruncHarshad(num) && isStrong(num);
    }
    
    public static boolean isHarshad(long num) {
        long sumDig = MF.sumOfDigits(num);
        if (sumDig == 0)
            return false;
        return num % MF.sumOfDigits(num) == 0;
    }
    
    public static boolean isRightTruncHarshad(long num) {
        if (!isHarshad(num))
            return false;
        
        while (num >= 10) {
            //strip last digit
            
            num /= 10;
            
            if (!isHarshad(num))
                return false;
        }
        
        return true;
    }
    
    public static boolean isStrong(long num) {
        return MF.isPrimeFaster(num / MF.sumOfDigits(num));
    }
}
/*
max= 10000	90619	t= 0.001877477
max= 100000	388207	t= 0.010934213
max= 1000000	1188721	t= 0.188846933
max= 10000000	10057005	t= 4.77757716
max= 100000000	130459097	t= 125.953749517
*/


//to speed up
//for right truncatable: dynamic programming, keep results
//for right truncatable: keep a running sum of digits, when cutting off digit, just subtract that digit from total
//generate all primes beforehand?, only check primes for strong righttruncatable harshad numbers?
