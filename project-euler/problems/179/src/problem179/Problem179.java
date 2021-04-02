package problem179;

import mathtools.MF;


public class Problem179 {
    
    /*
    Find the number of integers 1 < n < 107, for which n and n + 1 have the same number of positive divisors. 
    For example, 14 has the positive divisors 1, 2, 7, 14 while 15 has 1, 3, 5, 15.
    */
    public static void main(String[] args) {
        int[] numDivs = new int[10000001]; //2 to 10000000
        for (int i = 2; i < numDivs.length; i++)
            numDivs[i] = numDivisors(i);
        
        int count = 0;
        for (int i = 2; i < 10000000; i++)
            if (numDivs[i] == numDivs[i+1])
                count++;
        System.out.println(count);
    }
    
    public static int numDivisors(int num) {
        int numDivisors = 2;    //one and itself
        for (int i = 2; i < (int)Math.ceil(Math.sqrt(num)); i++) {
            if (num % i == 0)
                numDivisors += 2;
        }
        if (MF.isPerfectSquare(num))
            numDivisors++;
        return numDivisors;
    }
}
//986262