package problem601;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mathtools.MF;

/**
* Represents a pattern as a cycle of additions from the last element such as 3,5,9,11,15,17,... (start 3, add 2 then 4)
* A strictly increasing rule is implemented in this class, although it is reasonable to not have one.
* The reasoning of this is to not worry about patterns that never increase, or those that decrease
*/
public class ComplexPattern {
    
    public long start;
    public List<Long> steps;

    
    public ComplexPattern(long start, List<Long> steps) {
        this.start = start;
        this.steps = steps;
        if (this.steps == null || this.steps.isEmpty()) {
            this.steps = new ArrayList<>();
            this.steps.add(1L);
        }
    }

    
    public ComplexPattern(long start, Long... steps) {
        this.start = start;
        this.steps = Arrays.asList(steps);
        if (this.steps == null || this.steps.isEmpty()) {
            this.steps = new ArrayList<>();
            this.steps.add(1L);
        }
    }

    
    public ComplexPattern(long start, long[] steps) {
        this.start = start;
        this.steps = new ArrayList<>();
        for (long step : steps)
            this.steps.add(step);
        if (this.steps == null || this.steps.isEmpty()) {
            this.steps = new ArrayList<>();
            this.steps.add(1L);
        }
    }

    
    //convienence method
    public static long sum(List<Long> arr) {
        long sum = 0;
        for (long num : arr)
            sum += num;
        return sum;
    }
    
    
    @Override
    public String toString() {
        return "start: " + this.start + " steps: " + MF.listToString(this.steps);
    }

    
    /**
     * Determines if there are any numbers in either pattern that are in the other pattern.
     * @param pat1
     * @param pat2
     * @return 
     */
    public static boolean anyCollisions(ComplexPattern pat1, ComplexPattern pat2) {

        long sumSteps1 = sum(pat1.steps);
        long sumSteps2 = sum(pat2.steps);
        long maxStep = MF.max(sumSteps1, sumSteps2);
        long maxStart = MF.max(pat1.start, pat2.start);
        long end = maxStart + (10 * maxStep);

        List<Long> numbers1 = pat1.getPatternUnder(end);
        List<Long> numbers2 = pat2.getPatternUnder(end);

        for (Long n : numbers1) {
            if (numbers2.contains(n))
                return true;
        }

        return false;
    }

    
    /**
     * Returns a list of numbers in the pattern under a certain maximum number.
     * @param max
     * @return 
     */
    public List<Long> getPatternUnder(long max) {
        if (max < this.start)
            return new ArrayList<>();

        List<Long> toReturn = new ArrayList<>();
        Long toAdd = start;
        int stepIndex = 0;

        while (toAdd < max) {
            toReturn.add(toAdd);

            toAdd += steps.get(stepIndex);

            stepIndex++;
            if (stepIndex >= steps.size())
                stepIndex %= steps.size();

        }

        return toReturn;
    }


    /**
     * Returns a list of the first n elements in this pattern
     * @param numElements
     * @return 
     */
    public List<Long> getPatternList(long numElements) {
        if (numElements <= 0)
            return new ArrayList<>();

        List<Long> toReturn = new ArrayList<>();
        Long toAdd = start;
        int stepIndex = 0;

        while (toReturn.size() < numElements) {
            toReturn.add(toAdd);

            toAdd += steps.get(stepIndex);

            stepIndex++;
            if (stepIndex >= steps.size())
                stepIndex %= steps.size();

        }

        return toReturn;
    }


    /**
     * Determines if this pattern contains a specific number
     * @param num
     * @return 
     */
    public boolean contains(long num) {
        if (num < this.start)
            return false;


        try {
        if (this.steps.size() == 1)
            return ( (num - start) % steps.get(0) == 0);
        }
        catch (Exception e) {
            System.out.println(this);
            e.printStackTrace();
        }

        //break complex pattern into simple n linear patterns with a step of the sum of little steps
        long bigStep = sum(steps);
        long[] starts = new long[steps.size()];
        starts[0] = start;
        for (int i = 0; i < steps.size() - 1; i++)
            starts[i+1] = starts[i] + steps.get(i);

        for (long tempStart : starts) {
            if ( (num - tempStart) % bigStep == 0)
                return true;
        }

        return false;


    }


    public static ComplexPattern matchPattern(List<Long> nums) {
        return matchPattern(nums.toArray(new Long[nums.size()]));
    }

    
    public static ComplexPattern matchPattern(Long... nums) {
        long[] copy = new long[nums.length];
        for (int i = 0; i < nums.length; i++)
            copy[i] = nums[i];
        return matchPattern(copy);
    }


    /**
     * Tries to match a pattern to a list of numbers
     * Considers a pattern that repeats at least twice to be a pattern.
     * Give more input numbers than necessary to avoid bad patterns.
     * @param nums
     * @return 
     */
    public static ComplexPattern matchPattern(long... nums) {

        long[] numsCopy = Arrays.copyOf(nums, nums.length);

        if (numsCopy == null || numsCopy.length == 0)
            return null;
        if (numsCopy.length == 1)
            return null;

        long startNumber = numsCopy[0];

        //get difference between each number
        for (int i = 0; i < numsCopy.length - 1; i++)
            numsCopy[i] = numsCopy[i+1] - numsCopy[i];

        //cannot use last element
        numsCopy = Arrays.copyOf(numsCopy, numsCopy.length - 1);        //should just ignore last index, rather than this overhead. (inefficient!) 

        //find pattern length
        int length = 0;
        boolean gotPattern = false;
        do {
            length++;
            //see if got pattern
            boolean works = true;
            for (int startIndex = 0; startIndex < length; startIndex++) {
                for (int tempIndex = startIndex + length; tempIndex < numsCopy.length; tempIndex += length) {
                    if (numsCopy[tempIndex] != numsCopy[startIndex]) {
                        works = false;
                        break;

                    }
                }
                if (!works)
                    break;
            }
            if (works)
                gotPattern = true;


        } while (!gotPattern && length <= numsCopy.length / 2);

        ComplexPattern pat = new ComplexPattern(startNumber, Arrays.copyOf(numsCopy, length));

        return pat;
    }
}
