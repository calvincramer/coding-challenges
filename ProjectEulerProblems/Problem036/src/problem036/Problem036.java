package problem036;

public class Problem036 {

    /*
    The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.

    Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

    (Please note that the palindromic number, in either base, may not include leading zeros.)
    */
    public static void main(String[] args) {
        
        int numOfPals = 0;
        int sumOfPals = 0;
        
        for (int n = 0; n < 1000000; n++) {
            
            if (isPalindrome("" + n) && isPalindrome(Integer.toBinaryString(n))) {
                numOfPals++;
                sumOfPals += n;
                System.out.println(n + "\t" + Integer.toBinaryString(n) + "\tpal");
            }

        }
        System.out.println("\nNumber of pals: " + numOfPals);
        System.out.println("Sum of all: " + sumOfPals);
    }
    /*
    answer:
    Number of pals: 20
    Sum of all: 872187
    */
    
    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }

}