//this class is a copy of Try3.java, but stripped down to make it look nice
package problem601;

import java.util.ArrayList;
import java.util.List;

public class TidyingUp {
    
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        ComplexPattern[] patterns = new ComplexPattern[32];     //holds patterns
        
        List<Pair> nums = new ArrayList<>();                
        for (long n = 2; n <= 100+2; n++)
            nums.add(new Pair(n, streak6(n)));
        
        long minStreak = nums.get(0).streakVal;
        while (minStreak < patterns.length) {
            
            minStreak = nums.get(0).streakVal;                  //find min streak
            for (Pair p : nums)
                if (p.streakVal < minStreak)
                    minStreak = p.streakVal;
            
            List<Long> allMinStreakNums = new ArrayList<>();     //get list of all pairs of min streak
            for (Pair p : nums)
                if (p.streakVal == minStreak)
                    allMinStreakNums.add(p.num);
            
            ComplexPattern patternToSave = ComplexPattern.matchPattern(allMinStreakNums.toArray(new Long[allMinStreakNums.size()]));    //calculate pattern
            
            if (minStreak < patterns.length)                    //save pattern
                patterns[(int)minStreak] = patternToSave;
            else
                break;
            
            List<Pair> remainingPairs = new ArrayList<>();      //remove min pairs from list
            for (Pair p : nums)
                if (p.streakVal != minStreak)
                    remainingPairs.add(p);
            nums = remainingPairs;
            
            List<Long> lastNumbers = new ArrayList<>();         //grow list up
            final int NUMBERS_TO_ADD_TO_LIST = 30;              //get last n numbers from list, get its complex pattern, add to end of list
            int start = nums.size() - 1 - NUMBERS_TO_ADD_TO_LIST;
            if (start < 0)
                start = 0;
            for (int i = start; i < nums.size(); i++) 
                lastNumbers.add(nums.get(i).num);
            
            ComplexPattern endingPattern = ComplexPattern.matchPattern(lastNumbers);
            endingPattern.start = nums.get(nums.size() - 1).num;            //set start of pattern to be ending number
            List<Long> numsToAddToEnd = endingPattern.getPatternList(100 - nums.size());
            
            for (int i = 1; i < numsToAddToEnd.size(); i++) {       //calculate streak of these new numbers and add to end of list
                Pair toAdd = new Pair(numsToAddToEnd.get(i), streak6(numsToAddToEnd.get(i)));
                nums.add( toAdd );
            }

        }
        long totalP = 0;    //find out values of P(s, n)
        for (int i = 1; i < patterns.length; i++)
            if (patterns[i] != null)
                totalP += patterns[i].getPatternUnder( (long)Math.pow(4, i) ).size();
        System.out.println("total P(i, 4^i) [1,31] = " + totalP);
        System.out.println((System.nanoTime() - startTime)/ 1000000000.0);      //total time
        
        for (int i = 1; i < patterns.length; i++) {
                System.out.println("streak: " + i + " :\t" + patterns[i]);
        }
    }
    
    public static long streak6(long n) {
        if (n < 2)
            return -1;
        long nm1 = n-1;
        
        if (n % 2 == 0)
            return 1;
        else if ( nm1 % 6 != 0)
            return 2;
        else if ( nm1 % 60 != 0) {
            if ( nm1 % 12 != 0)
                return 3;
            return 4;
        }
        else if ( nm1 % 420 != 0)
            return 6;
        else if ( nm1 % 2520 != 0) {
            if ( nm1 % 840 != 0)
                return 7;
            return 8;
        }
        else if ( nm1 % 27720 != 0)
            return 10;
        
        long divBy = 1;     //actual calculation
        while (n % divBy == 0) {
            divBy++;
            n++;
        }
        return divBy - 1;
    }
    
    private static class Pair {
        long num;
        long streakVal;

        public Pair(long num1, long num2) {
            this.num = num1;
            this.streakVal = num2;
        }
    }
}
