/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problem061;

import java.util.ArrayList;
import java.util.Arrays;
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
public class Try3 {
    
    public static void main(String[] args) {
        
        MF.startTimer();
        
        List<Integer> triNums = new ArrayList<>();
        List<Integer> squareNums = new ArrayList<>();
        List<Integer> pentNums = new ArrayList<>();
        List<Integer> hexNums = new ArrayList<>();
        List<Integer> heptNums = new ArrayList<>();
        List<Integer> octNums = new ArrayList<>();
        
        triNums.add(-1);
        squareNums.add(-1);
        pentNums.add(-1);
        hexNums.add(-1);
        heptNums.add(-1);
        octNums.add(-1);
        
        boolean done = false;
        int index = 1;
        while (!done) {
            int triNum = triangleNumbers(index);
            int squareNum = squareNumbers(index);
            int pentNum = pentagonalNumbers(index);
            int hexNum = hexagonalNumbers(index);
            int heptNum = heptagonalNumbers(index);
            int octNum = octagonalNumbers(index);
            
            if (triNum >= 1000 && triNum < 10000)
                triNums.add(triNum);
            if (squareNum >= 1000 && squareNum < 10000)
                squareNums.add(squareNum);
            if (pentNum >= 1000 && pentNum < 10000)
                pentNums.add(pentNum);
            if (hexNum >= 1000 && hexNum < 10000)
                hexNums.add(hexNum);
            if (heptNum >= 1000 && heptNum < 10000)
                heptNums.add(heptNum);
            if (octNum >= 1000 && octNum < 10000)
                octNums.add(octNum);
            
            if (triNum >= 10000
                    && squareNum >= 10000
                    && pentNum >= 10000
                    && hexNum >= 10000
                    && heptNum >= 10000
                    && octNum >= 10000)
                done = true;
            
            index++;
        }
        triNums.remove(0);
        squareNums.remove(0);
        pentNums.remove(0);
        hexNums.remove(0);
        heptNums.remove(0);
        octNums.remove(0);
        
        List<Integer> allPoly = new ArrayList<>();
        allPoly.addAll(triNums);
        allPoly.addAll(squareNums);
        allPoly.addAll(pentNums);
        allPoly.addAll(hexNums);
        allPoly.addAll(heptNums);
        allPoly.addAll(octNums);
        allPoly = quickSort(allPoly);
        allPoly = removeDuplicates(allPoly);    //may be shown the in range [1000, 10000) there exists no duplicates
        
        
        //for each unique number in all poly lists 
            //do chain length - 2 times:
                //first 2 digits are previous numbers' last 2 digits
                //find every number in poly lists that starts with the first 2 digits
        
        System.out.println("Starting\n");
        
        /*
        String[] chain = new String[3];
        
        end:
        for (int firstNum : allPoly) {
            chain[0] = ""+firstNum;
            
            for (int secondNum : allPoly) {
                chain[1] = ""+secondNum;
                if (!chain[0].substring(2, 4).equals(chain[1].substring(0, 2)))
                    continue;
                
                chain[2]= chain[1].substring(2, 4).concat(chain[0].substring(0, 2));
                
                //check if good
                int[] foundAt = new int[chain.length];
                for (int i = 0 ; i < foundAt.length; i++)
                    foundAt[i] = -1;
                
                for (int i = 0; i < chain.length; i++) {
                    int number = Integer.parseInt(chain[i]);
                    
                    if (triNums.indexOf(number) >= 0) {
                        foundAt[0] = triNums.indexOf(number);
                    }
                    else if (squareNums.indexOf(number) >= 0) {
                        foundAt[1] = squareNums.indexOf(number);
                    }
                    else if (pentNums.indexOf(number) >= 0) {
                        foundAt[2] = pentNums.indexOf(number);
                    }
                }
                boolean quasiGood = true;
                for (int i = 0; i < foundAt.length; i++) {
                    if (foundAt[i] == -1)
                        quasiGood = false;
                }
                
                if (quasiGood) {
                    System.out.println("qg");
                    for(int i = 0; i < chain.length; i++)
                        System.out.println(chain[i] + "\t" + foundAt[i]);
                    System.out.println();
                    break end;
                }
            }
        }
                */
        
        String[] chain = new String[6];
        
        end:
        for (int num0 : allPoly) {
            chain[0] = ""+num0;
            
        for (int num1 : allPoly) {
            chain[1] = ""+num1;
            if (!chain[0].substring(2, 4).equals(chain[1].substring(0, 2)))
                continue;
            
        for (int num2 : allPoly) {
            chain[2] = ""+num2;
            if (!chain[1].substring(2, 4).equals(chain[2].substring(0, 2)))
                continue;
            
        for (int num3 : allPoly) {
            chain[3] = ""+num3;
            if (!chain[2].substring(2, 4).equals(chain[3].substring(0, 2)))
                continue;
        
        for (int num4 : allPoly) {
            chain[4] = ""+num4;
            if (!chain[3].substring(2, 4).equals(chain[4].substring(0, 2)))
                continue;
            
            chain[5]= chain[4].substring(2, 4).concat(chain[0].substring(0, 2));

            //check if good
            boolean[][] found = new boolean[chain.length][chain.length];

            for (int i = 0; i < chain.length; i++) {
                int number = Integer.parseInt(chain[i]);

                if (triNums.indexOf(number) >= 0) {
                    found[i][0] = true;
                }
                else if (squareNums.indexOf(number) >= 0) {
                    found[i][1] = true;
                }
                else if (pentNums.indexOf(number) >= 0) {
                    found[i][2] = true;
                }
                else if (hexNums.indexOf(number) >= 0) {
                    found[i][3] = true;
                }
                else if (heptNums.indexOf(number) >= 0) {
                    found[i][4] = true;
                }
                else if (octNums.indexOf(number) >= 0) {
                    found[i][5] = true;
                }
            }
            
            boolean good = true;
            /*
            for (int y = 0; y < found.length; y++) {
                int numFoundPerRow = 0;
                for (int x = 0; x < found[0].length; x++) {
                    if (found[y][x])
                        numFoundPerRow++;
                }
                if (numFoundPerRow < 1)
                    good = false;
            }
                    */
            for (int x = 0; x < found[0].length; x++) {
                int numFoundPerCol = 0;
                for (int y = 0; y < found.length; y++) {
                    if (found[y][x])
                        numFoundPerCol++;
                }
                if (numFoundPerCol != 1)
                    good = false;
            }

            if (good) {
                for (String s : chain)
                    System.out.println(s);
                printGrid(found);
                System.out.println();
            }
                    
            
        }
        }
        }
        }
        }
        
        
        
        
        System.out.println();
        System.out.println("time: " + MF.getElapsedSeconds());
                
    }
    
