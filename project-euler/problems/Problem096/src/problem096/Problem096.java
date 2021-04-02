
package problem096;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mathtools.MF;
import mathtools.MF.Coordinate;

public class Problem096 {

    /*
    Su Doku (Japanese meaning number place) is the name given to a popular puzzle concept. Its origin is unclear, but credit must be attributed to Leonhard Euler who invented a similar, and much more difficult, puzzle idea called Latin Squares. The objective of Su Doku puzzles, however, is to replace the blanks (or zeros) in a 9 by 9 grid in such that each row, column, and 3 by 3 box contains each of the digits 1 to 9. Below is an example of a typical starting puzzle grid and its solution grid.
    
    A well constructed Su Doku puzzle has a unique solution and can be solved by logic, although it may be necessary to employ "guess and test" methods in order to eliminate options (there is much contested opinion over this). The complexity of the search determines the difficulty of the puzzle; the example above is considered easy because it can be solved by straight forward direct deduction.

    The 6K text file, sudoku.txt (right click and 'Save Link/Target As...'), contains fifty different Su Doku puzzles ranging in difficulty, but all with unique solutions (the first puzzle in the file is the example above).

    By solving all fifty puzzles find the sum of the 3-digit numbers found in the top left corner of each solution grid; for example, 483 is the 3-digit number found in the top left corner of the solution grid above.
    */
    
    public static GrabBag<Integer> oneToNine = new GrabBag<>(1,2,3,4,5,6,7,8,9);
    public static int puzzlesSolved = 0;
    public static Integer[][] solved = new Integer[][] {
        {4,8,3,9,2,1,6,5,7},
        {9,6,7,3,4,5,8,2,1},
        {2,5,1,8,7,6,4,9,3},
        {5,4,8,1,3,2,9,7,6},
        {7,2,9,5,6,4,1,3,8},
        {1,3,6,7,9,8,2,4,5},
        {3,7,2,6,8,9,5,1,4},
        {8,1,4,2,5,3,7,6,9},
        {6,9,5,4,1,7,3,8,2}
    };
    
    
    public static void main(String[] args) {
        
        Scanner reader = null;
        File curDir = new File("");
        List<String> lines = new ArrayList<>();
        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem096/sudoku.txt"));
            while (reader.hasNextLine())
                lines.add(reader.nextLine());
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        List<Integer[][]> puzzles = new ArrayList<>();
        
        for (int i = 0; i < lines.size(); i += 10) {
            Integer[][] puzzle = new Integer[9][9];
            for (int line = 0; line < 9; line++) {
                char[] digits = lines.get(i+line+1).toCharArray();
                for (int d = 0; d < 9; d++)
                    puzzle[line][d] = Character.getNumericValue(digits[d]);
            }
            puzzles.add(puzzle);
        }
        
        //brute force test
        bruteForce(puzzles.get(0));

        //solve puzzles
        for (int i = 0; i < puzzles.size(); i++) {
            
            solvePuzzle(puzzles.get(i));
            
            printPuzzle(puzzles.get(i));
            
            //System.out.println("SOLVED\n###################################");
            System.out.println("Puzzle " + (i+1) + " : " + isComplete(puzzles.get(i)));
            System.out.println();
            
            if (isComplete(puzzles.get(i)))
                puzzlesSolved++;
        }
        System.out.println("Solved: " + puzzlesSolved + "/" + puzzles.size());
        
        int sum = 0;
        for (int i = 0; i < puzzles.size(); i++) {
            int n = puzzles.get(i)[0][0] * 100;
            n += puzzles.get(i)[0][1] * 10;
            n += puzzles.get(i)[0][2];
            sum += n;
        }
        System.out.println("Sum: " + sum);
        
    }
    
    public static void solvePuzzle(Integer[][] grid) {
        
        while (!isComplete(grid)) {
            
            //printPuzzle(grid);
            //System.out.println("\n");
            
            boolean r1 = fillInOne(grid);
            boolean r2 = evaluateSubGrids(grid);
            boolean r3 = evaluateRowsAndCols(grid);
            
            if (r1 == false 
                    && r2 == false 
                    && r3 == false)
                break;  //give up
        }
        if (!isComplete(grid)) {
            System.out.println("Brute forcing");
            bruteForce(grid);
        }
    }
    
