package problem058;

import java.util.ArrayList;
import java.util.List;

public class Problem058 {

   /*
    Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side length 7 is formed.

    37 36 35 34 33 32 31
    38 17 16 15 14 13 30
    39 18  5  4  3 12 29
    40 19  6  1  2 11 28
    41 20  7  8  9 10 27
    42 21 22 23 24 25 26
    43 44 45 46 47 48 49

    It is interesting to note that the odd squares lie along the bottom right diagonal, but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; that is, a ratio of 8/13 ≈ 62%.

    If one complete new layer is wrapped around the spiral above, a square spiral with side length 9 will be formed. If this process is continued, what is the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%?
    */
    public static void main(String[] args) {
        /*
        int[][] grid;
        int primes = 1;
        int diags = 1;
        for (int size = 3; primes*1.0/diags >= 0.1; size += 2) {
            grid = new int[size][size];
            generateSpiral(grid);
            
            diags = size / 2 * 4 + 1;
            primes = 0;
            
            System.out.println("Spiral length: " + size);
            
            //System.out.print("Primes: ");
            for (int i = 0; i < size / 2; i++) {
                if (isPrime(grid[i][i])) {
                    primes++;
                    //System.out.print(grid[i][i] + " ");
                }
                if (isPrime(grid[grid.length-1-i][i])) {
                    primes++;
                    //System.out.print(grid[grid.length-1-i][i] + " ");
                }
                if (isPrime(grid[i][grid.length-1-i])) {
                    primes++;
                    //System.out.print(grid[i][grid.length-1-i] + " ");
                }
                
            }
            //System.out.println("");
            System.out.println("Num primes: " + primes + " / " + diags);
            System.out.println(primes*1.0/diags);
            
            
            System.out.println("");
        }
        */
        
        long number = 2;
        long length = 3;
        long cornerPrimes = 0;
        long totalCorners = 1;  //counts all diagonals plus the middle
        boolean done = false;
        long[] corners = new long[4];
        
        while (!done) {
            number += length - 2; //go to first corner
            corners[0] = number;
            number += length - 1;   //top left corner
            corners[1] = number;
            number += length - 1;   //bottom left corner
            corners[2] = number;
            number += length - 1;   //bottom right corner
            corners[3] = number;
            
            System.out.println("Length: " + length);
            System.out.print("Corners: " );
            for (long n : corners)
                System.out.print(n + ", ");
            System.out.println("");
            
            number += 1;    //start of next spiral
            length += 2;    //length of spiral increases by 2
            totalCorners += 4;  //encountered 4 corners
            
            //count primes
            for (long corner : corners) {
                if (isPrimeFast(corner)) {
                    cornerPrimes++;
                }
            }
            
            System.out.println(cornerPrimes + " / " + totalCorners + " = " + (cornerPrimes*1.0/totalCorners));
            System.out.println("");
            //check for done
            if (cornerPrimes * 1.0 / totalCorners < 0.1) {
                done = true;
            }
            
        }
        
    }
    /*
    answer:
    Length: 26241
    5248 / 52481 = 0.09999809454850327
    */
    
    public static void generateSpiral(int[][] grid) {
        int num = 1;
        int x = grid[0].length / 2;
        int y = grid.length / 2;
        int direction = 0;
        int maxNumber = grid.length * grid[0].length;
        
        
        while (x >= 0 && y >= 0 && x < grid[0].length && y < grid.length && num < maxNumber) {
            
            grid[y][x] = num;
            num++;
            
            //printGrid(grid);
            //System.out.println("");
            
            switch (direction) {
                case 0: 
                    x += 1;
                    if (grid[y-1][x] == 0)
                        direction++;
                    break;
                case 1: 
                    y -= 1;
                    if (grid[y][x-1] == 0)
                        direction++;
                    break;
                case 2: 
                    x -= 1;
                    if (grid[y+1][x] == 0)
                        direction++;
                    break;
                case 3: 
                    y += 1;
                    if (grid[y][x+1] == 0)
                        direction = 0;
                    break;
            }
        }
        grid[y][x] = num++;
    }
    
    public static void printGrid(int[][] grid) {
        int maxLength = 0;
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++)
                if ((grid[y][x]+"").length() > maxLength)
                    maxLength = (grid[y][x]+"").length();
            
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                System.out.print(fixedWidthString(grid[y][x]+"", maxLength) + " ");
            } 
            System.out.println("");
        }
    }
    
    public static void printGrid(ArrayList<ArrayList<Integer>> grid) {
        for (int y = 0; y < grid.size(); y++) {
            for (int x = 0; x < grid.get(y).size(); x++) {
                System.out.print(fixedWidthString(grid.get(y).get(x)+"", 10) + "  ");
            } 
            System.out.println("");
        }
    }
    
    public static String fixedWidthString(String string, int length) {
        return String.format("%1$" + length + "s", string);
    }
    
    public static boolean isPrime(int num) {
        if (num < 2) 
            return false;
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) return false;
        }
        return true;
    }
    
    public static boolean isPrime(long num) {
        if (num < 2) 
            return false;
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) return false;
        }
        return true;
    }
    
    public static boolean isPrimeFast(long num) {
        if (num < 2) 
            return false;
        for (int n = 2; n <= (int) Math.sqrt(num); n++) {
            if (num % n == 0) return false;
        }
        
        return true;
    }
}
