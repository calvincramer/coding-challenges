package problem082;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import mathtools.MF;

public class Problem082 {
    
    

    public static void main(String[] args) {
        Random rng = new Random(System.currentTimeMillis());
        
        //build matrix
        Scanner reader = null;
        File curDir = new File("");
        List<String> lines = new ArrayList<>();
        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem082/matrix.txt"));
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
        
        int[][] smallMatrix = new int[10][10];
        for (int y = 0; y < 10; y++) {
            String line = lines.get(y);
            String[] nums = line.split(",");
            for (int x = 0; x < 10; x++)
                smallMatrix[y][x] = Integer.parseInt(nums[x]);
        }
        
        for (int y = 0; y < smallMatrix.length; y++) {
            for (int x = 0; x < smallMatrix[y].length; x++)
                System.out.print(smallMatrix[y][x] + "\t");
            System.out.println();
        }
        
        //tests
        
        long bestSmallNeigh = Long.MAX_VALUE;
        for (int y = 0; y < matrix.length; y++) {
            long path = smallestNeighborPath(y,0, matrix);
            //System.out.println(path);
            if (path < bestSmallNeigh)
                bestSmallNeigh = path;
        }
        
        System.out.println("Random forever:");
        long bestRandomPath = Long.MAX_VALUE;
        //for (int n = 1; n <= 50000000; n++) {
        while(true) {
            int y = rng.nextInt(matrix.length);
            long path = randomPath(y, 0, matrix);
            if (path < bestRandomPath) {
                bestRandomPath = path;
                System.out.println(bestRandomPath);
            }
        }
        
        
        
        
        //System.out.println("Smallest smallestNeighbor: " + bestSmallNeigh);
        //System.out.println("Smallest randomPath: " + bestRandomPath);
    }
    

    public static long smallestNeighborPath(int startY, int startX, int[][] grid) {
        int y = startY;
        int x = startX;
        long sum = grid[y][x];
        Direction lastDir = Direction.RIGHT;   //only care about up and down infinite loops
        
        while (x < grid[0].length - 1) {
            
            int up = (y == 0) ? Integer.MAX_VALUE : grid[y-1][x];
            int right = (x == grid[0].length - 1) ? Integer.MAX_VALUE : grid[y][x+1];
            int down = (y == grid.length - 1) ? Integer.MAX_VALUE : grid[y+1][x];
            
            //System.out.println("at " + y + ", " + x + " up: " + up + ", right: " + right + ", " + down);
            //System.out.print("at (" + y + ", " + x + ")");
            
            //choose next location
            if (lastDir == Direction.RIGHT) {
                
                int smallest = MF.min(up, right, down);
                
                if (smallest == up) {
                    lastDir = Direction.UP;
                    y--;
                }
                else if (smallest == down) {
                    lastDir = Direction.DOWN;
                    y++;
                }
                else {
                    lastDir = Direction.RIGHT;
                    x++;
                }
            }
            else 
            {
                if (lastDir == Direction.UP) {  
                    //choose between up and right
                    if (up < right) {
                        lastDir = Direction.UP;
                        y--;
                    }
                    else {
                        lastDir = Direction.RIGHT;
                        x++;
                    }
                }
                else if (lastDir == Direction.DOWN) {   
                    //choose between down and right
                    if (down < right) {
                        lastDir = Direction.DOWN;
                        y++;
                    }
                    else {
                        lastDir = Direction.RIGHT;
                        x++;
                    }
                }
            }
            //add current location
            sum += grid[y][x];
            
            //System.out.println(" going " + lastDir.toString() + " to " + grid[y][x]);
        }
        
        return sum;
    }
    
    public static long randomPath(int startY, int startX, int[][] grid) {
        Random rng = new Random(System.currentTimeMillis());
        int y = startY;
        int x = startX;
        long sum = grid[y][x];

        while (x < grid[0].length - 1) {
            
            //System.out.println(y + ", " + x);
            
            int dir = rng.nextInt(10);  //0 to 9
                //0 is up
                //1 is down
                //2 - 9 are right (8 / 10 values = 80percent)
            
            if (dir == 0) { //up
                if (y > 0)
                    y--;
            }
            else if (dir == 1) {    //down
                if (y < grid.length - 1)
                    y++;
            }
            else {  //right
                if ( x < grid[0].length - 1)
                    x++;
            }
            
            
            //add current location
            sum += grid[y][x];
        }
        
        return sum;
    }
    
    public enum Direction {
        UP, DOWN, RIGHT;
    }
}
/*
Smallest smallestNeighbor: 431728
Smallest randomPath: 332444
*/
