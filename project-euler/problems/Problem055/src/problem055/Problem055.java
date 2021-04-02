package problem055;

import java.math.BigInteger;

public class Problem055 {

    /*
    Lychrel numbers
    Problem 55
    If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.

    Not all numbers produce palindromes so quickly. For example,

    349 + 943 = 1292,
    1292 + 2921 = 4213
    4213 + 3124 = 7337

    That is, 349 took three iterations to arrive at a palindrome.

    Although no one has proved it yet, it is thought that some numbers, like 196, never produce a palindrome. A number that never forms a palindrome through the reverse and add process is called a Lychrel number. 
    Due to the theoretical nature of these numbers, and for the purpose of this problem, we shall assume that a number is Lychrel until proven otherwise. 
    In addition you are given that for every number below ten-thousand, it will either (i) become a palindrome in less than fifty iterations, or, (ii) no one, with all the computing power that exists, has managed so far to map it to a palindrome. In fact, 10677 is the first number to be shown to require over fifty iterations before producing a palindrome: 4668731596684224866951378664 (53 iterations, 28-digits).

    Surprisingly, there are palindromic numbers that are themselves Lychrel numbers; the first example is 4994.

    How many Lychrel numbers are there below ten-thousand?

    NOTE: Wording was modified slightly on 24 April 2007 to emphasise the theoretical nature of Lychrel numbers.

    */
    public static void main(String[] args) {
        int total = 0;
        
        for (int i = 0; i < 10000; i++) {
            if (isLychrelNumber(i)) {
                total++;
                System.out.println(i);
            } 
        }
        
        System.out.println("\nTotal: " + total);
    }
    //Total: 249
    
    
    public static boolean isLychrelNumber(int n) {
        BigInteger number = new BigInteger((n+reverseNumber(n))+"");
        final int maxTests = 50;
        int iteration = 1;
        
        while (iteration < maxTests) {
            if(isPalindrome(number.toString()))
                return false;
            else 
                number = number.add(reverseNumber(number));
            
            iteration++;
        }
        
        
        return true;
    }
    
    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }
    
    public static boolean isPalindrome(int n) {
        return isPalindrome(n+"");
    }
    
    public static int reverseNumber(int n) {
        String str = n+"";
        char[] reverse = new char[str.length()];
        for (int i = 0; i < str.length(); i++) 
            reverse[str.length()-1-i] = str.charAt(i);
        String revString = new String(reverse);
        return Integer.parseInt(revString);
    }
    
    public static BigInteger reverseNumber(BigInteger n) {
        String str = n.toString()+"";
        char[] reverse = new char[str.length()];
        for (int i = 0; i < str.length(); i++) 
            reverse[str.length()-1-i] = str.charAt(i);
        String revString = new String(reverse);
        return new BigInteger(revString);
    }
}
