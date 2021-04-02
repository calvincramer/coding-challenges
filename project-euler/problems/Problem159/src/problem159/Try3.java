package problem159;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import mathtools.MF;
import mathtools.PrimeFactorizationPrecomputer;

public class Try3 {

    static final int size = 1000001;
    static final int[] digitalRoots = new int[size];
    
    public static void main(String[] args) {
 
        
        for (int i = 0; i < size; i ++)
            digitalRoots[i] = (int) MF.digitalRoot(i);
        
        
        ////////////////////
        
        Random rng = new Random(System.currentTimeMillis());
        
        for (int min = 3; min < 20; min++) {
            MF.startTimer();
            long ansReduced = getSumMDRSreduced(1000000, min);
            System.out.println("reduced = " + ansReduced + "\t" + MF.getElapsedSeconds() + "\tmin=" + min);
        }
        /*
        long totalMDRS = 0;
        long totalMDRSreduced = 0;
        
        for (int n = 2; n <= 100000; n++) {
            //int n = rng.nextInt(30000) + 211000;
            
            List<Long> factors = PrimeFactorizationPrecomputer.getPrimeFactorization(n);
            int[] factorsArr = new int[factors.size()];
            for (int i = 0; i < factors.size(); i++)
                factorsArr[i] = factors.get(i).intValue();
            
            
            int[] ans = getMDRSans(factorsArr);
            long mdrs = digitalRootSum(ans);
            totalMDRS += mdrs;
            
            int[] reduced = reduceGreedy(factorsArr);
            int[] ansRed = getMDRSans(reduced);
            long mdrsReduced = digitalRootSum(ansRed);
            totalMDRSreduced += mdrsReduced;
            
            //if (mdrs != mdrsReduced)
                System.out.print(n 
                        + MF.fixedWidthStringLeftJustified("  facs = " + factors, 48)
                        + MF.fixedWidthStringLeftJustified("\tmdrs = " + MF.listToString(ans) + " : " + mdrs, 48)
                        + MF.fixedWidthStringLeftJustified("reduced = " + MF.listToString(reduced) + " -> " + MF.listToString(ansRed) + " : " + mdrsReduced, 48));
            
            if (mdrs != mdrsReduced) System.out.println("\tBAD");
            else System.out.println();
        }
        System.out.println("");
        System.out.println("total mdrs = \t\t" + totalMDRS);
        System.out.println("total mdrs reduced = \t" + totalMDRSreduced);
        */
        /////////////////////////////
        
        /*
        for (int max = 16; max <= 10000000; max *= 2) {
            System.out.println("max = " + max);
            
            MF.startTimer();
            long actualAns = getSumMDRS(max);
            System.out.println("actual = " + actualAns + "\t" + MF.getElapsedSeconds());
            
            MF.startTimer();
            long ansReduced = getSumMDRSreduced(max);
            System.out.println("reduced = " + ansReduced + "\t" + MF.getElapsedSeconds());
            
            if (actualAns != ansReduced) {
                System.out.println("BAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD\t");
            }
            else
                System.out.println("");
            System.out.println("");
        }
        */
        
        
        
        
    }

    
    public static long getSumMDRS(int maximum) {
        long total = 0;
        for (int n = 2; n < maximum; n++) {
            List<Long> factors = PrimeFactorizationPrecomputer.getPrimeFactorization(n);
            int[] factorsArr = new int[factors.size()];
            for (int i = 0; i < factors.size(); i++)
                factorsArr[i] = factors.get(i).intValue(); 
            int[] ans = getMDRSans(factorsArr);
            long mdrs = digitalRootSum(ans);
            total += mdrs;
        }
        return total;
    }
    
    
    public static long getSumMDRSreduced(int maximum, int min) {
        long total = 0;
        for (int n = 2; n < maximum; n++) {
            List<Long> factors = PrimeFactorizationPrecomputer.getPrimeFactorization(n);
            int[] factorsArr = new int[factors.size()];
            for (int i = 0; i < factors.size(); i++)
                factorsArr[i] = factors.get(i).intValue(); 
            int[] reduced = reduceGreedy(factorsArr, 4);
            int[] ans = getMDRSans(reduced);
            long mdrs = digitalRootSum(ans);
            total += mdrs;
        }
        return total;
    }


    /**
     * Tries to reduce as far as possible to increase the mdrs as much as possible
     * @param list
     * @return 
     */
    public static int[] reduceGreedyOld(int[] list) {
        //IN STEAD OF DOING FIRST REDUCTION, DO MAXIMUM REDUCITON, look at when n=18
        for (int i = 0; i < list.length; i++) {
        for (int j = i+1; j < list.length; j++) {
            if (i == j) continue;
            
            long origDigitalRoot = digitalRoots[list[i]] + digitalRoots[list[j]];
            int newNumber = list[i] * list[j];
            long newDigitalRoot = digitalRoots[newNumber];
            
            if (newDigitalRoot > origDigitalRoot) {     //greedy, wonder what would happen if also accept equal case
                int[] newList = new int[list.length - 1];
                int index = 0;
                for (int r = 0; r < list.length; r++) {
                    if (r != i && r != j) {
                        newList[index] = list[r];
                        index++;
                    }
                }
                newList[index] = newNumber; //last position
                Arrays.sort(newList);
                return reduceGreedyOld(newList);
            }
        }
        }
        return list;
    }  
    
