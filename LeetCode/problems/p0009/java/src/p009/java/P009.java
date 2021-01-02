//[COMPLETED]

package p009;

import java.util.Random;

public class P009 {

    public static void main(String[] args) {
        Solution s = new Solution();
        Random rng = new Random(System.currentTimeMillis());
        for (int i = -15; i < 15; i++) {
            int n = rng.nextInt(10000) - 5000;
            System.out.print(n + "\t");
            if (s.isPalindrome(n))
                System.out.println("pali");
            else
                System.out.println("");
        }
        
        System.out.println("");
        System.out.println("-1" + "\t" + s.isPalindrome(-1));
        System.out.println("-11" + "\t" + s.isPalindrome(-11));
        System.out.println("-111" + "\t" + s.isPalindrome(-111));
        System.out.println("-1221" + "\t" + s.isPalindrome(-1221));
        System.out.println("-1234" + "\t" + s.isPalindrome(-1234));
        System.out.println("-2147447412" + "\t" + s.isPalindrome(-2147447412));

    }

private static class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        
        int left = numDigits(x) - 1;
        int right = 0;
        while (left + 1 > right) {
            if (getDigit(x, left) != getDigit(x, right))
                return false;
            left--;
            right++;
        }
        return true;
    }
    
    public int getDigit(int num, int digit) {
        if (digit < 0)
            return Integer.MAX_VALUE;
        
        for (int n = 0; n < digit; n++)
            num /= 10;
        
        return num % 10;
    }
    
    public int numDigits(int num) {
        return (""+num).length();
    }
    
    
}

}
