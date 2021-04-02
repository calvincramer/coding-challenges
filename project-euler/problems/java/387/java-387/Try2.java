package problem387;

import mathtools.MF;

public class Try2 {
    
    public static void main(String[] args) {
        System.out.println("2!");
        
        for (long max = 10000; max <= 100000000000000L; max *= 10) {
            
            MF.startTimer();
            System.out.print("max= " + max);
            long sum = 0;
            
            for (long n = 0; n < max; n++) {
                //determine if qualifies
                
                    //if (!MF.isPrimeFaster(n))
                    if (!MF.isPrimeByPrimes(n))
                        continue;

                    long num = n/10;
                    long sumDigits = MF.sumOfDigits(num);
                    
                    //is harshad?
                    if (sumDigits == 0)
                        continue;
                    else if (num % sumDigits != 0)
                        continue;
                    
                    //is strong?
                    if (!MF.isPrimeFaster(num / sumDigits))
                        continue;
                    
                    //right truncatable?
                    boolean isRightTruncatable = true;
                    while (num >= 10) {

                        sumDigits -= num % 10;              //subtract digit to be removed from total sum of digits
                        num /= 10;

                        //test for harshad
                        if (sumDigits == 0) {
                            isRightTruncatable = false;
                            break;
                        } else if (num % sumDigits != 0) {
                            isRightTruncatable = false;
                            break;
                        }
                    }
                    if (isRightTruncatable == false)
                        continue;
                    else
                        sum += n;   //qualifies as strong right truncatable harshad prime

                
            }
            
            System.out.println("\t" + sum + "\tt= " + MF.getElapsedSeconds());
        }

        
    }
    
}
/*
max= 10000	90619	t= 0.002987825
max= 100000	388207	t= 0.010515859
max= 1000000	1188721	t= 0.156105396
max= 10000000	10057005	t= 2.678586882
max= 100000000	130459097	t= 57.56883283
*/
/*
Minimal efficiency gains by making sure to not recompute known values, and keeping track of the sum of digits while cutting off digits
Big efficiency gains by precomputing primes, and using MF.isPrimeByPrimes()
*/