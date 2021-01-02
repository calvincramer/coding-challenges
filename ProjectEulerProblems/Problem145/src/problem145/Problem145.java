package problem145;

public class Problem145 {
    
    /*
    Some positive integers n have the property that the sum [ n + reverse(n) ] consists entirely of odd (decimal) digits. For instance, 36 + 63 = 99 and 409 + 904 = 1313. We will call such numbers reversible; so 36, 63, 409, and 904 are reversible. Leading zeroes are not allowed in either n or reverse(n).

    There are 120 reversible numbers below one-thousand.

    How many reversible numbers are there below one-billion (10^9)?
    */
    public static void main(String[] args) {
        long numRev = 0;
        for (long n = 1; n < 1000000000; n++) {
            if (isReversible(n)) {
                //System.out.println(n);
                numRev++;
            }
        }
        System.out.println(numRev);
    }
    
    
    public static boolean isReversible(long num) {
        long rev = reverseNumber(num);
        if (rev == -1)
            return false;
        long n = num + rev;
        return isOnlyOdd(n);
    }
    
    public static long reverseNumber(long num) {
        StringBuilder n = new StringBuilder(""+num);
        String s = n.reverse().toString();
        if (s.charAt(0)=='0') {
            //System.out.println("leading zero: " + s);
            return -1;
        }
        return Long.parseLong(s);
        
    }
    
    public static boolean isOnlyOdd(long num) {
        while (num >= 10) {
            int dig = (int) (num % 10);
            if (dig % 2 == 0)
                return false;
            num /= 10;
        } 
        if (num % 2 == 0)
            return false;
        return true;
    }
}
//answer 608720