    public static void printGrid(boolean[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x])
                    System.out.print("1 ");
                else
                    System.out.print("_ ");
                
            }
            System.out.println();
        }
    }
    
    public static boolean sameFirstTwoDigits(int testNumber, int firstDigit, int secondDigit) {
        String tn = "" + testNumber;
        return tn.substring(0, 1).equals("" + firstDigit)
                && tn.substring(1, 2).equals("" + secondDigit);
    }
    
    public static boolean sameFirstTwoDigits(int num1, int num2) {
        String ns1 = "" + num1;
        String ns2 = "" + num2;
        
        return ns1.substring(0, 2).equals(ns2.substring(0, 2));
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
    
    public static List<Integer> quickSort(List<Integer> list) {
        Integer[] listAsArr = list.toArray(new Integer[list.size()]);
        quickSort(listAsArr, 0, listAsArr.length - 1);
        return new ArrayList<Integer>(Arrays.asList(listAsArr));
    }
    
    public static void quickSort(Integer[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
                return;

        if (low >= high)
                return;

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
                while (arr[i] < pivot) {
                        i++;
                }

                while (arr[j] > pivot) {
                        j--;
                }

                if (i <= j) {
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        i++;
                        j--;
                }
        }

        // recursively sort two sub parts
        if (low < j)
                quickSort(arr, low, j);

        if (high > i)
                quickSort(arr, i, high);
    }
    
    public static List<Integer> removeDuplicates(List<Integer> arr) {
        
        for (int i = 0; i < arr.size() - 1; i++) {
            while (arr.get(i) == arr.get(i+1))
                arr.remove(i+1);
        }
        
        return arr;
    }
}
