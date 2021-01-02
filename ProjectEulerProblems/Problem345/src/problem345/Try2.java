package problem345;

import java.util.Arrays;
import java.util.Random;
import mathtools.MF;

public class Try2 {
    
    
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
        
        int[][] matrix = grid; //to compute with
        Random rng = new Random(System.currentTimeMillis());
        final int TIMES = 1000;
        int[] scores = new int[TIMES];
        
        for (int n = 0; n < TIMES; n++) {
            int[] cols = new int[matrix.length];
            for (int i = 0; i < cols.length; i++)
                cols[i] = i;

            int count = 0;
            while (true) {

                boolean increased = false;
                //iterate
                int oldScore = scoreCols(cols, matrix);
                //pick two to swap
                int ind1 = rng.nextInt(cols.length);
                int ind2 = ind1;
                while (ind2 == ind1)
                    ind2 = rng.nextInt(cols.length);

                swap(ind1, ind2, cols);

                int newScore = scoreCols(cols, matrix);

                if (newScore > oldScore)
                    increased = true;
                else
                    swap(ind1, ind2, cols);


                if (increased)
                    count = 0;
                else if (count > 100000)
                    break;
                else 
                    count++;
            }

            System.out.println(scoreCols(cols, matrix));
            scores[n] = scoreCols(cols,matrix);
        }
        System.out.println("--------------");
        System.out.println("max: " + max(scores));
    }
    
    public static int max(int[] arr) {
        int max = arr[0];
        for (int i : arr)
            if (i > max)
                max = i;
        return max;
    }
    
    
    public static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static int scoreCols(int[] cols, int[][] arr) {
        int score = 0;
        for (int x = 0; x < arr[0].length; x++)
            score += arr[cols[x]][x];
        return score;
    }
    
    
    public static void randomizeList(int[] arr) {
        Random rng = new Random(System.currentTimeMillis());
        for (int i = 0; i < arr.length; i++) {
            int k = rng.nextInt(arr.length);
            int temp = arr[k];
            arr[k] = arr[i];
            arr[i] = temp;
        }
    }
}
//13892
//13938    <-- answer