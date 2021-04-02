package problem004;

import java.util.ArrayList;
import java.util.List;

public class Problem004 {

  /*
    A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 
        9009 = 91 × 99.
    Find the largest palindrome made from the product of two 3-digit numbers.
    */  
    public static void main(String[] args) {
        
        List<NPair> pals = new ArrayList<>();
        
        for (int n1 = 100; n1 < 1000; n1++) {
            for (int n2 = 100; n2 < 1000; n2++) {
                
                int product = n1 * n2;
                if (isPalindrome(product))
                {
                    pals.add(new NPair(n1, n2));
                }
                
            }
        }
        for (int i = 0; i < pals.size(); i++) { //output pals
            int num1 = pals.get(i).n1;
            int num2 = pals.get(i).n2;
            System.out.println(num1 + " x " + num2 + " = " + (num1*num2));
        }
        
        int largestPalIndex = 0;
        int largestPal = pals.get(0).n1 * pals.get(0).n2;
        for (int i = 0; i < pals.size(); i++)
        {
            int tempPal = pals.get(i).n1 * pals.get(i).n2;
            if (tempPal > largestPal)
            {
                largestPal = tempPal;
                largestPalIndex = i;
            }
        }
        System.out.println("\nLARGEST PAL: " + pals.get(largestPalIndex).n1 + " x " + pals.get(largestPalIndex).n2 + " = " + largestPal);
        
        
    } //answer 906609 (913x993)

    private static boolean isPalindrome(int n) {
        int maxDigits = (int) Math.log10(n)+1;
        int r = 1;
        int l = maxDigits;
        
        while (r < l) {
            int digitRight = (int) ((n / Math.pow(10, r-1)) % 10);
            int digitLeft = (int) ((n / Math.pow(10, l-1)) % 10);
            
            if (digitRight != digitLeft) return false;
            r++;
            l--;
        }
        return true;
    }
    
    private static class NPair
    {
        protected int n1;
        protected int n2;
        
        public NPair(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

    }
}
