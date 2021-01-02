package problem088;

import mathtools.MF;

public class Problem088 {

    /*
    A natural number, N, that can be written as the sum and product of a given set of at least two natural numbers, {a1, a2, ... , ak} is called a product-sum number: N = a1 + a2 + ... + ak = a1 × a2 × ... × ak.

    For example, 6 = 1 + 2 + 3 = 1 × 2 × 3.

    For a given set of size, k, we shall call the smallest N with this property a minimal product-sum number. The minimal product-sum numbers for sets of size, k = 2, 3, 4, 5, and 6 are as follows.

    k=2: 4 = 2 × 2 = 2 + 2
    k=3: 6 = 1 × 2 × 3 = 1 + 2 + 3
    k=4: 8 = 1 × 1 × 2 × 4 = 1 + 1 + 2 + 4
    k=5: 8 = 1 × 1 × 2 × 2 × 2 = 1 + 1 + 2 + 2 + 2
    k=6: 12 = 1 × 1 × 1 × 1 × 2 × 6 = 1 + 1 + 1 + 1 + 2 + 6

    Hence for 2≤k≤6, the sum of all the minimal product-sum numbers is 4+6+8+12 = 30; note that 8 is only counted once in the sum.

    In fact, as the complete set of minimal product-sum numbers for 2≤k≤12 is {4, 6, 8, 12, 15, 16}, the sum is 61.

    What is the sum of all the minimal product-sum numbers for 2≤k≤12000?
    */
    
    private static boolean cont = true;
    
    public static void main(String[] args) {

        int[] minProdSum = new int[12001];
        for (int i = 0; i < minProdSum.length; i++)
            minProdSum[i] = Integer.MAX_VALUE;
        
        for (int n = 2; n < 1000; n++) {  //maximum bound?
            //generate list of ways to product-sum this number
        }
        
        for (int k = 2; k <= 12000; k++) {
            System.out.println("k="+k);
            for (int n = k; n <= 200; n++) { //maximum bound?
                //System.out.println(" n="+n);
                expRecurse(n, 0, new int[k], 0, 1);    //just want strickly increasing pattern (or equal to previous)
                if (!cont) {
                    cont = true;
                    break;
                }
            }
        }
        
    }

    public static void experiment(int n) {
        for (int size = 2; size < n; size++) {
            int[] nums = new int[size];
            
        }
    }
    
    public static void expRecurse(int n, int index, int[] nums, int sum, int prod) {
        if (sum > n || prod > n)
            return;
        
        if (index == nums.length-1) {
            int numberToPlace = n - sum;
            if (numberToPlace > 0 && numberToPlace >= nums[index-1]) {
                nums[index] = numberToPlace;
                sum += numberToPlace;
                prod *= numberToPlace;
                
                if (sum == n && prod == n) {
                    //print
                    for (int tn : nums)
                        System.out.print(tn + " ");
                    System.out.println("\t\t: " + MF.sum(nums));
                    //break when found answer
                    cont = false;
                    return;
                }
            }
        }
        else {
            int previous = (index > 0) ? nums[index-1] : 1;
            for (int number = previous; number < n - sum; number++) {
                nums[index] = number;
                sum += number;
                prod *= number;
                
                expRecurse(n, index+1, nums, sum, prod);
                
                prod /= number;
                sum -= number;
            }
        }
        
    }
    

}