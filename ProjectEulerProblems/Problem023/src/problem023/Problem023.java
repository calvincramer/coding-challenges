package problem023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem023 {

    /*
    A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. 
    For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

    A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

    As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. 
    However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.

    Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
    */
    public static void main(String[] args) {
        int[] naturalNumbers = new int[30000];
        for (int i = 0; i < naturalNumbers.length; i++)
            naturalNumbers[i] = i;
        
        List<Integer> abundantNums = new ArrayList<>();
        for (int i = 1; i <= 28123; i++) {
            if (getNumberType(i) > 0)
                abundantNums.add(i);
        }
        
        System.out.println("Number of abundant nums under 28123: " + abundantNums.size());

        List<Integer> allSums = new ArrayList<>();
        for (int n1 = 0; n1 < abundantNums.size() ; n1++) {
            for (int n2 = 0; n2 < abundantNums.size() ; n2++) {
                
                int toCrossOff = abundantNums.get(n1) + abundantNums.get(n2);
                if (toCrossOff < naturalNumbers.length)
                    naturalNumbers[toCrossOff] = 0;
            }
        }
        
        long sumOfNotFound = 0;
        for (int n : naturalNumbers) {
            if (n != 0) {
                System.out.println(n);
                sumOfNotFound += n;
            }
        }
        
        
        
        
        System.out.println("Total of not founds: " + sumOfNotFound);
    } //answer: 4179871
    
    /**
     * Gets the type of number. Numbers can be deficient, perfect, or abundant
     * Deficient if the sum of proper divisors is less than the number
     * Perfect if the sum of proper divisors is equal to the number
     * Abundant if the sum of proper divisors is greater than the number
     * @param n
     * @return A negative number if deficient.
     * Zero if perfect.
     * Positive number if abundant
     */
    public static int getNumberType(int n) {
        int sumOfDivs = sumOfProperDivisors(n);
        if (sumOfDivs == n) return 0;
        else if (sumOfDivs < n) return -1;
        else return 1;
    }

    public static int sumOfProperDivisors(Integer n) {
        List<Integer> divs = properDivisors(n);
        int sum = 0;
        for (int i = 0; i < divs.size(); i++)
            sum += divs.get(i);
        return sum;
    }
    
    public static List<Integer> properDivisors(Integer n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i < (n/2)+1; i++) {
            if (n % i == 0)
                divisors.add(i);
        }
        return divisors;
    }
    
    //credit: http://www.programcreek.com/2012/11/quicksort-array-in-java/
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
}
