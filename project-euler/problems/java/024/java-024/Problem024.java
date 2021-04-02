package problem024;

public class Problem024 {

    /*
    A permutation is an ordered arrangement of objects. 
    For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. 
    If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. 
    The lexicographic permutations of 0, 1 and 2 are:

    012   021   102   120   201   210

    What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
    */
    public static void main(String[] args) {
        int permNum = 0;
        int[] permutation = {0,1,2,3,4,5,6,7,8,9};
        
        //from right to left first first decrease.
        //number on left of decrease get incremented to next avaiable number (lowest number not equal to any number to the left of it)
        //numbers on the right of this new number get filled in in an increasing order
        
        do {
            permNum++;
            for (int n : permutation)
                System.out.print(n);
            System.out.println("\t\t" + permNum);
        } while (permute(permutation));
      
            
    }
    //999999th : 2783915406
    //1000000th: 2783915460
    //1000001th: 2783915460
    
    public static boolean permute (int[] numbers) {
        int fd = numbers.length;
        for (int i = numbers.length-1; i > 0; i--) {
            if (numbers[i] > numbers[i-1]) {
                fd = i-1;
                break;
            }
        }
        if (fd == numbers.length)
            return false;
        
        boolean incUnique = false;
        while(!incUnique) {
            numbers[fd] += 1;
            incUnique = true;   //assume we incremented to a unique number (to the left) first
            for(int i = 0; i < fd; i++)
                if (numbers[i] == numbers[fd])
                    incUnique = false;
        }
        
        //fill in numbers to the right of fd
        for (int i = fd+1; i < numbers.length; i++) {   //for each index from right of decrease to end
            
            for (int numTry = 0; numTry < numbers.length; numTry++) {   //loop over all possible numbers to choose from
                boolean foundGoodNum = true;
                for (int index = 0; index < i; index++) {
                    if (numbers[index] == numTry) {
                        foundGoodNum = false;
                        break;
                    }
                }
                if (foundGoodNum) {
                    numbers[i] = numTry;
                    break;
                }
            }
            
        }
        
        return true;
        
    }
    

}
