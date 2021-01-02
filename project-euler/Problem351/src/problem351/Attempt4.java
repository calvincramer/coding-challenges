package problem351;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;


public class Attempt4 {
    
    public static final int hmil = 100000000;
    public static int[][] collisions;

    public static void main(String[] args) {
        
        int[] ns = {1,2,3,4,5, 10, 20, 50};
        for (int n : ns) {
            collisions = new int[n+1][n+1];
            MF.startTimer();
            long res = h(n);
            System.out.println("H(" + n + ") = " + res + "\t\t" + MF.getElapsedSeconds());
            
            //printGrid(collisions);
            //System.out.println("");
            //System.out.println("guess:");
            
            
            int[][] guess = new int[n+1][n+1];
            for (int y = 0; y < n+1; y++) {
                for (int x = 0; x < n+1; x++) {
                    if (y != 0 && y >= x)
                        guess[x][y] = (n-y)/y;
                }
            }
            //printGrid(guess);
            //System.out.println("");
        }
        
    }
    
    /**
     * visiting each individual point
     * @param size
     * @return 
     */
    public static long h(int size) {
        
        long sum = 0;
        
        for (int s = 2; s <= size; s++) {
            
            for (int i = 0; i <= s; i++) {
                
                long gfac = MF.gcf(i, s);
                
                if (gfac != 1) {    //collision
                    sum++;
                    
                    int numerator = i / (int) gfac;
                    int denom = s / (int) gfac;
                    collisions[numerator][denom] += 1;
                }
                    
                else {  //no collision
                    
                }
                    
                
            }
            
        }

        return (sum - size + 1) * 6;
    }
    
    public static void printGrid(int[][] grid) {
        int greatest = 0;
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++)
                if (grid[y][x] > greatest)
                    greatest = grid[y][x];
        int length = (greatest+"").length() + 1;
        
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++)
                System.out.print(fixedWidthString(""+grid[y][x], length));
            System.out.println("");
        }
        
    }
    
    public static String fixedWidthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }
    
    private static class Triplet {
        public long numerator;
        public long denom;
        public long counter = 0;
        
        public Triplet(long numerator, long denom) {
            this.numerator = numerator;
            this.denom = denom;
        }
    }
}
/*
A whole lot faster than previous attemps
Will take a very long time to calculate n = 100 000 000
takes about 17 seconds for the last row alone

H(5) = 30		9.6476E-5
H(10) = 138		1.0232E-5
H(1000) = 1177848		0.038219596
H(10000) = 117645084		4.449003362
H(100000) = 11762395476		555.257037102
*/
/*
H(1) = 0		9.5599E-5
H(2) = 6		1.0525E-5
H(3) = 12		6.139E-6
H(4) = 24		4.678E-6
H(5) = 30		5.555E-6
H(10) = 138		8.77E-6
H(20) = 492		2.9235E-5
H(50) = 3006		1.11678E-4
*/