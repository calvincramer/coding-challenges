package problem034;

import java.util.ArrayList;
import java.util.List;

public class Problem034 {

    /*
    145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

    Find the sum of all numbers which are equal to the sum of the factorial of their digits.

    Note: as 1! = 1 and 2! = 2 are not sums they are not included.
    */
    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<Integer>();
        
        int n = 3;
        int haventFound = 0;
        final int MAX = 10000000;
        while (haventFound < MAX) {
            
            int total = 0;
            int number = n;
            while (number > 0) {
                int digit = number % 10;
                total += factorial(digit);
                number /= 10;
            }
            
            if (total == n) {
                haventFound = 0;
                nums.add(n);
            } else {
                haventFound++;
            }
            
            n++;
        }
        
        for (int r : nums) {
            System.out.println(r);
        }
    }
    //answer: only 145 and 40585 work
    //so 145 + 40585 = 40730
    
    public static int factorial(int n) {
        int f = 1;
        while (n > 1) {
            f *= n;
            n--;
        }
        return f;
    }

}
