package problem078;

import java.math.BigInteger;
import mathtools.MF;

public class Try4 {
    
    public static final int SIZE = 25000;
    public static BigInteger[][] ptable = new BigInteger[SIZE][SIZE];           //sum by largest number
    public static boolean[][] filledIn = new boolean[SIZE][SIZE];
    
    public static BigInteger ONE = BigInteger.ONE;
    public static BigInteger ZERO = BigInteger.ZERO;
    public static BigInteger MAXI = new BigInteger(""+SIZE);
    
    
    public static void main(String[] args) {
        
        ptable[0][0] = BigInteger.ZERO;
        filledIn[0][0] = true;
        
        BigInteger million = new BigInteger(""+100000);
        
        for (BigInteger i = ONE; i.compareTo(million) < 0; i = i.add(ONE)) {
            MF.startTimer();
            BigInteger res = partition(i,i);
            System.out.println(i + ":\t" + partition(i, i) + "\t" + MF.getElapsedSeconds());
            if (res.mod(million).equals(BigInteger.ZERO)) {
                System.out.println("winner winner");
                System.out.println("n= " + i);
                System.out.println("p(n)= " + res.toString());
                break;
            }
        }
        
    }
    
    
    
    /**
     * 100% credit: http://www.programminglogic.com/integer-partition-algorithm/
     * Thank you Daniel Scocco!
     * I made it dynamically programmed :)
     * @param sum
     * @param largestNumber
     * @return 
     */
    public static BigInteger partition(BigInteger sum, BigInteger largestNumber){
        if (largestNumber.equals(ZERO))
            return ZERO;
        if (sum.equals(ZERO))
            return ONE;
        if (sum.compareTo(ZERO) < 0)
            return ZERO;

        BigInteger ans = BigInteger.ZERO;
        
        //partition(sum,largestNumber-1) 
        if (sum.compareTo(MAXI) < 0
                && largestNumber.subtract(ONE).compareTo(MAXI) < 0
                && filledIn[sum.intValue()][largestNumber.intValue() - 1])
            ans = ans.add(ptable[sum.intValue()][largestNumber.intValue() - 1]);
        else
            ans = ans.add(partition(sum, largestNumber.subtract(ONE)));
        
        //+ partition(sum-largestNumber,largestNumber);
        if (sum.subtract(largestNumber).compareTo(MAXI) < 0
                && sum.subtract(largestNumber).compareTo(ZERO) >= 0
                && largestNumber.compareTo(MAXI) < 0
                && filledIn[sum.subtract(largestNumber).intValue()][largestNumber.intValue()])
            ans = ans.add(ptable[sum.subtract(largestNumber).intValue()][largestNumber.intValue()]);
        else
            ans = ans.add(partition(sum.subtract(largestNumber), largestNumber));
        
        
        if (sum.compareTo(MAXI) < 0 && largestNumber.compareTo(MAXI) < 0) {
            filledIn[sum.intValue()][largestNumber.intValue()] = true;
            ptable[sum.intValue()][largestNumber.intValue()] = ans;
        }
        
        return ans;
    }

}
//I know the answer is above 7455

//74:	7089500	4.7069E-5                               DIVISIBLE BY 100
//449:	126891542690981418000	2.26864E-4              DIVISIBLE BY 1000
//599:	435350207840317348270000	4.20985E-4      DIVISIBLE BY 10000
//11224: ##############...##5572300000                  DIVISIBLE BY 100000     from try 5