package problem125;

import mathtools.MF;

public class Problem125 {

    /*
    The palindromic number 595 is interesting because it can be written as the sum of consecutive squares: 6^2 + 7^2 + 8^2 + 9^2 + 10^2 + 11^2 + 12^2.

    There are exactly eleven palindromes below one-thousand that can be written as consecutive square sums, and the sum of these palindromes is 4164. 
    Note that 1 = 0^2 + 1^2 has not been included as this problem is concerned with the squares of positive integers.

    Find the sum of all the numbers less than 108 that are both palindromic and can be written as the sum of consecutive squares.
    */
    public static void main(String[] args) {
        final int ten8 = 100000000;
        
        long total = 0;
        
        for (int i = 2; i < ten8; i++) {
            if (MF.isPalindrome(i)) {
                //ystem.out.println(i);
                if (canSumSqr(i)) {
                    System.out.println(i);
                    total += i;
                }
            }
        }
        System.out.println("\nTotal:\t" + total);
        
        
    }
    
    public static boolean canSumSqr(int n) {
        if (n < 2)
            return false;
        if (MF.isPerfectSquare(n))
            return false;
        
        for (int start = 1; start <= (int) Math.sqrt(n); start++) {
            int sum = 0;
            int i = start;
            while (sum < n) {
                sum += i*i;
                i++;
            }
            if (sum == n)
                return true;
        }
        
        return false;
    }
    
}
//Total:	2906969179