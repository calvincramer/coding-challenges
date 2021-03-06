package problem029;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Problem029 {

    /*
    Consider all integer combinations of ab for 2 ≤ a ≤ 5 and 2 ≤ b ≤ 5:

    2^2=4, 2^3=8, 2^4=16, 2^5=32
    3^2=9, 3^3=27, 3^4=81, 3^5=243
    4^2=16, 4^3=64, 4^4=256, 4^5=1024
    5^2=25, 5^3=125, 5^4=625, 5^5=3125
    If they are then placed in numerical order, with any repeats removed, we get the following sequence of 15 distinct terms:

    4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125

    How many distinct terms are in the sequence generated by ab for 2 ≤ a ≤ 100 and 2 ≤ b ≤ 100?
    */
    public static void main(String[] args) {
        
        List<BigInteger> nums = new ArrayList<>();
        
        for (BigInteger a = new BigInteger("2"); a.intValue() <= 100; a = a.add(BigInteger.ONE)) {
        for (int b = 2; b <= 100; b++) {
            nums.add(a.pow(b));
        }}
        
        BigInteger[] copy = nums.toArray(new BigInteger[nums.size()]);
        quickSort(copy, 0, copy.length-1);
        
        //remove duplicates
        for (int i = 0; i < copy.length; i++) {
            BigInteger test = copy[i];
            while (i < copy.length - 1 && test.compareTo(copy[i+1]) == 0) {
                copy[i+1] = BigInteger.ZERO;
                i++;
            }
        }
        
        nums = new ArrayList<>();
        for (BigInteger n : copy) {
            if (n.compareTo(BigInteger.ZERO) != 0)
                nums.add(n);
        }
        
        for (BigInteger n : nums)
            System.out.println(n);
        
        System.out.println();
        System.out.println("Number of terms: " + nums.size());
    }
    
    public static void quickSort(BigInteger arr[], int low, int high) {
        int i = low, j = high;
        BigInteger temp;
        BigInteger pivot = arr[(low + high) / 2];

        /** partition **/
        while (i <= j) 
        {
            while (arr[i].compareTo(pivot) < 0)
                i++;
            while (arr[j].compareTo(pivot) > 0)
                j--;
            if (i <= j) 
            {
                /** swap **/
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j--;
            }
        }

        /** recursively sort lower half **/
        if (low < j)
            quickSort(arr, low, j);
        /** recursively sort upper half **/
        if (i < high)
            quickSort(arr, i, high);
    }

}
