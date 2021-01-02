package problem093;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mathtools.MF;

public class Problem093 {
    
    public static void main(String[] args) {
        
        long numDisctDigs = 0;
        long numDigitPerms = 0;
        long numOpPerms = 0;
        
        int longestRun = 0;
        List<Integer> bestPoss = null;
        int[] bestStart = null;
        
        for (int d0 = 0; d0 <= 9; d0++) {                   //for each set of four increasing digits d0<d1<d2<d3
        for (int d1 = d0+1; d1 <= 9 && d1 > d0; d1++) {
        for (int d2 = d1+1; d2 <= 9 && d2 > d1; d2++) {
        for (int d3 = d2+1; d3 <= 9 && d3 > d2; d3++) {
        
        /*
        for (int d0 = 0; d0 <= 9; d0++) {                   //unique digits
        for (int d1 = 0; d1 <= 9; d1++) {
            if (d1 == d0)
                continue;
        for (int d2 = 0; d2 <= 9; d2++) {
            if (d2 == d0 || d2 == d1)
                continue;
        for (int d3 = 0; d3 <= 9; d3++) {
            if (d3 == d0 || d3 == d1 || d3 == d2)
                continue;            
        */
        /*
        for (int d0 = 0; d0 <= 9; d0++) {                   //every combination of digits
        for (int d1 = 0; d1 <= 9; d1++) {
        for (int d2 = 0; d2 <= 9; d2++) {
        for (int d3 = 0; d3 <= 9; d3++) {
        */
        
            List<Integer> possibleNums = new ArrayList<>();
            
            int[] nums = {d0,d1,d2,d3};
            System.out.println(MF.listToString(nums) + "\t<--- orig");
            
            
            boolean cont = true;
            while (cont) {                                  //for each permutation of the set of digits
                
                //System.out.println("\t"+MF.listToString(nums));
                
                int start = 0;
                int end = 4*4*4-1;
                for (int ops = start; ops <= end; ops++) {      //for each permutation of operations
                    
                    String opStr = leftPad('0', Integer.toString(ops, 4), 3);
                    //System.out.print("\t\t"+combine(nums,opStr));
                    Integer result = compute(nums, opStr);
                    if (result != null) {
                        //System.out.println("\t"+result);
                        if (!possibleNums.contains(result)) {
                            possibleNums.add(result);
                        }
                    }
                    
                    numOpPerms++;
                }
                

                cont = nextPermutation(nums);
                numDigitPerms++;
            }
            
            //reset array
            int[] temp = new int[4];
            for (int i = 0; i < 4; i++)
                temp[i] = nums[nums.length-1-i];
            nums = temp;
            
            Integer[] possNums = possibleNums.toArray(new Integer[possibleNums.size()]);
            Arrays.sort(possNums);      //sort
            
            int firstPos = 0;
            while (firstPos < possNums.length && possNums[firstPos] <= 0)   //take out negative numbers (dont need)
                firstPos++;
            possNums = Arrays.copyOfRange(possNums, firstPos, possNums.length);
            System.out.println("\t" + MF.listToString(possNums));
            System.out.println("\tsize: " + possNums.length);
            
            possibleNums = new ArrayList<>(Arrays.asList(possNums));
            int mustContain = 1;
            while (possibleNums.contains(mustContain))
                mustContain++;
            mustContain--;
            
            if (mustContain > longestRun) {
                longestRun = mustContain;
                bestPoss = possibleNums;
                bestStart = nums;
            }
            
            //System.out.println("\t1.."+mustContain);
            
            
            numDisctDigs++;
        }}}}
        
        System.out.println("\n\ndistinct digit sets: " + numDisctDigs);
        System.out.println("digit permutations: " + numDigitPerms);
        System.out.println("op permutations: " + numOpPerms);
        
        System.out.println("\n\n");
        System.out.println("greatest run: 1.." + longestRun);
        System.out.println("nums: " + MF.listToString(bestStart));
        System.out.println(MF.listToString(bestPoss));
        
    }
    
    public static Integer compute(int[] nums, String ops) {
        int numerator = nums[0];
        int denom = 1;
        
        for (int i = 0; i <= 2; i++) {
            int nextNum = nums[i+1];
            char op = ops.charAt(i);
            
            switch (op) {
                case '0':
                    numerator = numerator + (nextNum * denom);
                    break;
                case '1':
                    numerator = numerator - (nextNum * denom);
                    break;
                case '2':
                    numerator *= nextNum;
                    break;
                case '3':
                    denom *= nextNum;
                    break;
            }
        }
        
        if (denom == 0)
            return null;
        else if (numerator % denom == 0) //integer result
            return new Integer(numerator / denom);
        else
            return null;
        
        
    }
    
    public static String combine(int[] nums, String ops) {
        String s = "";
        for (int i = 0; i <= 3; i++) {
            s += nums[i];
            if (i < 3)
                s += getOp(ops.charAt(i));
        }
        return s;
    }
    
    public static char getOp(char intChar) {
        if (intChar == '0')
            return '+';
        else if (intChar == '1')
            return '-';
        else if (intChar == '2')
            return '*';
        else if (intChar == '3')
            return '/';
        else
            return '#';
    }
    
    public static String leftPad(char padChar, String orig, int length) {
        if (orig.length() >= length)
            return orig;
        
        while (orig.length() < length)
            orig = padChar + orig;
        
        return orig;
        
    }

    public static void permutation(String str) { 
        permutation("", str); 
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }
    
    public static boolean nextPermutation(int[] array) {
        // Find longest non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return false;

        // Let array[i - 1] be the pivot
        // Find rightmost element that exceeds the pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        // Successfully computed the next permutation
        return true;
    }
    
}
