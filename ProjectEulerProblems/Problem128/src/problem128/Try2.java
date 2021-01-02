package problem128;

import java.util.Arrays;
import mathtools.MF;

public class Try2 {

    enum Dir {D, DR, R, U, UL, L};
    
    public static void main(String[] args) {
        final int numLevels = 10;
        int arrSize = numLevels * 2 + 1;
        long[][] grid = new long[arrSize][arrSize];
        int y = numLevels;
        int x = numLevels;
        
        grid[y][x] = 1;
        y--;
        x--;
        
        int n = 2;
        int level = 1;
        int count = 0;
        Dir dir = Dir.D;
        
        while (y >= 0) {
            grid[y][x] = n;
            count++;
            n++;
            
            /*
            for (int ty = 0; ty < grid.length; ty++) {
            for (int tx = 0; tx < grid[0].length; tx++) {
                System.out.printf("%-3d", grid[ty][tx]);
            }
                System.out.println("");
            }
            System.out.println("");
            */
            
            switch (dir) {
                case D : y++; break;
                case DR: y++; x++; break;
                case R : x++; break;
                case U : y--; break;
                case UL: y--; x--; break;
                case L : x--; break;
            }
            
            if (dir == Dir.L && count >= level) {
                dir = Dir.D;
                x--;
                y--;
                level++;
                count = 0;
            }
            else if (count >= level) {
                count = 0;
                dir = Dir.values()[(dir.ordinal()+1) % Dir.values().length];
            }
            
        }
        
        
        for (y = 0; y < grid.length; y++) {
        for (x = 0; x < grid[0].length; x++) {
            System.out.printf("%-4d", grid[y][x]);
        }
            System.out.println("");
        }
        System.out.println("");
        
        
        //traverse
        y = numLevels - 1;
        x = numLevels - 1;
        level = 1;
        count = 0;
        dir = Dir.D;
        int pdCount = 1;    //tile 1 is first tile with 3 prime differences
        
        
        while (y >= 0) {
            //visit
            if (level < numLevels) {
                if (PD(grid, y, x) == 3) {
                    System.out.print(grid[y][x]);
                    pdCount++;
                    System.out.print("\tpd# = " + pdCount);
                    System.out.println("");
                }
            }
            
            
            count++;

            switch (dir) {
                case D : y++; break;
                case DR: y++; x++; break;
                case R : x++; break;
                case U : y--; break;
                case UL: y--; x--; break;
                case L : x--; break;
            }
            
            if (dir == Dir.L && count >= level) {
                dir = Dir.D;
                x--;
                y--;
                level++;
                count = 0;
            }
            else if (count >= level) {
                count = 0;
                dir = Dir.values()[(dir.ordinal()+1) % Dir.values().length];
            }
            
        }
        
    }
    
    
    public static long[] getNeighbors(long[][] grid, int y, int x) {
        long[] neigh = new long[6];
        neigh[0] = grid[y-1][x];
        neigh[1] = grid[y-1][x-1];
        neigh[2] = grid[y][x-1];
        neigh[3] = grid[y+1][x];
        neigh[4] = grid[y+1][x+1];
        neigh[5] = grid[y][x+1];
        return neigh;
    }
    
    public static long[] differences(long[][] grid, int y, int x) {
        long[] neigh = getNeighbors(grid, y, x);
        for (int i = 0; i < neigh.length; i++) {
            neigh[i] -= grid[y][x];
            neigh[i] = Math.abs(neigh[i]);
        }
        return neigh;
    }
    
    public static int PD(long[][] grid, int y, int x) {
        long[] diff = differences(grid, y, x);
        int numPrimes = 0;
        for (long d : diff) {
            if (MF.isPrimeByPrimes2(d))
                numPrimes++;
        }
        return numPrimes;
    }
}
