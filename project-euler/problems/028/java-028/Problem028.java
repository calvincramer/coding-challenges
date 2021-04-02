package problem028;

public class Problem028 {

    /*
    Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

    21 22 23 24 25
    20  7  8  9 10
    19  6  1  2 11
    18  5  4  3 12
    17 16 15 14 13

    It can be verified that the sum of the numbers on the diagonals is 101.

    What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
    */
    public static void main(String[] args) {

        final int size = 1001;
        int[][] grid = new int[size][size];
        
        Direction dir = Direction.RIGHT;
        int x = size/2;
        int y = size/2;
        int n = 1;
        
        while(n < (size*size)) { //top left
            if (y >= size || y < 0 || x < 0 || x >= size) {
                System.out.println("Going out of bounds");
                break;
            }
            grid[y][x] = n;
            
            //printGrid(grid);
            //System.out.println();
            
            switch (dir) {
                case UP: 
                    y -= 1;
                    if (grid[y][x+1] == 0)
                        dir = dir.rotate90Clockwise();
                    break;
                case RIGHT: 
                    x += 1;
                    if (grid[y+1][x] == 0)
                        dir = dir.rotate90Clockwise();
                    break;
                case DOWN: 
                    y += 1;
                    if (grid[y][x-1] == 0)
                        dir = dir.rotate90Clockwise();
                    break;
                case LEFT: 
                    x -=1;
                    if (grid[y-1][x] == 0)
                        dir = dir.rotate90Clockwise();
                    break;
                default:
                    System.out.println("Error");
            }
            
            n++;
        }
        grid[y][x] = n; //fill in last square
        
        printGrid(grid);
        
        long sumOfDiagonals = 0;
        for (int i = 0; i < size; i++) {
            sumOfDiagonals += grid[i][i];
            sumOfDiagonals += grid[size-1-i][i];
            if (i == size / 2)
                sumOfDiagonals -= grid[i][i];
        }
        
        System.out.println();
        System.out.println("Sum of diags: " + sumOfDiagonals);
        
    }
    //answer : 669171001
    
    public static void printGrid(int[][] g) {
        int length = String.valueOf(g[0][0]).length() + 1;
        for (int y = 0; y < g.length; y++) {
        for (int x = 0; x < g[0].length; x++) {
            System.out.printf("%" + length + "s", g[y][x]);
        }
            System.out.println();
        }
    }
    
    public enum Direction {
        UP, RIGHT, DOWN, LEFT;
        public Direction rotate90Clockwise() {
            return Direction.values()[(this.ordinal()+1)%Direction.values().length];
        }
    }

}
