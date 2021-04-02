package problem601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mathtools.MF;

public class Try3 {

    /*
    Idea for this attempt:
    
    Start with list of natural numbers up to some maximum number (or max elements)
        Select list with the least streak value
            Calculate pattern of these numbers
            Save pattern
        Remove these numbers from the main list
        Grow list down to >= 2
            How to grow list down s.t. elements added to list are not from any previous patterns???
            Need to grow down?
        Grow list to max elements
            How to grow list up s.t. elements added to list are not from any previous patterns???
            
        Stop when smallest streak == 32
    */
    
    static final int MAX_ELEMENTS = 100;
    static final int MAX_PATTERNS = 32;     //anything above 32 will run into 64 bit overflow probably
    
    public static void main(String[] args) {
        
        long startTime = System.nanoTime();
        
        double timeCalculatingPattern = 0.0;
        double timeRemovingOldElements = 0.0;
        double timeGrowingListDown = 0.0;
        double timeGrowingListUp = 0.0;
        
        long totalAddedDown = 0;
        long totalAddedUp = 0;
        
        
        ComplexPattern[] patterns = new ComplexPattern[MAX_PATTERNS];
        
        List<Pair> nums = new ArrayList<>();
        for (long n = 2; n <= MAX_ELEMENTS+2; n++)
            nums.add(new Pair(n, streak6(n)));
        
        long minStreak = nums.get(0).streakVal;
        while (minStreak < patterns.length) {
            //find min streak
            minStreak = nums.get(0).streakVal;
            for (Pair p : nums)
                if (p.streakVal < minStreak)
                    minStreak = p.streakVal;
            
            //get list of all pairs of min streak
            List<Long> allMinStreakNums = new ArrayList<>();
            for (Pair p : nums)
                if (p.streakVal == minStreak)
                    allMinStreakNums.add(p.num);
            
            //calculate pattern
            MF.startTimer();
            ComplexPattern patternToSave = ComplexPattern.matchPattern(allMinStreakNums.toArray(new Long[allMinStreakNums.size()]));
            timeCalculatingPattern += MF.getElapsedSeconds();
            
            
            //save pattern
            if (minStreak < patterns.length)
                patterns[(int)minStreak] = patternToSave;
            else {
                System.out.println("cannot save pattern\t" + minStreak);
                break;  //if minStreak is greater than pattern array, we are done
            }
            
            
            //remove min pairs from list
            MF.startTimer();
            List<Pair> remainingPairs = new ArrayList<>();
            for (Pair p : nums)
                if (p.streakVal != minStreak)
                    remainingPairs.add(p);
            nums = remainingPairs;
            remainingPairs = null;
            timeRemovingOldElements += MF.getElapsedSeconds();
            
            //grow list down
            //probably unecessary, as a total of 0 numbers are added!
            /*
            MF.startTimer();
            int numAddedDown = 0;
            long minNum = nums.get(0).num;
            for (long n = minNum - 1; n >= 2; n--) {    
                //try to add n
                boolean inAnyPatterns = false;
                for (ComplexPattern pattern : patterns) {
                    if (pattern == null)
                        continue;
                    if (pattern.contains(n)) {
                        inAnyPatterns = true;
                        break;
                    }
                }
                
                if (!inAnyPatterns) {
                    nums.add(0, new Pair(n, streak6(n)));
                    numAddedDown++;
                }
            }
            totalAddedDown += numAddedDown;
            timeGrowingListDown += MF.getElapsedSeconds();
            */
            
            //grow list up
            //please make me faster Calvin OwO
            MF.startTimer();
            /*
            int numAddedUp = 0;
            long maxNum = nums.get(nums.size() - 1).num;
            long numToAdd = maxNum + 1;
            if (numToAdd % 2 == 0)
                numToAdd++;
            while (nums.size() < MAX_ELEMENTS) {
                //try to add numToAdd
                boolean inAnyPatterns = false;
                for (ComplexPattern pattern : patterns) {
                    if (pattern == null)
                        continue;
                    if (pattern.contains(numToAdd)) {
                        inAnyPatterns = true;
                        break;
                    }
                }
                
                if (!inAnyPatterns) {
                    nums.add(new Pair(numToAdd, streak6(numToAdd)));
                    numAddedUp++;
                }
                
                numToAdd += 2;
            }
            totalAddedUp += numAddedUp;
            */
            
            //get last n numbers from list, get its complex pattern, add to end of list
            List<Long> lastNumbers = new ArrayList<>();
            final int NUMBERS_TO_ADD = 30;
            int start = nums.size() - 1 - NUMBERS_TO_ADD;
            if (start < 0)
                start = 0;
            for (int i = start; i < nums.size(); i++) 
                lastNumbers.add(nums.get(i).num);
            
            ComplexPattern endingPattern = ComplexPattern.matchPattern(lastNumbers);
            endingPattern.start = nums.get(nums.size() - 1).num;    //set start of pattern to be ending number
            List<Long> numsToAddToEnd = endingPattern.getPatternList(MAX_ELEMENTS - nums.size());
            totalAddedUp += MAX_ELEMENTS - nums.size();
            //calculate streak of these new numbers and add to end of list
            for (int i = 1; i < numsToAddToEnd.size(); i++) {    //skip over starting number, because starting number is last element in the list already
                Pair toAdd = new Pair(numsToAddToEnd.get(i), streak6(numsToAddToEnd.get(i)));
                nums.add( toAdd );
                
                //test if toAdd's streak value is greater than the min streak of this iteration
                //if it doesn't pass, that means we put in the wrong numbers
                if (toAdd.streakVal <= minStreak) {
                    System.out.println("bad new number");
                }
                
            }
            
            timeGrowingListUp += MF.getElapsedSeconds();
            
            
            
        }
        System.out.println("done buidling patterns");
        
        //print patterns
        for (int i = 0; i < patterns.length; i++) {
            if (patterns[i] == null) {
                System.out.println("streak: " + i + " = null");
                System.out.println("");
                continue;
            }
            System.out.println("streak: " + i + "\t" + patterns[i]);
            MF.printList(patterns[i].getPatternList(25));
            System.out.println("");
        }
        
        //assert patterns contain only the numbers of their specific streak number
        System.out.println("\n--------------------------------------");
        System.out.println("asserting patterns are correct");
        final long numsToCheck = 1000;  //first 1000 nums
        for (int i = 0; i < patterns.length; i++) {
            ComplexPattern pat = patterns[i];
            if (pat == null)
                continue;
            List<Long> numsInPat = pat.getPatternList(numsToCheck);
            for (long n : numsInPat) {
                if (streak6(n) != i) {
                    System.out.println("ERROR : pattern " + i + " is not correct:");
                    System.out.println("streak(" + n + ") = " + streak6(n));
                }
            }
        }
        System.out.println("done checking");
        System.out.println("-------------------------------------------\n");
        
        
        //find out values of P(s, n)
        long totalP = 0;
        long max = 4;
        int i = 1;
        
        for (; i < patterns.length; i++) {
            
            System.out.print("P(" + i + ", " + max + ") = ");
            if (patterns[i] == null)
                System.out.println("0 (null)");
            else {
                System.out.println(patterns[i].getPatternUnder(max).size());
                totalP += patterns[i].getPatternUnder(max).size();
            }
            
            max *= 4;
        }
        System.out.println("\ntotal P(i, 4^i) [1,31] = " + totalP);
        System.out.println("");
        
        
        //testing timing
        System.out.println("");
        System.out.println("time calculating pattern : " + timeCalculatingPattern);
        System.out.println("time removing : " + timeRemovingOldElements);
        System.out.println("time growing down : " + timeGrowingListDown);
        System.out.println("time growing up : " + timeGrowingListUp);
        System.out.println("");
        System.out.println("total added down : " + totalAddedDown);
        System.out.println("total added up : " + totalAddedUp);
        
        
        System.out.println("P(3,14) = " + patterns[3].getPatternUnder(14).size());
        System.out.println("P(6,10^6) = " + patterns[6].getPatternUnder((long)Math.pow(10, 6)).size());
        
        //total time
        System.out.println((System.nanoTime() - startTime)/ 1000000000.0);
        
    }
    
    
    public static long streak6(long n) {
        if (n < 2)
            return -1;
        
        long nm1 = n-1;
        
        if (n % 2 == 0)     //even numbers
            return 1;
        
        if ( nm1 % 6 != 0)
            return 2;
        
        if ( nm1 % 60 != 0) {
            if ( nm1 % 12 != 0)
                return 3;
            return 4;
        }
        
        if ( nm1 % 420 != 0)
            return 6;
        
        if ( nm1 % 2520 != 0) {
            if ( nm1 % 840 != 0)
                return 7;
            return 8;
        }
        
        if ( nm1 % 27720 != 0)
            return 10;
        
        
        //actual calculation
        long divBy = 1;
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
/*
P(1, 4) = 1
P(2, 16) = 5
P(3, 64) = 5
P(4, 256) = 17
P(5, 1024) = 0 (null)
P(6, 4096) = 59
P(7, 16384) = 20
P(8, 65536) = 52
P(9, 262144) = 0 (null)
P(10, 1048576) = 379
P(11, 4194304) = 0 (null)
P(12, 16777216) = 559
P(13, 67108864) = 0 (null)
P(14, 268435456) = 0 (null)
P(15, 1073741824) = 1490
P(16, 4294967296) = 5609
P(17, 17179869184) = 0 (null)
P(18, 68719476736) = 5313
P(19, 274877906944) = 0 (null)
P(20, 1099511627776) = 0 (null)
P(21, 4398046511104) = 0 (null)
P(22, 17592186044416) = 72285
P(23, 70368744177664) = 0 (null)
P(24, 281474976710656) = 42056
P(25, 1125899906842624) = 0 (null)
P(26, 4503599627370496) = 112150
P(27, 18014398509481984) = 0 (null)
P(28, 72057594037927936) = 866266
P(29, 288230376151711744) = 0 (null)
P(30, 1152921504606846976) = 479041
P(31, 4611686018427387904) = 31936

total P(i, 4^i) [1,31] = 1617243

*/