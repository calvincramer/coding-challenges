package problem078;

import java.util.Random;
import mathtools.MF;

public class Try5 {
    
    public static final int SIZE = 60000;
    public static int[][] ptable = new int[SIZE][SIZE];           //sum by largest number
    public static boolean[][] filledIn = new boolean[SIZE][SIZE];
    
    public static void main(String[] args) {
        
        Random rng = new Random(System.currentTimeMillis());
        
        for (int i = 1; i < 100000; i++) {
            
            if (i == 4274) {
                System.out.println("boop");
            }
            MF.startTimer();
            long res = partition(i,i);
            
            if (rng.nextDouble() < 0.01)
                System.out.println(i + ":\t" + partition(i, i) + "\t" + MF.getElapsedSeconds());
            
            if (res % 1000000 == 0) {
                System.out.println("winner winner");
                System.out.println("n= " + i);
                System.out.println("p(n)= " + res);
                break;
            }
            if (i >= SIZE)
                System.out.println("exceeded size, will take forever.");
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
    public static long partition(long sum, long largestNumber){
        if (largestNumber==0)
            return 0;
        if (sum==0)
            return 1;
        if (sum<0)
            return 0;
        
        long ans = 0;
        if (sum < SIZE && largestNumber - 1 < SIZE && filledIn[(int)sum][(int)largestNumber - 1])
            ans += ptable[(int)sum][(int)largestNumber - 1];
        else
            ans += partition(sum, largestNumber - 1);
        
        if (sum - largestNumber < SIZE && sum - largestNumber >= 0 && largestNumber < SIZE && filledIn[(int)(sum - largestNumber)][(int)largestNumber])
            ans += ptable[(int)(sum - largestNumber)][(int)largestNumber];
        else
            ans += partition(sum - largestNumber, largestNumber);
        
        if (sum < SIZE && largestNumber < SIZE) {
            filledIn[(int)sum][(int)largestNumber] = true;
            ptable[(int)sum][(int)largestNumber] = (int) (ans % 1000000);
        }

        return ans;
    }

}
//answer is above 50000
//