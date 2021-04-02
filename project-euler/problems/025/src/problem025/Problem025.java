package problem025;

import java.math.BigInteger;

public class Problem025 {

    /*
    The Fibonacci sequence is defined by the recurrence relation:

    Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
    Hence the first 12 terms will be:

    F1 = 1
    F2 = 1
    F3 = 2
    F4 = 3
    F5 = 5
    F6 = 8
    F7 = 13
    F8 = 21
    F9 = 34
    F10 = 55
    F11 = 89
    F12 = 144
    The 12th term, F12, is the first term to contain three digits.

    What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
    */
    public static void main(String[] args) {
        
        long index = 2;         //starting at the second fib number (the second 1)
        BigInteger fib1 = new BigInteger("1");
        BigInteger fib2 = new BigInteger("1");
        
        while (fib2.toString().length() < 1000) {
            index++;
            BigInteger temp = fib1.add(fib2);
            fib1 = fib2;
            fib2 = temp;
        }
        System.out.println("1000 digit fib number index: " + index);
        System.out.println(fib2.toString());
        
        

    }

}
