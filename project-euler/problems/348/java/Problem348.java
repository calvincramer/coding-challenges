package problem348;

import mathtools.MF;

public class Problem348 {
    
    /*
    Many numbers can be expressed as the sum of a square and a cube. Some of them in more than one way.

    Consider the palindromic numbers that can be expressed as the sum of a square and a cube, both greater than 1, in exactly 4 different ways.
    For example, 5229225 is a palindromic number and it can be expressed in exactly 4 different ways:

    2285^2 + 20^3
    2223^2 + 66^3
    1810^2 + 125^3
    1197^2 + 156^3

    Find the sum of the five smallest such palindromic numbers.
    */
    public static void main(String[] args) {
        
        int palLength = 10000000;
        long[] palindromeCount = new long[palLength];
        
        int size = 200000;
        long[] squares = new long[size];
        long[] cubes = new long[size];
        for (int i = 0; i < size; i++) {
            squares[i] = i*i;
            cubes[i] = squares[i]*i;
        }
        
        System.out.println("made squares and cubes");
        
        for (int s = 2; s < size; s++) {
            System.out.println(s * 1.0 / size);
            for (int c = 2; c < size; c++) {
                long res = squares[s] + cubes[c];
                if (MF.isPalindrome(res)) {
                    //System.out.println(s + "^2 + " + c + "^3 = \t" + (squares[s] + cubes[c]) );
                    if (res < palLength)
                        palindromeCount[(int)res]++;
                }
            }
        }
        
        System.out.println("\n\n");
        
        for (int i = 0; i < palLength; i++) {
            if (palindromeCount[i] == 4)
                System.out.println(i);
        }
    }
}
/*
4
484
94249
698896
5229225
6129216
*/