    public static boolean evaluateSubGrids(Integer[][] grid) {
        //rule 2
        
        //printPuzzle(grid);
        
        for (int gy = 0; gy < 9; gy += 3) {
        for (int gx = 0; gx < 9; gx += 3) {
            GrabBag[][] candidates = new GrabBag[3][3];
            for (int dy = 0; dy < 3; dy++) 
                for (int dx = 0; dx < 3; dx++)
                    if (grid[gy+dy][gx+dx] == 0)
                        candidates[dy][dx] = getCandidates(grid,gy+dy, gx+dx);
                    else
                        candidates[dy][dx] = new GrabBag();
            
            //When a candidate number appears just once in an area (row, column or box), 
            //that number goes into the square.
            //look for a number that only appears once in candidates
            for (int num = 1; num <= 9; num++) {
                int totalFound = 0;
                int lastFoundY = -1;
                int lastFoundX = -1;
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        if (candidates[y][x].bag.contains(num)) {
                            totalFound++;
                            lastFoundY = y;
                            lastFoundX = x;
                        }
                    }
                }
                if (totalFound == 1) {
                    grid[gy+lastFoundY][gx+lastFoundX] = num;
                    return true;
                }
                
            }
            
        }}
        
        return false;
    }
    
    public static boolean evaluateRowsAndCols(Integer[][] grid) {
        
        //for each row
        for (int y = 0; y < 9; y++) {
            GrabBag[] row = new GrabBag[9];
            for (int x = 0; x < 9; x++)
                row[x] = getCandidates(grid, y, x);
            
            for (int num = 1; num <= 9; num++) {    //check if there is a num that appears once
                int numTimeAppears = 0;
                int lastX = -1;
                for (int x = 0; x < 9; x++) {
                    if (row[x].bag.contains(num)) {
                        numTimeAppears++;
                        lastX = x;
                    }
                }
                if (numTimeAppears == 1) {
                    grid[y][lastX] = num;
                    return true;
                }
            }
            
        }
        
        //for each col
        for (int x = 0; x < 9; x++) {
            GrabBag[] col = new GrabBag[9];
            for (int y = 0; y < 9; y++)
                col[y] = getCandidates(grid, y, x);
            
            for (int num = 1; num <= 9; num++) {    //check if there is a num that appears once
                int numTimeAppears = 0;
                int lastY = -1;
                for (int y = 0; y < 9; y++) {
                    if (col[y].bag.contains(num)) {
                        numTimeAppears++;
                        lastY = y;
                    }
                }
                if (numTimeAppears == 1) {
                    grid[lastY][x] = num;
                    return true;
                }
            }
            
        }
        
        return false;
    }
    
    public static GrabBag<Integer> getCandidates(Integer[][] grid, int y, int x) {
        
        if (grid[y][x] != 0)
            return new GrabBag();
        
        GrabBag<Integer> candidates = oneToNine.duplicate();
        //remove from 3x3, row, col
        //row
        for (int travx = 0; travx < 9; travx++) 
            if (grid[y][travx] != 0)
                candidates.remove(grid[y][travx]);
        //col
        for (int travy = 0; travy < 9; travy++) 
            if (grid[travy][x] != 0)
                candidates.remove(grid[travy][x]);
        //3x3
        int startY = (y / 3) * 3;   //top left corner of 3x3 grid
        int startX = (x / 3) * 3;
        for (int travY = startY; travY < startY + 3; travY++) {
            for (int travX = startX; travX < startX + 3; travX++) {
                if (grid[travY][travX] != 0)
                    candidates.remove(grid[travY][travX]);
            }
        }
        return candidates;
    }
    
    public static GrabBag<Integer> getCandidatesPretendZero(Integer[][] grid, int y, int x) {
        int temp = grid[y][x];
        grid[y][x] = 0;
        GrabBag<Integer> gb = getCandidates(grid, y, x);
        grid[y][x] = temp;
        return gb;
    }
    
    public static boolean fillInOne(Integer[][] grid) {
        //look for empty spot
        for (int y = 0; y < 9; y++) {
        for (int x = 0; x < 9; x++) {
            if (grid[y][x] == 0) {
                
                GrabBag<Integer> numsNotThere = getCandidates(grid,y,x);

                if (numsNotThere.size() == 1) {
                    grid[y][x] = numsNotThere.bag.get(0);
                    return true;
                }
            }
        }}
        return false;
    }
    
    public static boolean bruteForce(Integer[][] grid) {
        
        List<Coordinate> points = new ArrayList<>();    //all squares that are initially empty (have zeroes)
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++)
                if (grid[y][x] == 0)
                    points.add(new Coordinate(y,x));
        
        int pointIndex = 0;
        
        //printPuzzle(grid);
        //System.out.println();
        
        while (!isComplete(grid) || pointIndex >= 0) {
            
            //printPuzzle(grid);
            //System.out.println();
            
            //get candidates of current point
            //if number of candidates is zero, hit a wall, fill point with zero and backtrack
            //if number of candidates in non zero, increment candidate
            int py = points.get(pointIndex).y;
            int px = points.get(pointIndex).x;
            
            GrabBag<Integer> candidates = getCandidatesPretendZero(grid, py, px);
            
            if (candidates.bag.size() == 0) {
                grid[py][px] = 0;
                pointIndex--;
                continue;
            }
            //place a candidate
            if (grid[py][px] == 0) {
                grid[py][px] = candidates.bag.get(0);
            } else {
                //increment candidate
                if (grid[py][px] == candidates.bag.get(candidates.bag.size()-1)) {  //if incrementing candidate, and at last candidate, backtrack
                    grid[py][px] = 0;
                    pointIndex--;
                    continue;
                }
                for (int candI = 0; candI < candidates.bag.size() - 1; candI++) {   //increment candidate   
                    if (grid[py][px] == candidates.bag.get(candI)) {
                        grid[py][px] = candidates.bag.get(candI + 1);
                        break;
                    }
                }
            }
            pointIndex++;
            if (pointIndex >= points.size())
                return true;
        }
        
        return true;
        
    }
    
    
    public static void printPuzzle(Integer[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            if (y % 3 == 0)
                System.out.println("|-----------------------|");

            for (int x = 0; x < grid[0].length; x++) {
                if (x % 3 == 0)
                    System.out.print("| ");
                if (grid[y][x] == 0)
                    System.out.print("  ");
                else
                    System.out.print(grid[y][x] + " ");
            }
            System.out.println("|");
        }
        System.out.println("|-----------------------|");
    }
    
    public static boolean isComplete(Integer[][] grid) {
        
        //check for any zeroes
        for (int y = 0; y < grid.length; y++) 
            for (int x = 0; x < grid[0].length; x++)
                if (grid[y][x] == 0)
                    return false;
        
        //check rows
        for (int y = 0; y < 9; y++) {
            GrabBag<Integer> numsNotThere = oneToNine.duplicate();
            for (int x = 0; x < 9; x++) {
                boolean numWasPresent = numsNotThere.remove(grid[y][x]);
                if (!numWasPresent)
                    return false;
            }
            if (numsNotThere.size() > 0)
                return false;
        }
        //check columns
        for (int x = 0; x < 9; x++) {
            GrabBag<Integer> numsNotThere = oneToNine.duplicate();
            for (int y = 0; y < 9; y++) {
                boolean numWasPresent = numsNotThere.remove(grid[y][x]);
                if (!numWasPresent)
                    return false;
            }
            if (numsNotThere.size() > 0)
                return false;
        }
        //check 3x3
        for (int y = 0; y < 9; y += 3) {
            for (int x = 0; x < 9; x += 3) {
                GrabBag<Integer> numsNotThere = oneToNine.duplicate();
                numsNotThere.remove(grid[y][x]);
                numsNotThere.remove(grid[y][x+1]);
                numsNotThere.remove(grid[y][x+2]);
                numsNotThere.remove(grid[y+1][x]);
                numsNotThere.remove(grid[y+1][x+1]);
                numsNotThere.remove(grid[y+1][x+2]);
                numsNotThere.remove(grid[y+2][x]);
                numsNotThere.remove(grid[y+2][x+1]);
                numsNotThere.remove(grid[y+2][x+2]);
                if (numsNotThere.size() > 0)
                    return false;
            }
        }
        return true;
    }
    
    private static class GrabBag<E extends Comparable> {
        
        public List<E> bag = new ArrayList<>();
        
        public GrabBag(E... elements) {
            bag = new ArrayList<>();
            for (int i = 0; i < elements.length; i++)
                bag.add(elements[i]);
            MF.quickSort(bag);
        }
        
        public boolean remove(E element) {
            return bag.remove(element);
        }
        
        public int size() {
            return bag.size();
        }
        
        public GrabBag<E> duplicate() {
            GrabBag<E> copy = new GrabBag<>();
            for (int i = 0; i < this.bag.size(); i++) 
                copy.bag.add(this.bag.get(i));
            return copy;
        }
        
        @Override public String toString() {
            String s = "[";
            for (int i = 0; i < bag.size() - 1; i++)
                s += bag.get(i).toString() + ", ";
            if (bag.size() > 0)
                s += bag.get(bag.size()-1);
            s += "]";
            return s;
        }
    }
    
    
}//answer: 24702
//only implemented some logic solving approaches
//have yet to introdute hidden / naken doubles or triples logic
//so used brute force after logic 