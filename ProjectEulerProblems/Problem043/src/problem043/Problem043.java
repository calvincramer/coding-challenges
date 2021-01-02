package problem043;

import java.math.BigInteger;

public class Problem043 {

    /*
    The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

    Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

    d2d3d4=406 is divisible by 2
    d3d4d5=063 is divisible by 3
    d4d5d6=635 is divisible by 5
    d5d6d7=357 is divisible by 7
    d6d7d8=572 is divisible by 11
    d7d8d9=728 is divisible by 13
    d8d9d10=289 is divisible by 17
    Find the sum of all 0 to 9 pandigital numbers with this property.
    */

    public static void main(String[] args) {
        
        BigInteger sum = BigInteger.ZERO;
        int[] primes = {2,3,5,7,11,13,17};
        
        for (long i = 1000000000; i < 9999999999L; i++) {
            if (isPandigital("" + i, 0, 9)) {
                
                String s = "" + i;
                int[] digits = new int[10];
                for (int d = 0; d < digits.length; d ++)
                    digits[d] = Character.getNumericValue(s.charAt(d));
                
                int primeIndex = 0;
                boolean isDivisible = true;
                for (int d = 1; d <= 7; d++) {
                    
                    int subNumber = Integer.parseInt(s.substring(d, d+3));
                    if (subNumber % primes[primeIndex] != 0) {
                        isDivisible = false;
                        break;
                    }
                    primeIndex++;
                }
                
                if (isDivisible) {
                    System.out.println(i + "\tQualifies");
                    sum = sum.add(new BigInteger(i + ""));
                }
                
            }
        }
        
        System.out.println("Total: " + sum);
    }

    public static boolean isPandigital(String str, int lowNum, int highNum) {
        if (str.length() != highNum - lowNum + 1) 
            return false;
        for (int i = 0; i < str.length(); i++)  //no zeroes allowed
            if (Character.getNumericValue(str.charAt(i)) < lowNum
                    || Character.getNumericValue(str.charAt(i)) > highNum)
                return false;
        
        boolean[] numOccur = new boolean[10];   //index of 1 = number 1 occurred
        for (int i = 0; i < str.length(); i++) {
            int num = Character.getNumericValue(str.charAt(i));
            if (numOccur[num] == true)  //if already found the number
                return false;
            else
                numOccur[num] = true;
        }
        
        return true;
    }
    
}
