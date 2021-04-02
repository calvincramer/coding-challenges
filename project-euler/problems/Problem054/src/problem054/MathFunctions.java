package problem054;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class MathFunctions {
    
    private static long startTime = System.currentTimeMillis();
    
    /**
     * Calculates n!
     * @param n
     * @return
     */
    public static BigInteger factorial(int n) {
        if (n < 0) {
            return BigInteger.ZERO;
        }
        if (n == 0) {
            return BigInteger.ONE;
        }

        BigInteger num = new BigInteger(n + "");
        BigInteger res = BigInteger.ONE;

        for (BigInteger i = BigInteger.ONE; i.compareTo(num) <= 0; i = i.add(BigInteger.ONE)) {
            res = res.multiply(i);
        }

        return res;

    }

    /**
     * Calculates the difference between the start time and current time
     * @return
     */
    public static long getElapledTime() {
        return System.currentTimeMillis() - MathFunctions.startTime;
    }

    /**
     * Calculates the factors of a number
     * @param n
     * @return
     */
    public static List<Integer> getFactorsOf(Integer n) {
        List<Integer> factors = new ArrayList<>();

        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        factors.add(n); //every number is a factor of itself

        return factors;
    }
    
    /**
     * Calculates the factors of a number. Option for including one and the number itself. As ever number is evenly divisible by one and itself.
     * @param n
     * @return
     */
    public static List<Integer> getFactorsOf(Integer n, boolean includeOne, boolean includeSelf) {
        List<Integer> factors = new ArrayList<>();

        int start = includeOne ? 1 : 2;

        for (int i = start; i <= n / 2; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        if (includeSelf) {
            factors.add(n);
        }

        return factors;
    }
    
    /**
     * Gets the first digit of the number
     * May not handle negative numbers.
     * @param number
     * @return 
     */
    public static int getFirstDigit(int number) {
        String s = String.valueOf(Math.abs(number));
        char c = s.toCharArray()[0];
        return Integer.valueOf(c + "");
    }
    
    /**
     * Calculates the prime factors of a number
     * @param n
     * @return 
     */
    public static List<Integer> getPrimeFactorsOf(Integer n) {
        List<Integer> factors = new ArrayList<>();

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0 && isPrime(i)) {
                factors.add(i);
            }
        }

        return factors;
    }
    
    /**
     * Calculates whether the number is circularly prime.
     * @param number
     * @return 
     */
    public static boolean isCircularPrime(int number) {
        if (!isPrime(number)) {
            return false;
        }

        if (number < 10) {
            return isPrime(number);
        }

        List<Integer> digits = new ArrayList<>();
        int n = number;
        while (n > 0) {
            digits.add(0, n % 10);
            n /= 10;
        }

        //cycle the number
        for (int i = 1; i <= digits.size(); i++) {
            int dig = digits.remove(0);
            digits.add(dig);    //to end

            int cycledNum = 0;
            int scale = 1;
            for (int k = digits.size() - 1; k >= 0; k--) {
                cycledNum += scale * digits.get(k);
                scale *= 10;
            }

            if (!isPrime(cycledNum)) {
                return false;
            }

        }

        return true;
    }
    
    /**
     * Determines whether a number is a Lychrel number.
     * A number is a Lychrel number when it eventually forms a palindrome by adding the reverse of itself.
     * The reverse-add process is repeated fifty times.
     * @param n
     * @return 
     */
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
    
    /**
     * Determines whether the number is palindromic. 
     * @param n
     * @return 
     */
    public static boolean isPalindrome(int n) {
        return isPalindrome(n + "");
    }
    
    /**
     * Determines whether the number is palindromic. 
     * @param n
     * @return 
     */
    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Old method of determining if a number is palindromic.
     * Approximately 10x slower than MathFunctions.isPalindrome()
     * @param n
     * @return 
     */
    public static boolean isPalindromeNumber(int n) {
        int maxDigits = (int) Math.log10(n) + 1;
        int r = 1;
        int l = maxDigits;

        while (r < l) {
            int digitRight = (int) ((n / Math.pow(10, r - 1)) % 10);
            int digitLeft = (int) ((n / Math.pow(10, l - 1)) % 10);

            if (digitRight != digitLeft) {
                return false;
            }
            r++;
            l--;
        }
        return true;
    }
    
    /**
     * Determines if a number is pandigital (all digits from lowNum to highNum occur exactly once)
     * @param str
     * @param lowNum
     * @param highNum
     * @return 
     */
    public static boolean isPandigital(String str, int lowNum, int highNum) {
        if (str.length() != highNum - lowNum + 1) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) //no zeroes allowed
        {
            if (Character.getNumericValue(str.charAt(i)) < lowNum
                    || Character.getNumericValue(str.charAt(i)) > highNum) {
                return false;
            }
        }

        boolean[] numOccur = new boolean[10];   //index of 1 = number 1 occurred
        for (int i = 0; i < str.length(); i++) {
            int num = Character.getNumericValue(str.charAt(i));
            if (numOccur[num] == true) //if already found the number
            {
                return false;
            } else {
                numOccur[num] = true;
            }
        }

        return true;
    }
    
    /**
     * Determines if a number is prime
     * O(n/2) worst case.
     * MathFunctions.isPrimeFast() is faster.
     * @param num
     * @return 
     */
    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Determines if a number is prime
     * O(sqrt(n)) worse case.
     * @param num
     * @return 
     */
    public static boolean isPrimeFast(long num) {
        if (num < 2) {
            return false;
        }
        for (int n = 2; n <= (int) Math.sqrt(num); n++) {
            if (num % n == 0) {
                return false;
            }
        }

        return true;
    }
    
    

    public static void startTimer() {
        MathFunctions.startTime = System.currentTimeMillis();
    }
    
    

    

    /**
     * Calculates nCr = n!/(r!(n−r)!)
     *
     * @param n
     * @param r
     * @return
     */
    public static BigInteger nCr(int n, int r) {

        if (r > n) {
            return BigInteger.ZERO;
        }

        BigInteger nfact = factorial(n);
        BigInteger rfact = factorial(r);
        BigInteger nMinusrfact = factorial(n - r);

        BigInteger res = rfact.multiply(nMinusrfact);
        res = nfact.divide(res);

        return res;
    }

    /**
     * Converts an integer to its string representation, of how one would say the number out loud. Currently supports numbers 0 to 1000
     *
     * @param n The number to convert
     * @return The string representation
     */
    public static String numberToString(int n) {
        if (n < 10) {
            return singleDigitToString(n);
        }
        if (n < 20) {
            return tensToString(n);
        }
        if (n < 100) {
            return lessThan100ToString(n);
        }
        if (n < 1000) {
            return lessThan1000ToString(n);
        }
        if (n == 1000) {
            return "one thousand";
        }

        return "NOT SUPPORTED YET";
    }
    
    /**
     * Reverses the digits of a number. Just like a horizontal reflection.
     * @param n
     * @return 
     */
    public static int reverseNumber(int n) {
        String str = n+"";
        char[] reverse = new char[str.length()];
        for (int i = 0; i < str.length(); i++) 
            reverse[str.length()-1-i] = str.charAt(i);
        String revString = new String(reverse);
        return Integer.parseInt(revString);
    }
    
    /**
     * Reverses the digits of a number. Just like a horizontal reflection.
     * @param n
     * @return 
     */
    public static BigInteger reverseNumber(BigInteger n) {
        String str = n.toString()+"";
        char[] reverse = new char[str.length()];
        for (int i = 0; i < str.length(); i++) 
            reverse[str.length()-1-i] = str.charAt(i);
        String revString = new String(reverse);
        return new BigInteger(revString);
    }

    private static String lessThan1000ToString(int n) {
        int hundredDigit = n / 100;
        String s = "";
        switch (hundredDigit) {
            case 1:
                s = "one hundred";
                break;
            case 2:
                s = "two hundred";
                break;
            case 3:
                s = "three hundred";
                break;
            case 4:
                s = "four hundred";
                break;
            case 5:
                s = "five hundred";
                break;
            case 6:
                s = "six hundred";
                break;
            case 7:
                s = "seven hundred";
                break;
            case 8:
                s = "eight hundred";
                break;
            case 9:
                s = "nine hundred";
                break;
            default:
                s = "ERROR HUNDRED";
                break;
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
            case 0:
                return singleDigitToString(onesDigit);
            case 1:
                return tensToString(n);
            case 2:
                s = "twenty";
                break;
            case 3:
                s = "thirty";
                break;
            case 4:
                s = "forty";
                break;
            case 5:
                s = "fifty";
                break;
            case 6:
                s = "sixty";
                break;
            case 7:
                s = "seventy";
                break;
            case 8:
                s = "eighty";
                break;
            case 9:
                s = "ninety";
                break;
            default:
                s = "ERROR";
                break;
        }
        switch (onesDigit) {
            case 0:
                s += "";
                break;
            case 1:
                s += "-one";
                break;
            case 2:
                s += "-two";
                break;
            case 3:
                s += "-three";
                break;
            case 4:
                s += "-four";
                break;
            case 5:
                s += "-five";
                break;
            case 6:
                s += "-six";
                break;
            case 7:
                s += "-seven";
                break;
            case 8:
                s += "-eight";
                break;
            case 9:
                s += "-nine";
                break;
            default:
                s += "-errrrrrrror";
                break;
        }
        return s.trim();
    }

    private static String tensToString(int n) {
        String s;
        switch (n) {
            case 10:
                s = "ten";
                break;
            case 11:
                s = "eleven";
                break;
            case 12:
                s = "twelve";
                break;
            case 13:
                s = "thirteen";
                break;
            case 14:
                s = "fourteen";
                break;
            case 15:
                s = "fifteen";
                break;
            case 16:
                s = "sixteen";
                break;
            case 17:
                s = "seventeen";
                break;
            case 18:
                s = "eighteen";
                break;
            case 19:
                s = "nineteen";
                break;
            default:
                s = null;
                break;
        }
        return s;
    }

    private static String singleDigitToString(int n) {
        String s;
        switch (n) {
            case 0:
                s = "zero";
                break;
            case 1:
                s = "one";
                break;
            case 2:
                s = "two";
                break;
            case 3:
                s = "three";
                break;
            case 4:
                s = "four";
                break;
            case 5:
                s = "five";
                break;
            case 6:
                s = "six";
                break;
            case 7:
                s = "seven";
                break;
            case 8:
                s = "eight";
                break;
            case 9:
                s = "nine";
                break;
            default:
                s = null;
                break;
        }
        return s;
    }
    
    
    
    
    
    public static void main(String[] args) {
        //System.out.println("No code in MathFunctions.java main");
        
        
        /*
        //Simple two method test
        final int MAX = 1000000;
        MathFunctions.startTimer();
        for (int i = 0; i <= MAX; i++) {
            MathFunctions.isPrimeFast(i);
        }
        long time = MathFunctions.getElapledTime();
        System.out.println("Fast: " + time);
        
        MathFunctions.startTimer();
        for (int i = 0; i <= MAX; i++) {
            MathFunctions.isPrime(i);
        }
        time = MathFunctions.getElapledTime();
        System.out.println("Slow: " + time);
                */
        
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (i % 100000 == 0)
                System.out.println(i + " / " + Integer.MAX_VALUE);
            
            boolean ip = isPrime(i);
            boolean ipf = isPrimeFast(i);
            if (ip != ipf) {
                System.out.println("ERROR: " + i + "\tisPrime: " + ip + "\tisPrimeFast: " + ipf);
            }
        }
                
    }
}
