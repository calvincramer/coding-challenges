package problem093;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mathtools.MF;
import static problem093.Problem093.compute;
import static problem093.Problem093.leftPad;
import static problem093.Problem093.nextPermutation;

public class Try2 {
    
    public static void main(String[] args) {

        final int[][] groupingIndexes = {{},  //grouping indexes, positive for (, negative for )
            {-3,0},
            {-5,2},
            {-7,4},
            {-7,4,-3,0},
            {-5,0},
            {-7,2},
            {-5,-3,0,0},
            {-5,-5,2,0},
            {-7,-5,2,2},
            {-7,-7,4,2}
        };
        
        long numDisctDigs = 0;
        long numDigitPerms = 0;
        long numOpPerms = 0;
        long groupingPerms = 0;
        
        int longestRun = 0;
        List<Integer> bestPoss = null;
        int[] bestStart = null;
        
        for (int d0 = 0; d0 <= 9; d0++) {                           //for each set of four increasing digits d0<d1<d2<d3
        for (int d1 = d0+1; d1 <= 9 && d1 > d0; d1++) {
        for (int d2 = d1+1; d2 <= 9 && d2 > d1; d2++) {
        for (int d3 = d2+1; d3 <= 9 && d3 > d2; d3++) {
        
            List<Integer> possibleNums = new ArrayList<>();         //store every integer result using these digits
            
            int[] nums = {d0,d1,d2,d3};
            //System.out.println(MF.listToString(nums) + "\t<--- orig");
            
            
            boolean cont = true;
            while (cont) {                                          //for each permutation of the set of digits

                int start = 0;
                int end = 4*4*4-1;
                for (int ops = start; ops <= end; ops++) {          //for each permutation of operations
                    
                    String opStr = leftPad('0', Integer.toString(ops, 4), 3);
                    opStr = opStr.replaceAll("0", "+");
                    opStr = opStr.replaceAll("1", "-");
                    opStr = opStr.replaceAll("2", "*");
                    opStr = opStr.replaceAll("3", "/");
                    
                    String noGroupingExpression = "";       //build algebreic expression as string
                    for (int i = 0; i < 3; i++)
                        noGroupingExpression += nums[i] + "" + opStr.charAt(i);
                    noGroupingExpression += nums[3];
                    
                    //apply all ways to group with parenthesis
                    for (int[] grouping : groupingIndexes) {
                        String expressionWithGroupings = noGroupingExpression;
                        for (int index : grouping) {
                            char paren = (index < 0) ? ')' : '(';
                            expressionWithGroupings = expressionWithGroupings.substring(0, Math.abs(index)) 
                                    + paren 
                                    + expressionWithGroupings.substring(Math.abs(index), expressionWithGroupings.length());

                        }
                        
                        //compute answer
                        AlgebraExpression expr = new AlgebraExpression(expressionWithGroupings);
                        try {
                            Fraction result = expr.computeExpression();
                            if (result.getValue() instanceof Long) {
                                Integer ans = ((Long) result.getValue()).intValue();
                                if (!possibleNums.contains(ans))
                                    possibleNums.add(ans);
                            }
                            
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        
                        groupingPerms++;
                    }
                    
                    numOpPerms++;
                }
                

                cont = nextPermutation(nums);
                numDigitPerms++;
            }
            
            
            Collections.sort(possibleNums);
            while (possibleNums.get(0) <= 0 && !possibleNums.isEmpty())
                possibleNums.remove(0);
            int mustContain = 1;
            while (mustContain - 1 < possibleNums.size() && possibleNums.get(mustContain - 1) == mustContain)
                mustContain++;
            mustContain--;
            
            int[] temp = new int[4];    //reset digit array to original order
            for (int i = 0; i < 4; i++)
                temp[i] = nums[nums.length-1-i];
            nums = temp;
            
            if (mustContain > longestRun) {     //possibly update greatest run
                longestRun = mustContain;
                bestPoss = possibleNums;
                bestStart = nums;
            }
            
            System.out.println(MF.listToString(nums));
            System.out.println(MF.listToString(possibleNums));
            System.out.println("size: " + possibleNums.size());
            
            numDisctDigs++;
        }}}}
        
        System.out.println("\n\ndistinct digit sets: " + numDisctDigs);
        System.out.println("digit permutations: " + numDigitPerms);
        System.out.println("op permutations: " + numOpPerms);
        System.out.println("grouping permutations: " + groupingPerms);
        
        System.out.println("\n\n");
        System.out.println("greatest run: 1.." + longestRun);
        System.out.println("nums: " + MF.listToString(bestStart));
        System.out.println(MF.listToString(bestPoss));
    }

    
}
//answer:
/*
greatest run: 1..51
nums: [1, 2, 5, 8]
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 
41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51,  55, 56, 57, 60, 63, 64, 70, 72, 75, 78, 79, 80, 81, 82, 85, 88, 90, 96, 120]
*/