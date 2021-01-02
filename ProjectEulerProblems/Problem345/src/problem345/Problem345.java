package problem345;

import java.util.ArrayList;
import java.util.List;

public class Problem345 {
    
    public static int[][] temp;
    
    public static void main(String[] args) {
        int[][] smallGrid = {
            {  7, 53,183,439,863},
            {497,383,563, 79,973},
            {287, 63,343,169,583},
            {627,343,773,959,943},
            {767,473,103,699,303}
        };
        int[][] smallGrid2 = {
            { 53,183,439,863},
            {383,563, 79,973},
            { 63,343,169,583},
            {343,773,959,943},
        };
        int[][] grid = {
            {  7, 53,183,439,863,497,383,563, 79,973,287, 63,343,169,583},
            {627,343,773,959,943,767,473,103,699,303,957,703,583,639,913},
            {447,283,463, 29, 23,487,463,993,119,883,327,493,423,159,743},
            {217,623,  3,399,853,407,103,983, 89,463,290,516,212,462,350},
            {960,376,682,962,300,780,486,502,912,800,250,346,172,812,350},
            {870,456,192,162,593,473,915, 45,989,873,823,965,425,329,803},
            {973,965,905,919,133,673,665,235,509,613,673,815,165,992,326},
            {322,148,972,962,286,255,941,541,265,323,925,281,601, 95,973},
            {445,721, 11,525,473, 65,511,164,138,672, 18,428,154,448,848},
            {414,456,310,312,798,104,566,520,302,248,694,976,430,392,198},
            {184,829,373,181,631,101,969,613,840,740,778,458,284,760,390},
            {821,461,843,513, 17,901,711,993,293,157,274, 94,192,156,574},
            { 34,124,  4,878,450,476,712,914,838,669,875,299,823,329,699},
            {815,559,813,459,522,788,168,586,966,232,308,833,251,631,107},
            {813,883,451,509,615, 77,281,613,459,205,380,274,302, 35,805} };
        
        int[][] matrix = smallGrid2; //to compute with
        /*
        temp = new int[matrix.length][matrix[0].length];
        
        int score = compute(matrix, 0, new ArrayList<>());
        
        System.out.println("\n\nscore: " + score);
        System.out.println("");
        
        printGrid(temp);
        */
        
        
        //testing
        temp = new int[matrix.length][matrix[0].length];
        
        for (int y = 0; y < temp.length; y++) {
            for (int x = 0; x < temp[0].length; x++) {
                int willCrossOut = -2 * matrix[y][x];   //will go this entry twice, don't want to count
                for (int ty = 0; ty < temp.length; ty++)
                    willCrossOut += matrix[ty][x];
                for (int tx = 0; tx < temp[0].length; tx++)
                    willCrossOut += matrix[y][tx];
                temp[y][x] = willCrossOut;
            }
        }
        System.out.println("\n\n------------------");
        printGrid(temp);
        
        
    }
    
    public static int compute(int[][] arr, int col, List<Integer> usedRows) {
        
        if (col >= arr[0].length)
            return 0;
        
        int bestRow = 0;
        int bestScore = Integer.MIN_VALUE;
        
        for (int y = 0; y < arr.length; y++) {
            if (usedRows.contains(y))
                continue;
            int score = arr[y][col];
            for (int x = col+1; x < arr[0].length; x++) 
                score -= arr[y][x];
            
            if (score > bestScore) {
                bestRow = y;
                bestScore = score;
            }
        }
        //cross out
        for (int x = col +1; x < arr[0].length; x++) {
            arr[bestRow][x] = 0;
        }
        
        printGrid(arr);
        System.out.println("");
        
        temp[bestRow][col] = arr[bestRow][col];
        usedRows.add(bestRow);
        
        return arr[bestRow][col] + compute(arr, col+1, usedRows);
    }
    
    
    public static void printGrid(int[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[0].length; x++)
                System.out.print(arr[y][x] + "\t");
            System.out.println("");
        }
    }
}