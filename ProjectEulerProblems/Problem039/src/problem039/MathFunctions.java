package problem039;

import java.util.ArrayList;
import java.util.List;

public class MathFunctions {
    
    private static long startTime = System.currentTimeMillis();
    
    public static void startTimer() {
        MathFunctions.startTime = System.currentTimeMillis();
    }
    
    public static long getElapledTime() {
        return System.currentTimeMillis() - MathFunctions.startTime;
    }
    
    public static boolean isPrime(int num) {
        if (num < 2) 
            return false;
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) return false;
        }
        return true;
    }
    
    public static boolean isCircularPrime(int number) {
        if (!isPrime(number))
            return false;
        
        if (number < 10)
            return isPrime(number);
        
        List<Integer> digits = new ArrayList<>();
        int n = number;
        while (n > 0) {
            digits.add(0, n%10);
            n /= 10;
        }
        
        //cycle the number
        for (int i = 1; i <= digits.size(); i++) {
            int dig = digits.remove(0);
            digits.add(dig);    //to end
            
            int cycledNum = 0;
            int scale = 1;
            for (int k = digits.size()-1; k >= 0; k--) {
                cycledNum += scale * digits.get(k);
                scale *= 10;
            }
            
            if (!isPrime(cycledNum))
                return false;
            
        }
        
        return true;
    }
    
    public static int getFirstDigit(int number) {
        String s = String.valueOf(Math.abs(number));
        char c = s.toCharArray()[0];
        return Integer.valueOf(c + "");
    }
    
    public static List<Integer> getFactorsOf(Integer n) {
        List<Integer> factors = new ArrayList<>();
        
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0)
                factors.add(i);
        }
        factors.add(n); //every number is a factor of itself
        
        return factors;
    }
    
    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }
    
    public static boolean isPalindromeNumber(int n) {
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
    
    /**
     * Converts an integer to its string representation, of how one would say the number out loud.
     * Currently supports numbers 0 to 1000
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(int n) {
        if (n < 10)
            return singleDigitToString(n);
        if (n < 20)
            return tensToString(n);
        if (n < 100)
            return lessThan100ToString(n);
        if (n < 1000)
            return lessThan1000ToString(n);
        if (n == 1000)
            return "one thousand";
            
        return "NOT SUPPORTED YET";
    }
    
    private static String lessThan1000ToString(int n) {
        int hundredDigit = n / 100;
        String s = "";
        switch (hundredDigit) {
           case 1: s = "one hundred"; break;
           case 2: s = "two hundred"; break;
           case 3: s = "three hundred"; break;
           case 4: s = "four hundred"; break;
           case 5: s = "five hundred"; break;
           case 6: s = "six hundred"; break;
           case 7: s = "seven hundred"; break;
           case 8: s = "eight hundred"; break;
           case 9: s = "nine hundred"; break;
           default: s = "ERROR HUNDRED"; break;
       }
       if (n % 100 != 0) //one hundred and something
       {
           s += " and ";
           s += lessThan100ToString(n % 100);
       }
       return s.trim();
    }
    
    private static String lessThan100ToString(int n) {
        int onesDigit = n % 10;
        int tensDigit = n / 10;
        
       String s = "";
       switch (tensDigit) {
           case 0: return singleDigitToString(onesDigit);
           case 1: return tensToString(n);
           case 2: s = "twenty"; break;
           case 3: s = "thirty"; break;
           case 4: s = "forty"; break;
           case 5: s = "fifty"; break;
           case 6: s = "sixty"; break;
           case 7: s = "seventy"; break;
           case 8: s = "eighty"; break;
           case 9: s = "ninety"; break;
           default: s = "ERROR"; break;
       }
       switch (onesDigit) {
            case 0: s += ""; break;
            case 1: s += "-one"; break;
            case 2: s += "-two"; break;
            case 3: s += "-three"; break;
            case 4: s += "-four"; break;
            case 5: s += "-five"; break;
            case 6: s += "-six"; break;
            case 7: s += "-seven"; break;
            case 8: s += "-eight"; break;
            case 9: s += "-nine"; break;
            default:s += "-errrrrrrror"; break;
        }
       return s.trim();
    }
    
    private static String tensToString(int n) {
        String s;
        switch (n) {
            case 10: s = "ten"; break;
            case 11: s = "eleven"; break;
            case 12: s = "twelve"; break;
            case 13: s = "thirteen"; break;
            case 14: s = "fourteen"; break;
            case 15: s = "fifteen"; break;
            case 16: s = "sixteen"; break;
            case 17: s = "seventeen"; break;
            case 18: s = "eighteen"; break;
            case 19: s = "nineteen"; break;
            default:s = null; break;
        }
        return s;
    }
    
    private static String singleDigitToString(int n) {
        String s;
        switch (n) {
            case 0: s = "zero"; break;
            case 1: s = "one"; break;
            case 2: s = "two"; break;
            case 3: s = "three"; break;
            case 4: s = "four"; break;
            case 5: s = "five"; break;
            case 6: s = "six"; break;
            case 7: s = "seven"; break;
            case 8: s = "eight"; break;
            case 9: s = "nine"; break;
            default:s = null; break;
        }
        return s;
    }
    
    public static void main(String[] args) {
        List<Integer> primes = new ArrayList<>();
        for (int n = 2; n < 1000000; n++)
            if (isPrime(n))
                primes.add(n);
        
        for (int n : primes)
            System.out.println(n );
    }
}
