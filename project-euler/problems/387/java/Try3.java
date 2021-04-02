package problem387;

import mathtools.MF;

public class Try3 {
    
    public static final int RT_SIZE = 10000000;
    public static boolean[] filledIn = new boolean[RT_SIZE];
    public static boolean[] rt_table = new boolean[RT_SIZE];
    
    public static void main(String[] args) {
        System.out.println("3!");
        
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
                    if (num < RT_SIZE && filledIn[(int)num]) {
                        if (rt_table[(int)num] == true)
                            sum += n;
                        else
                            continue;
                    }
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
                    //fill in table
                    if (num < RT_SIZE && !filledIn[(int)num]) {
                        filledIn[(int)num] = true;
                        rt_table[(int)num] = isRightTruncatable;
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