    /**
     * Tries to reduce as far as possible to increase the mdrs as much as possible
     * @param list
     * @return 
     */
    public static int[] reduceGreedy(int[] list, int min) {
        //IN STEAD OF DOING FIRST REDUCTION, DO MAXIMUM REDUCITON, look at when n=18
        //not enough to just get maximum of 2, need to account for more (how many more?)
            //apparantly considering up to 3 is enough (checked up to 100000
        if (list.length < min)
            return list;
        
        int[] reductionIndexes = new int[3];
        int reductionSize = 0;
        long maxDigitalRootIncrease = 0;    //new digital root minus original digital root
        
        //search for 2
        for (int i = 0; i < list.length; i++) {
        for (int j = i+1; j < list.length; j++) {
            if (i == j) continue;
            
            long origDigitalRoot = digitalRoots[list[i]] + digitalRoots[list[j]];
            int newNumber = list[i] * list[j];
            long newDigitalRoot = digitalRoots[newNumber];
            long change = newDigitalRoot - origDigitalRoot;
          
            if (change > maxDigitalRootIncrease) {
                maxDigitalRootIncrease = change;
                reductionIndexes[0] = i;
                reductionIndexes[1] = j;
                reductionIndexes[2] = -1;
                reductionSize = 2;
            }
        }}
        
        //search for 3
        for (int i = 0  ; i < list.length; i++) {
        for (int j = i+1; j < list.length; j++) {
        for (int k = j+1; k < list.length; k++) {
            if (i == j || j == k) continue;
            
            long origDigitalRoot = digitalRoots[list[i]] + digitalRoots[list[j]] + digitalRoots[list[k]];
            int newNumber = list[i] * list[j] * list[k];
            long newDigitalRoot = digitalRoots[newNumber];
            long change = newDigitalRoot - origDigitalRoot;
          
            if (change > maxDigitalRootIncrease) {
                maxDigitalRootIncrease = change;
                reductionIndexes[0] = i;
                reductionIndexes[1] = j;
                reductionIndexes[2] = k;
                reductionSize = 3;
            }
        }}}
         
        if (maxDigitalRootIncrease == 0)    //found no icnrease
            return list;
        
        if (reductionSize == 2) {
            int[] newList = new int[list.length - 1];
            int index = 0;
            for (int r = 0; r < list.length; r++) {
                if (r != reductionIndexes[0] && r != reductionIndexes[1]) {
                    newList[index] = list[r];
                    index++;
                }
            }
            newList[index] = list[reductionIndexes[0]] * list[reductionIndexes[1]]; //last position
            Arrays.sort(newList);
            return reduceGreedy(newList, min);
        }
        else if (reductionSize == 3) {
            int[] newList = new int[list.length - 2];
            int index = 0;
            for (int r = 0; r < list.length; r++) {
                if (r != reductionIndexes[0] && r != reductionIndexes[1] && r != reductionIndexes[2]) {
                    newList[index] = list[r];
                    index++;
                }
            }
            newList[index] = list[reductionIndexes[0]] * list[reductionIndexes[1]] * list[reductionIndexes[2]]; //last position
            Arrays.sort(newList);
            return reduceGreedy(newList, min);      
        }
        else {
            System.out.println("weird number for reduction size = " + reductionSize);
            System.exit(1);
        }
        return new int[0];
    }  
    
    
    
    public static int[] getMDRSans(int[] factors) {
        
        //System.out.println(factors);    //visit
        if (factors.length <= 1)
            return factors;
        
        int[] max = factors;
        long maxMDRS = digitalRootSum(max);
        
        for (int i = 0; i < factors.length;) {
            for (int j = i + 1; j < factors.length;) {
                if (j == i)
                    continue;

                int prod = factors[i] * factors[j];
                int[] copy = new int[factors.length - 1];
                int index = 0;
                for (int r = 0; r < factors.length; r++) {
                    if (r == i || r == j) continue;
                    copy[index] = factors[r];
                    index++;
                }
                copy[index] = prod;
                //shuffle prod back
                while (index > 0 && copy[index] < copy[index-1]) {
                    int temp = copy[index-1];
                    copy[index-1] = copy[index];
                    copy[index] = temp;
                    index--;
                }
                
                //copy[index] = prod;
                //Arrays.sort(copy);

                int res[] = getMDRSans(copy);
                if (digitalRootSum(res) > maxMDRS) {
                    max = res;
                    maxMDRS = digitalRootSum(max);
                }
                
                int newJ = j+1;
                while (newJ < factors.length && factors[newJ] == factors[j])
                    newJ++;
                j = newJ;
            }
            int newI = i+1;
            while (newI < factors.length && factors[newI] == factors[i])
                newI++;
            i = newI;
        }
        
        return max;
    }
    
    public static long digitalRootSum(int[] arr) {
        long total = 0;
        for (int n : arr)
            total += digitalRoots[n];
        return total;
    }
    
    
    //new approach
    //which numbers have digitalRoot == 9, 8, 7... ? (done)
    //store all digitalRoots in table (done)
    //digital roots just cylce from 1 to 9, repeat
    
    //how to get good reductions in factors, only reduce to 8 factors or so if dont want to reduce to actual answer
    
    //combinations/reductions: (starting from prime factorization
    
        //there exist prime factors whose digital root is 1
        //obviously can't combine 10 factors, since min digital root is 1, and combining 10 would yeild less than 10
        
        //need a way to gaurantee combining two factors will not rule out a better outcome
            //factor1, factor2, other factors
            //factor1 combine with each all other factors
            //factor2 combine with each all other factors
    
    
    

}
