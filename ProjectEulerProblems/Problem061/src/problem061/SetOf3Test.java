/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem061;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;
import static problem061.Problem061.heptagonalNumbers;
import static problem061.Problem061.hexagonalNumbers;
import static problem061.Problem061.octagonalNumbers;
import static problem061.Problem061.pentagonalNumbers;
import static problem061.Problem061.squareNumbers;
import static problem061.Problem061.triangleNumbers;

/**
 *
 * @author Calvin
 */
public class SetOf3Test {
    
    public static void main(String[] args) {
        
        MF.startTimer();
        
        List<Integer> triNums = new ArrayList<>();
        triNums.add(1);
        List<Integer> squareNums = new ArrayList<>();
        squareNums.add(1);
        List<Integer> pentNums = new ArrayList<>();
        pentNums.add(1);
        List<Integer> hexNums = new ArrayList<>();
        hexNums.add(1);
        List<Integer> heptNums = new ArrayList<>();
        heptNums.add(1);
        List<Integer> octNums = new ArrayList<>();
        octNums.add(1);
        
        int ind = 2;
        while (triNums.get(triNums.size()-1) < 10000) {  //fill up arrays with polygonal numbers less than 10000
            triNums.add(triangleNumbers(ind));
            ind++;
        }
        ind = 2;
        while (squareNums.get(squareNums.size()-1) < 10000) {
            squareNums.add(squareNumbers(ind));
            ind++;
        }
        ind = 2;
        while (pentNums.get(pentNums.size()-1) < 10000) {
            pentNums.add(pentagonalNumbers(ind));
            ind++;
        }
        ind = 2;
        while (hexNums.get(hexNums.size()-1) < 10000) {
            hexNums.add(hexagonalNumbers(ind));
            ind++;
        }
        ind = 2;
        while (heptNums.get(heptNums.size()-1) < 10000) {
            heptNums.add(heptagonalNumbers(ind));
            ind++;
        }
        ind = 2;
        while (octNums.get(octNums.size()-1) < 10000) {
            octNums.add(octagonalNumbers(ind));
            ind++;
        }
        
        String[] chain = new String[3];
        int[] intChain = new int[chain.length];
        long numCycles = 0;
        int[][] foundGrid = new int[3][3];  //y is each number, x if found in each poly
        
        for (int i0 = 1000; i0 <= 9999; i0++) { //for all 4 digit numbers
            chain[0] = i0 + "";
            intChain[0] = Integer.parseInt(chain[0]);
            foundGrid[0][0] = triNums.indexOf(intChain[0]);
            foundGrid[0][1] = squareNums.indexOf(intChain[0]);
            foundGrid[0][2] = pentNums.indexOf(intChain[0]);
            //if all are not found, next iteration
            boolean allNotFound = true;
            for (int x = 0; x < foundGrid[0].length; x++)
                if (foundGrid[0][x] != -1)
                    allNotFound = false;
            if (allNotFound)
                continue;
            if (checkForSkip(0, foundGrid))
                continue;
            
        for (int i1 = 00; i1 <= 99; i1++) { //for all 2 digit numbers (we know the first two digits)
            chain[1] = cycle(chain[0], i1);
            intChain[1] = Integer.parseInt(chain[1]);
            foundGrid[1][0] = triNums.indexOf(intChain[1]);
            foundGrid[1][1] = squareNums.indexOf(intChain[1]);
            foundGrid[1][2] = pentNums.indexOf(intChain[1]);
            if (checkForSkip(1, foundGrid))
                continue;

            int firstTwoDigits = Integer.parseInt(chain[0].substring(0, 2));
            chain[2] = cycle(chain[1], firstTwoDigits);
            intChain[2] = Integer.parseInt(chain[2]);
            foundGrid[2][0] = triNums.indexOf(intChain[2]);
            foundGrid[2][1] = squareNums.indexOf(intChain[2]);
            foundGrid[2][2] = pentNums.indexOf(intChain[2]);
            
            boolean skip = false;       //verify each number is 4 digits
            for (int n : intChain) {
                if ( (n+"").length() != 4) {
                    skip = true;
                    break;
                } 
            }
            if (skip)
                continue;
            
            //check to see if grid has unique solution (every row and column has at least one found)
            //numbers (rows)
            boolean allFound = true;
            for (int y = 0; y < foundGrid.length; y++) {
                boolean foundSomething = false;
                for (int x = 0; x < foundGrid[y].length; x++) {
                    if (foundGrid[y][x] != -1) {
                        foundSomething = true;
                        break;
                    }
                }
                if (!foundSomething) {
                    allFound = false;
                    break;
                }
            }
            //polygons (verify at least one number goes into each poly)
            for (int x = 0; x < foundGrid[0].length; x++) {
                boolean foundSomething = false;
                for (int y = 0; y < foundGrid.length; y++) {
                    if (foundGrid[y][x] != -1) {
                        foundSomething = true;
                        break;
                    }
                }
                if (!foundSomething) {
                    allFound = false;
                    break;
                }
            }
            
            if (allFound) {
                
            
                for (String cha : chain)
                    System.out.println(cha);
                //System.out.println();

                for (int y = 0; y < foundGrid.length; y++) {
                    for (int x = 0; x < foundGrid[0].length; x++) {
                        System.out.print(foundGrid[y][x] + " ");
                    }
                    System.out.println();
                }
                int sum = 0;
                for (int ch : intChain)
                    sum += ch;
                System.out.println("sum: " + sum + "\n");
            }
 
            numCycles++;
            
            
        }}
        
        System.out.println();
        System.out.println("time: " + MF.getElapsedSeconds());
    }
    
