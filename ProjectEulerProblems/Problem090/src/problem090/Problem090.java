package problem090;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import mathtools.MF;

public class Problem090 {
    
    /*
    Each of the six faces on a cube has a different digit (0 to 9) written on it; the same is done to a second cube. By placing the two cubes side-by-side in different positions we can form a variety of 2-digit numbers.

    In fact, by carefully choosing the digits on both cubes it is possible to display all of the square numbers below one-hundred: 01, 04, 09, 16, 25, 36, 49, 64, and 81.

    For example, one way this can be achieved is by placing {0, 5, 6, 7, 8, 9} on one cube and {1, 2, 3, 4, 8, 9} on the other cube.

    However, for this problem we shall allow the 6 or 9 to be turned upside-down so that an arrangement like {0, 5, 6, 7, 8, 9} and {1, 2, 3, 4, 6, 7} allows for all nine square numbers to be displayed; otherwise it would be impossible to obtain 09.

    In determining a distinct arrangement we are interested in the digits on each cube, not the order.

    {1, 2, 3, 4, 5, 6} is equivalent to {3, 6, 4, 1, 2, 5}
    {1, 2, 3, 4, 5, 6} is distinct from {1, 2, 3, 4, 5, 9}

    But because we are allowing 6 and 9 to be reversed, the two distinct sets in the last example both represent the extended set {1, 2, 3, 4, 5, 6, 9} for the purpose of forming 2-digit numbers.

    How many distinct arrangements of the two cubes allow for all of the square numbers to be displayed?
    */
    
    public static List<int[]> possibleDie = new ArrayList<>();
    
    public static void main(String[] args) {
        
        permuteDigits(new int[]{0,1,2,3,4,5}, 0);
        //for combinations that have one 6 or 9 but not both, add the missing number
        //actually no, they are considered distinct, but add anyways so it can register a 6 as a 9 and vice-versa
        for (int i = 0; i < possibleDie.size(); i++) {
            boolean found6 = false;
            boolean found9 = false;
            for (int r = 0; r < possibleDie.get(i).length; r++) {
                if (possibleDie.get(i)[r] == 6)
                    found6 = true;
                if (possibleDie.get(i)[r] == 9)
                    found9 = true;
            }
            
            if (found6 && !found9)
                possibleDie.set(i, insertInOrder(possibleDie.get(i), 9));
            if (!found6 && found9)
                possibleDie.set(i, insertInOrder(possibleDie.get(i), 6));
        }
        
        
        List<int[]> uniqueDie = new ArrayList<>();
        uniqueDie.addAll(possibleDie);
        //remove any duplicate lists
        //dont remove duplicates, because assing the missing 6 or 9, the sets are still distinct
        /*
        for (int i = 0; i < possibleDie.size(); i++) {
        for (int j = 0; j < possibleDie.size(); j++) {
            if (i == j)
                continue;
            
            if (Arrays.equals(possibleDie.get(i), possibleDie.get(j)))
                possibleDie.set(j, null);
        }
        }
        for (int i = 0; i < possibleDie.size(); i++)
            if (possibleDie.get(i) != null)
                uniqueDie.add(possibleDie.get(i));
        */
        
        
        
        //print
        //for (int i = 0; i < uniqueDie.size(); i++)
        //    MF.printList(uniqueDie.get(i));
        System.out.println(uniqueDie.size() + "\tways to form a cube");
        
        //distinct arrangements of two cubes
        int count = 0;
        for (int i = 0; i < uniqueDie.size(); i++) {
            for (int j = i; j < uniqueDie.size(); j++) {
                if (canMakeSquares(uniqueDie.get(i), uniqueDie.get(j))) {
                    count++;
                    System.out.println( MF.listToString(uniqueDie.get(i)) + "\t" + MF.listToString(uniqueDie.get(j)) );
                }
            }
        }
        System.out.println("total: " + count);

    }
    
    public static int[] insertInOrder(int[] arr, int toInsert) {
        int[] newArr = new int[arr.length+1];
        
        int indexToAdd = 0;
        while (indexToAdd < arr.length && arr[indexToAdd] < toInsert)
            indexToAdd++;
            
        
        for (int i = 0; i < indexToAdd; i++) 
            newArr[i] = arr[i];
        
        newArr[indexToAdd] = toInsert;
        
        for (int i = indexToAdd; i < arr.length; i++)
            newArr[i+1] = arr[i];
        
        return newArr;
    }
    
    
    public static void permuteDigits(int[] digits, int index) {
        if (index >= digits.length)
            return;
        
        int start = (index > 0) ? digits[index-1] + 1 : 0;
        
        for (int n = start; n <= 9; n++) {
            //skip if number already in array
            boolean skip = false;
            digits[index] = -1;
            for (int digit : digits) {
                if (digit == n) {
                    skip = true;
                    break;
                }
            }
            if (skip)
                continue;
            
            digits[index] = n;
            
            if (index == digits.length - 1) {
                //have a permutation there
                possibleDie.add((int[])digits.clone());
            }
            permuteDigits(digits, index+1);
            
        }
        
    }
    
    public static boolean canMakeSquares(int[] arr1, int[] arr2) {
        return     canForm(arr1, arr2, 0, 1) && canForm(arr1, arr2, 0, 4) && canForm(arr1, arr2, 0, 9) 
                && canForm(arr1, arr2, 1, 6) && canForm(arr1, arr2, 2, 5) && canForm(arr1, arr2, 3, 6) 
                && canForm(arr1, arr2, 4, 9) && canForm(arr1, arr2, 6, 4) && canForm(arr1, arr2, 8, 1);
    }
    
    public static boolean canForm(int[] arr1, int[] arr2, int n1, int n2) {
        if (contains(arr1, n1) && contains(arr2, n2))
            return true;
        
        if (contains(arr1, n2) && contains(arr2, n1))
            return true;
        
        return false;
    }
    
    public static boolean contains(int[] arr, int toFind) {
        for (int n : arr)
            if (n == toFind)
                return true;
        return false;
    }
    
}
//answer: 1217
//didnt need to remove duplicates after adding missing 6 or 9 b/c they are considered distinct