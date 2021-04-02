package problem203;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mathtools.MF;

public class Problem203 {
    
    /*
    The binomial coefficients nCk can be arranged in triangular form, Pascal's triangle, like this:

    It can be seen that the first eight rows of Pascal's triangle contain twelve distinct numbers: 1, 2, 3, 4, 5, 6, 7, 10, 15, 20, 21 and 35.

    A positive integer n is called squarefree if no square of a prime divides n. 
    Of the twelve distinct numbers in the first eight rows of Pascal's triangle, all except 4 and 20 are squarefree. The sum of the distinct squarefree numbers in the first eight rows is 105.

    Find the sum of the distinct squarefree numbers in the first 51 rows of Pascal's triangle.
    */
    
    //largest number is 126410606437752, sqrt = 11,243,247.##
    public static List<Long> primes = MF.getPrimesUnder(12000000);
    public static List<BigInteger> primesSqr = new ArrayList<>();
    
    public static void main(String[] args) {
        
        for (long p : primes)
            primesSqr.add( new BigInteger((p*p)+"") );
        
        List<BigInteger> sqrFreeNums = new ArrayList<>();
        
        System.out.println("done building");
        
        for (int i = 0; i < 51; i++) {
            for (int j = 0; j <= i; j++) {
                BigInteger res = MF.nCr(i, j);
                //System.out.print(res + "\t");
                if (isSquareFree(res) && !sqrFreeNums.contains(res))
                    sqrFreeNums.add(res);
            }
        }
        Collections.sort(sqrFreeNums);
        MF.printList(sqrFreeNums);
        System.out.println(MF.sum(sqrFreeNums));
        
        
    }
    
    public static boolean isSquareFree(BigInteger num) {
        if (num.compareTo(BigInteger.ONE) < 0)
            return false;
        
        BigInteger sqrt = MF.bigIntSqRootFloor(num).add(BigInteger.TEN);
        
        for (BigInteger psqr : primesSqr) {
            if (psqr.compareTo(sqrt) >= 0)
                break;
            if (num.mod(psqr) == BigInteger.ZERO)
                return false;
            
        }
        
        return true;
    }
}
//34029210557462