    public static boolean checkForSkip(int y, int[][] grid) {
        if (y <= 0)
            return false;
        
        //check rows above y for occurence (may be different) in same column
        for (int x = 0; x < grid[0].length; x++) {
            if (grid[y][x] == -1) 
                continue;
            for (int upY = y - 1; upY >= 0; upY--) {
                if (grid[x][upY] != -1) {
                    //if found occurence in upper row, search for any other occurence in that row
                    //if that is the only occurence, return true
                    boolean foundDiffOcc = false;
                    for (int testX = 0; testX < grid[0].length; testX++) {
                        if (testX == x)
                            continue;
                        if (grid[upY][testX] != -1) {
                            foundDiffOcc = true;
                            break;
                        }
                    }
                    if (!foundDiffOcc)
                        return true;    //skip
                }
            }
        }
        //check rows above y for same occurence
        for (int x = 0; x < grid[0].length; x++) {
            if (grid[y][x] == -1)
                continue;
            //search upper rows for a row with only grid[y][x]
            for (int upY = y - 1; upY >= 0; upY--) {
                boolean foundyx = false;
                boolean anyOther = false;
                for (int testX = 0; testX < grid[0].length; testX++) {
                    if (grid[upY][testX] == -1)
                        continue;
                    else if (grid[upY][testX] == grid[y][x])
                        foundyx = true;
                    else
                        anyOther = true;
                }
                if (foundyx && !anyOther)
                    return true;
            }
        }
        
        return false;
    }
    
    //returns index of number in arr, if found. Negative number returned if not found
    public static int findNumIn(List<Integer> arr, int numToFind) {
        return 0;
    }
    
    public static String cycle(String oldNumber, int newNumber) {
        if (newNumber < 10)
            return oldNumber.substring(2) + "0" + newNumber;
        else
            return oldNumber.substring(2) + newNumber;
    }
    
    public static int triangleNumbers(int index) {
        if (index < 1)
            return 0;
        return index * (index + 1) / 2;
    }
    
    public static int squareNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * index;
    }
    
    public static int pentagonalNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * (3 * index - 1) / 2;
    } 
    
    public static int hexagonalNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * (2 * index - 1);
    }
    
    public static int heptagonalNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * (5 * index - 3) / 2;
    }
    
    public static int octagonalNumbers(int index) {
        if (index < 1) 
            return 0;
        return index * (3 * index - 2);
    }
}
