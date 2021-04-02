package problem062;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;

public class Problem062 {

    /*
    The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3). 
    In fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.

    Find the smallest cube for which exactly five permutations of its digits are cube.
    */
    public static void main(String[] args) {
        
        MF.startTimer();
        
        Long testNum = new Long(1);
        List<Long> permsGoneThru = new ArrayList<>();
        permsGoneThru.add(0L);
        //find number of cubes produce permutations of testNum
        //range to test is 1 to cube of testNum with digits in decreasing order
        int maxCubes = 0;
        
        while(true) {
            
            if (testNum % 1000000 == 0) {
                System.out.println(testNum + "\t\t" + MF.getElapsedSeconds() + "\n");
                MF.startTimer();
            }
            
            long lowestPerm = lowestPermutation(testNum);
            if (permsGoneThru.indexOf(lowestPerm) == -1)
                permsGoneThru.add(lowestPerm);
            else {
                testNum++;
                continue;
            }
            long highestPerm = highestPermutation(testNum);
            
            long end = (long) Math.cbrt(highestPerm) + 1;
            int numPermsAreCube = 0;
            List<Long> cubes = new ArrayList<>();
            
            for (long i = 1; i <= end; i++) {
                //test if i^3 is perm of testNum
                if (isPermutationOf(testNum, i*i*i)) {
                    numPermsAreCube++;
                    cubes.add(i);
                }
            }
            
            if (numPermsAreCube > maxCubes) {
                maxCubes = numPermsAreCube;
                
                System.out.println(testNum + " has these perms which are cubes:");
                for (long c : cubes)
                    System.out.println("\t" + (c*c*c) + " (" + c + "^3)");
                System.out.println("num: " + numPermsAreCube + "\n");
            }
            
            
            
            testNum++;
        }
                
    }
    
    public static boolean isPermutationOf(long firstNum, long testNum) {
        char[] firstSorted = (firstNum+"").toCharArray();
        quickSort(firstSorted);
        char[] testSorted = (testNum+"").toCharArray();
        quickSort(testSorted);
        
        if (firstSorted.length != testSorted.length)
            return false;
        for (int i = 0; i < firstSorted.length; i++)
            if (firstSorted[i] != testSorted[i])
                return false;
        return true;
    }
    
    public static long lowestPermutation(long num) {
        char[] ch = (num+"").toCharArray();
        quickSort(ch, 0, ch.length-1);
        return Long.parseLong(new String(ch));
    }
    
    public static long highestPermutation(long num) {
        char[] ch = (num+"").toCharArray();
        quickSort(ch, 0, ch.length-1);
        flipArr(ch);
        return Long.parseLong(new String(ch));
    }
    
    public static void quickSort(char[] arr) {
        quickSort(arr, 0, arr.length-1);
    }
    
    public static void quickSort(char[] arr, int low, int high) {
        if (arr == null || arr.length == 0)
                return;

        if (low >= high)
                return;

        // pick the pivot
        int middle = low + (high - low) / 2;
        char pivot = arr[middle];

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
                        char temp = arr[i];
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
    
    public static void flipArr(char[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            char temp = arr[i];
            arr[i] = arr[arr.length-i-1];
            arr[arr.length-i-1] = temp;
        }
    }

}
