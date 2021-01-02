
package problem088;

import java.util.ArrayList;
import java.util.List;
import mathtools.MF;


public class Try2 {
    
    //need to generate all multiplicative partitions
    public static List<Long> primes = MF.getPrimesUnder(1000000);
    public static ProdSum[] minProdSum = new ProdSum[13000];
    
    public static void main(String[] args) {
        /*
        for (int n = 2; n <= 12000; n++) {
            System.out.print(n + ":\t");
            MF.printList(getPrimeFactors(n));
        }
        */
        
        MF.startTimer();
        for (int n = 2; n <= minProdSum.length; n++) {
            allMultiplicativePartitions(n);
            //System.out.println("");
        }
        
        //retain only unique minProdSums
        List<Integer> minPDs = new ArrayList<>();
        for (int i = 2; i <= 12000; i++)
            if (minProdSum[i] != null)
                minPDs.add(minProdSum[i].prodSum);
        List<Integer> unique = new ArrayList<>();
        for (Integer n : minPDs)
            if (!unique.contains(n))
                unique.add(n);
        
        long sum = 0;
        for (int n : unique)
            sum += n;
        
        System.out.println(sum);
        System.out.println(MF.getElapsedSeconds());
        
        
    }
    
    
    public static  List<Integer> getPrimeFactors(int num) {
        List<Integer> pfs = new ArrayList<>();
        if (MF.isPrimeByPrimes(num)) {
            pfs.add(num);
            return pfs;
        }
        
        while (num > 1) {
            for (Long p : primes) {
                if (num % p == 0) {
                    pfs.add(p.intValue());
                    num /= p;
                    break;
                }
            }
        }
        return pfs;
    }
    
    
    public static void allMultiplicativePartitions(int num) {
        int max = getPrimeFactors((int)num).size();
        for (int i = 1; i <= max; i++)
            multiplicativePartitions(num, i);
    }
    
    
    public static void multiplicativePartitions(int num, int length) {
        if (length < 1) {
            System.out.println("[]");
            return;
        }
        
        multiplicativePartitions(num, new int[length], 0);
        
        
    }
    
    public static void multiplicativePartitions(int num, int[] partition, int index) {
        
        if (index == partition.length)
            return;
        if (index == partition.length - 1) { //end
            //make the partition strictly increasing
            if (index > 0 && num < partition[index-1])
                return;
            partition[partition.length-1] = num;
            //System.out.println(MF.listToString(partition));
            //update result array
            int tempProdSum = multiplyAll(partition);
            int lengthOfProdSum = multiplyAll(partition) - addAll(partition) + partition.length;
            int onesToAdd = tempProdSum - addAll(partition);
            
            int[] numbers = new int[lengthOfProdSum];
            
            for (int i = 0; i < onesToAdd; i++)
                numbers[i] = 1;
            for (int i = 0; i < partition.length; i++)
                numbers[i+onesToAdd] = partition[i];
                
            
            if (lengthOfProdSum >= 0 && lengthOfProdSum < minProdSum.length) {
                if (minProdSum[lengthOfProdSum] == null)
                    minProdSum[lengthOfProdSum] = new ProdSum(tempProdSum, numbers);
                else if (tempProdSum < minProdSum[lengthOfProdSum].prodSum)
                    minProdSum[lengthOfProdSum] = new ProdSum(tempProdSum, numbers);
                
            }
            
            return;
        }
        
        List<Integer> primeFactors = MF.getFactorsOfFaster(num, false, false);
        for (int prime : primeFactors) {
            //strictly increasing
            if (index > 0 && prime < partition[index-1])
                continue;   //break?
            partition[index] = prime;
            multiplicativePartitions(num / prime, partition, index+1);
        }
        
        
    }
    
    public static int multiplyAll(int[] arr) {
        int res = 1;
        for (int n : arr)
            res *= n;
        return res;
    }
    
    public static int addAll(int[] arr) {
        int res = 0;
        for (int n : arr)
            res += n;
        return res;
    }
    
    
    public static class ProdSum {
        int prodSum;
        int[] numbers;
        
        public ProdSum(int prodSum, int[] numbers) {
            this.prodSum = prodSum;
            this.numbers = numbers;
        }
        
        @Override
        public String toString() {
            String str = prodSum + " = ";
            for (int i = 0; i < numbers.length - 1; i++)
                str += numbers[i] + " x ";
            str += numbers[numbers.length - 1] + " = ";
            for (int i = 0; i < numbers.length - 1; i++)
                str += numbers[i] + " + ";
            str += numbers[numbers.length - 1];
            return str;
        }
    }
}
//7587457