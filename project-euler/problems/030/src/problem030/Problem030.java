package problem030;

import java.util.ArrayList;
import java.util.List;

public class Problem030 {

    /*
    Problem 30
    Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

    1634 = 1^4 + 6^4 + 3^4 + 4^4
    8208 = 8^4 + 2^4 + 0^4 + 8^4
    9474 = 9^4 + 4^4 + 7^4 + 4^4
    As 1 = 14 is not a sum it is not included.

    The sum of these numbers is 1634 + 8208 + 9474 = 19316.

    Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
    */
    public static void main(String[] args) {
        
        List<Long> nums = new ArrayList<>();
        
        for (long i = 2; i < 10000000; i++) {
            long sum = 0;
            long n = i;
            while (n > 0) {
                sum += pow(n%10, 5);
                n /= 10;
            }
            if (i == sum)
                nums.add(i);
        }
        
        for (long n : nums)
            System.out.println(n);
        
        long sum = 0;
        for (long n : nums)
            sum += n;
        
        System.out.println("\nSum: " + sum);
        
    }

    public static long pow (long a, int b) {
        if ( b == 0)        return 1;
        if ( b == 1)        return a;
        if (b % 2 == 0)     return     pow ( a * a, b/2); //even a=(a^2)^b/2
        else                return a * pow ( a * a, b/2); //odd  a=a*(a^2)^b/2

    }
}
