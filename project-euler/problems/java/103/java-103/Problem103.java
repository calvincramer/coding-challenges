package problem103;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;

public class Problem103 {

    public static void main(String[] args) {
        //printAllSetSums(6, 115);
        /*
        int end = 115;
        int total = 0;
        for (int sum = 0; sum <= end; sum++) {
            List<Integer[]> ans = getAllSetSums(6, sum);
            System.out.println("sum = " + sum + "\tnum arrs: " + ans.size());
            total += ans.size();
        }
        System.out.println("\n\ntotal: " + total);
        */
        
        Integer[] osss1 = new Integer[] {1};
        Integer[] osss2 = new Integer[] {1,2};
        Integer[] osss3 = new Integer[] {2,3,4};
        Integer[] osss4 = new Integer[] {3,5,6,7};
        Integer[] osss5 = new Integer[] {6,9,11,12,13};
        Integer[] osss6 = new Integer[] {11,18,19,20,22,25};
        /*
        print(osss1);
        System.out.println("\t is osss? : " + isSpecialSumSet(osss1));
        print(osss2);
        System.out.println("\t is osss? : " + isSpecialSumSet(osss2));
        print(osss3);
        System.out.println("\t is osss? : " + isSpecialSumSet(osss3));
        print(osss4);
        System.out.println("\t is osss? : " + isSpecialSumSet(osss4));
        print(osss5);
        System.out.println("\t is osss? : " + isSpecialSumSet(osss5));
        print(osss6);
        System.out.println("\t is osss? : " + isSpecialSumSet(osss6));
        */
        
        /*
        List<Integer[]> ans = getAllSetSums(6, 115);
        for (Integer[] arr : ans) {
            //print(arr);
            if (isSpecialSumSet(arr)) {
                print(arr);
                System.out.println("\t SSS");
            }
            //System.out.println();
        }
        */
        
        /*
        int sum = 115;  //start at 249
        while (sum <= 150) {
            System.out.println(sum);
            List<Integer[]> poss = getAllSetSums(7,sum);
            System.out.println("second step");
            for (Integer[] arr : poss) {
                if (isSpecialSumSet(arr)) {
                    print(arr);
                    System.out.println("\t SSS");
                }
            }
            sum++;
        }
        */
        
        checkAllSetSums(6,115);
        
        int sum = 249;  //start at 249
        while (true) {
            System.out.println(sum);
            checkAllSetSums(7,sum);
            sum++;
        }
        
    }
    
    public static void print(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            System.out.print(arr[i] + " ");
        System.out.print(arr[arr.length-1]);
    }

    /**
     * Prints all combinations of sets with numEelements with a specific sum
     * @param sum 
     */
    public static void printAllSetSums(int numElements, int sum) {
        helper(new int[numElements], 0, sum, 1);
    }
    
    private static void helper(int[] set, int position, int partialSum, int considerNum) {
        if (position >= set.length) {
            if (partialSum == 0) {
                for (int n : set)
                    System.out.print(n + " ");
                System.out.println();
            }
            return;
        }
        for (int add = 0; add < partialSum; add++) {
            int placed = considerNum + add;
            set[position] = placed;
            helper(set, position + 1, partialSum - placed, placed + 1);
        }
    }
    
    public static List<Integer[]> getAllSetSums(int numElements, int sum) {
        List<Integer[]> toReturn = new ArrayList<>();
        helper2(toReturn, new int[numElements], 0, sum, 1);
        return toReturn;
    }
    
    private static void helper2(List<Integer[]> collection, int[] set, int position, int partialSum, int considerNum) {
        if (position >= set.length) {
            if (partialSum == 0) {
                collection.add(new Integer[set.length]);    //copy set to collection
                for (int i = 0; i < set.length; i++)
                    collection.get(collection.size()-1)[i] = set[i];
            }
            return;
        }
        for (int add = 0; add < partialSum; add++) {
            int placed = considerNum + add;
            set[position] = placed;
            helper2(collection, set, position + 1, partialSum - placed, placed + 1);
        }
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
        //Arrays.sort(universe);
        //Arrays.sort(set);
        
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
//answer at 255
//7: 20 31 38 39 40 42 45	 SSS