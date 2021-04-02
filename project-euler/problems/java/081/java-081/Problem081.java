package problem081;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import mathtools.MF;

public class Problem081 {

    /*
    In the 5 by 5 matrix below, the minimal path sum from the top left to the bottom right, by only moving to the right and down, is indicated in bold red and is equal to 2427.
    Find the minimal path sum, in matrix.txt (right click and "Save Link/Target As..."), a 31K text file containing a 80 by 80 matrix, from the top left to the bottom right by only moving right and down.
    */
    public static void main(String[] args) {
        
        Random rng = new Random(System.currentTimeMillis());
        
        //build matrix
        Scanner reader = null;
        File curDir = new File("");
        List<String> lines = new ArrayList<>();
        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem081/matrix.txt"));
            while (reader.hasNextLine())
                lines.add(reader.nextLine());
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        int[][] matrix = new int[80][80];
        for (int y = 0; y < 80; y++) {
            String line = lines.get(y);
            String[] nums = line.split(",");
            for (int x = 0; x < 80; x++)
                matrix[y][x] = Integer.parseInt(nums[x]);
        }
        
        //visit node, move to next node (choosing smallest node)
        //continue until at (79,79)
        /*
        int smallestNeighborPath = 0;
        int x = 0;
        int y = 0;
        while (x < 79 && y < 79) {
            smallestNeighborPath += matrix[y][x];
            if (x >= 79)    //on bottom row
                y++;
            if (y >= 79)    //on last column
                x++;

            if (matrix[y+1][x] > matrix[y][x+1])   //chose smallest path
                y++;
            else
                x++;
        }
        */
            
        //random paths
        /*
        int minRandomPath = Integer.MAX_VALUE;
        final int timesToGuess = 100000;
        for (int i = 0; i < timesToGuess; i++) {
            int path = 0;
            x = 0; 
            y = 0;
            while (x < 79 && y < 79) {
                path += matrix[y][x];
                if (x >= 79)    //on bottom row
                    y++;
                if (y >= 79)    //on last column
                    x++;

                if (rng.nextBoolean())   //chose smallest path
                    y++;
                else
                    x++;
            }
            if (path < minRandomPath)
                minRandomPath = path;
        }
        
        System.out.println("Smallest neighbor: \t" + smallestNeighborPath);
        System.out.println("Random paths (" + timesToGuess + ") : \t" + minRandomPath);
        */
        
        int[][] testGrid = new int[][] {
            {131, 673, 234, 103, 18},
            {201, 96, 342, 965, 150},
            {630, 803, 746, 422, 111},
            {537,699,497,121,956},
            {805,732,524,37,331}
        };
        
        MF.startTimer();
        
        System.out.println("Smallest path : " + getSmallestPath(matrix));
        
        System.out.println("time: " + MF.getElapsedSeconds());
    }
    
    public static int getSmallestPath(int[][] grid) {
        
        //for each diagonal
        //for each node in diagonal
        //add smallest parent to node
        //smallest path is in last node
        
        int startY = 1;     //start on second row, since nothing can happen on first row
        int startX = 0;
        
        while (startY <= grid.length && startX <= grid[0].length) {
            
            //for each node
            for (int x = startX, y = startY; x < grid[0].length && y >= 0; x++, y--) {
                //System.out.print(grid[y][x] + "\t");
                int parentLeft = (x == 0) ? Integer.MAX_VALUE : grid[y][x-1];
                int parentRight = (y == 0) ? Integer.MAX_VALUE : grid[y-1][x];
                
                if (parentLeft < parentRight)
                    grid[y][x] += parentLeft;
                else 
                    grid[y][x] += parentRight;
            }
            //System.out.println();
            
            //increment diagonal
            if (startY < grid.length - 1)
                startY++;
            else
                startX++;
        }
        
        
        return grid[grid.length-1][grid[0].length -1];
    }
    

}
//answer: Smallest path : 427337