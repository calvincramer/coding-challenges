package problem004;

import mathtools.MF;

public class Test {
    //problem 004
/*
A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 x 99.

Find the largest palindrome made from the product of two 3-digit numbers.
*/

    public static void main(String[] args) {
        
        MF.startTimer();
        long largest = 0;
        long bigA = 0;
        long bigB = 0;
        for (long a = 0; a < 10000; a++) {
            for (long b = 0; b < 10000; b++) {
                long temp = a*b;
                if (isPalindrome(temp) && temp > largest) {
                    largest = temp;
                    bigA = a;
                    bigB = b;
                }
            }
        }
        System.out.println("largest pal: " + largest);
        System.out.println("a= " + bigA);
        System.out.println("b= " + bigB);

        System.out.println("time: " + MF.getElapsedSeconds());
    }

    public static boolean isPalindrome(long num)
    {
        //cout << "(number): " << num;

        int length = numDigits(num );
        int[] digits = new int[length];
        
        
        for (int i = length - 1; i >= 0; i--)
        {
            digits[i] = (int) num % 10;
            num /= 10;
        }

        //cout << "\t(digit array): ";
        //for (int i = 0; i < length; i++)
        //    cout << digits[i];
        //cout << endl;

        for (int i = 0; i < length / 2; i++)
        {
            if (digits[i] != digits[length - 1 - i])
                return false;
        }

        return true;
    }

    public static int numDigits(long num)
    {
        int n = 0;
        do {
            n++;
            num /= 10;
        } while (num != 0);
        return n;
    }

}
