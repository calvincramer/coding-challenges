package problem103;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;

public class TestingGettingSubsets {
    
    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1,2,3};
        
        List<Integer[]> returned = getSubsetsOf(arr, false ,false);
        //System.out.println("");
        for (Integer[] subset : returned) {
            Integer[] complement = getComplement(arr, subset);
            
            for (Integer n : subset) 
                System.out.print(n + " ");
            System.out.print("\t | ");
            for (Integer n : complement)
                System.out.print(n + " ");
            System.out.println();
        }
        
        System.out.println("");
        
        List<Pair<Integer[], Integer[]>> res = getNonemptyDisjointSubsets(arr);
        for (Pair<Integer[], Integer[]> pair : res) {
            for (Integer n : pair.getKey()) 
                System.out.print(n + " ");
            System.out.print("\t | ");        
            for (Integer n : pair.getValue()) 
                System.out.print(n + " ");
            System.out.println("");
        }
        
    }
    
    
    
    /**
     * Gets the Nth bit of an integer
     * The 0th bit is the least significant
     * @param number -- number
     * @param bitN   -- the number of the bit you want
     * @return 
     */
    public static boolean getNthBit(Integer number, int bitN) {
        return (number & (1 << bitN)) >> bitN == 1;
    }
    
    public static List<Pair<Integer[], Integer[]>> getNonemptyDisjointSubsets(Integer[] arr) {
        List<Integer[]> subsets = getSubsetsOf(arr, false, false);
        List<Pair<Integer[], Integer[]>> nedisjSubs = new ArrayList<>();
        
        for (Integer[] subset : subsets) {
            Integer[] complement = getComplement(arr, subset);
            List<Integer[]> subsetsOfComp = getSubsetsOf(complement, false, true);
            
            for (Integer[] subset2 : subsetsOfComp) {
                Pair<Integer[], Integer[]> temp = new Pair<>(subset, subset2);
                nedisjSubs.add(temp);
            }
        }
        
        return nedisjSubs;
    }
    
    
    public static Integer[] getComplement(Integer[] universe, Integer[] set) {
        Arrays.sort(universe);
        Arrays.sort(set);
        
        int index = 0;
        Integer[] complement = new Integer[universe.length - set.length];
        for (int n : universe) {
            if (Arrays.binarySearch(set, n) < 0) {  //not found
                complement[index] = n;
                index++;
            }
        }
        return complement;
    }
    
    public static List<Integer[]> getSubsetsOf(Integer[] arr, boolean includeEmptySet, boolean includeWholeSet) {
        if (arr.length >= 30) {
            System.out.println("THATS GOING TO BE A LOT OF SUBSETS PAL");
            System.out.println("IM GOING TO GO AHEAD AND NOT DO THAT");
            return null;
        }
        
        List<Integer[]> listOfSubsets = new ArrayList<>();
        
        int max = 1 << arr.length;      //2 power of arr.length
        for (int mask = includeEmptySet ? 0 : 1; mask < max + (includeWholeSet ? 0 : -1); mask++) {    //start at 0 if allow empty set
            //get subset related to the mask
            
            int numberOfElements = 0;
            for (int n = 0; n < arr.length; n++)
                if (getNthBit(mask,n))
                    numberOfElements++;
            
            Integer[] subset = new Integer[numberOfElements];
            int index = 0;
            for (int n = 0; n < arr.length; n++) {
                if (getNthBit(mask,n)) {
                    subset[index] = arr[n];
                    index++;
                }
            }
            
            listOfSubsets.add(subset);
            //for (int n = 3; n >= 0; n--)
            //    System.out.print(getNthBit(mask, n) ? 1 : 0);
            //System.out.println("");
        }
        
        return listOfSubsets;
    }
}
