package problem106;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;

public class Problem106 {

    public static void main(String[] args) {
        isSpecialSumSet( new Integer[] {3,5,6,7} );
    }
    
    public static void print(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            System.out.print(arr[i] + " ");
        System.out.print(arr[arr.length-1]);
    }
    
    private static boolean isSpecialSumSet(Integer[] set) {
        //go thru all pairs of nonempty disjoint subsets
        //fast fail
        
        if (set == null || set.length == 0)
            return false;
        
        //Arrays.sort(set);   //make sure sorted
        
        //look for duplicates
        for (int i = 1; i < set.length; i++)
            if (set[i] == set[i-1])
                return false;
        
        //obvious check for fail if sum of first two elements is less than greatest element
        if (set.length >= 3 && set[0] + set[1] <= set[set.length-1])
            return false;
                
        //for any two nonempty disjoint subsets...
        List<Pair<Integer[], Integer[]>> res = getNonemptyDisjointSubsets(set);
        for (Pair<Integer[], Integer[]> pair : res) {
            Integer[] B = pair.getKey();
            Integer[] C = pair.getValue();

            print(B);
            System.out.print(" | ");
            print(C);
            System.out.println("");
            
            int sumB = 0;
            int sumC = 0;
            
            for (int n : B) sumB += n;
            for (int n : C) sumC += n;
            
            if (sumB == sumC) 
                return false;
            if (B.length != C.length) {
                if (B.length > C.length && sumB < sumC)
                    return false;
                else if (C.length > B.length && sumC < sumB)
                    return false;
            }
        }
        
        return true;
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
    
    public static List<Pair<Integer[], Integer[]>> 
        getNonemptyDisjointSubsets(Integer[] arr) {
            
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
    
    
    //to do:
    //in getAllSetSums, check the setsum right there, not later
    //  so we don't need to collect all, memory reqs?
    
    //next try
    public static void checkAllSetSums(int numElements, int sum) {
        //List<Integer[]> toReturn = new ArrayList<>();
        helper3(new Integer[numElements], 0, sum, 1);
        //return toReturn;
    }
    
    private static void helper3(Integer[] set, int position, int partialSum, int considerNum) {
        if (position >= set.length) {
            if (partialSum == 0) {
                //collection.add(new Integer[set.length]);    //copy set to collection
                //for (int i = 0; i < set.length; i++)
                //    collection.get(collection.size()-1)[i] = set[i];
                
                //check set
                if (isSpecialSumSet(set)) {
                    print(set);
                    System.out.println("\t SSS");
                }
                
            }
            return;
        }
        for (int add = 0; add < partialSum; add++) {
            int placed = considerNum + add;
            set[position] = placed;
            helper3(set, position + 1, partialSum - placed, placed + 1);
        }
    }
}