package problem068;

import java.util.ArrayList;
import java.util.List;


public class Try2 {
    
    public static void main(String[] args) {
        
        int[] permutation = {1,2,3,4,5,6,7,8,9,10};
        int[] pent = new int[15];  
        long numPerms = 0;
        List<Integer[]> possiblePerfs = new ArrayList<>();
        int[] sums = new int[5];
        
        do {
            numPerms++;
            buildPent(pent, permutation);
            for (int sumi = 0; sumi < sums.length; sumi++)
                for (int i = sumi*3; i < sumi*3 + 3; i++)
                    sums[sumi] += pent[i];

            boolean allEqual = true;
            for (int i = 1; i < sums.length; i++)
                if (sums[0] != sums[i])
                    allEqual = false;
            
            if (allEqual) {
                //printPent(pent);
                Integer[] copy = new Integer[pent.length];
                for (int i = 0; i < pent.length; i++)
                    copy[i] = pent[i];
                possiblePerfs.add(copy);
            }
            
                    
            
            //reset sums to 0
            for (int i = 0; i < sums.length; i++)
                sums[i] = 0;
            
        } while (permute(permutation, 1, 10));
        
        
        //rotate possible permutations to lowest external node
        for (int i = 0; i < possiblePerfs.size(); i++) {
            int lowesti = 0;
            for (int r = 0; r < possiblePerfs.get(i).length; r += 3)
                if (possiblePerfs.get(i)[r] < possiblePerfs.get(i)[lowesti])
                    lowesti = r;
            
            Integer[] rotated = new Integer[possiblePerfs.get(i).length];
            int roti = 0;
            for (int h = lowesti; h < rotated.length; h++) {
                rotated[roti] = possiblePerfs.get(i)[h];
                roti++;
            }
            for (int h = 0; h < lowesti; h++) {
                rotated[roti] = possiblePerfs.get(i)[h];
                roti++;
            }
            
            //System.out.println("non rot:\t");
            //printPent(possiblePerfs.get(i));
            
            possiblePerfs.set(i, rotated);
            
            //System.out.println("rot:\t");
            //printPent(possiblePerfs.get(i));
            
            //System.out.println();
        }
        
        List<String> strPerfs = new ArrayList<>();
        for (int i = 0; i < possiblePerfs.size(); i++) {
            String str = "";
            Integer[] p = possiblePerfs.get(i);
            for (int r = 0; r < p.length; r++)
                str += p[r];
            strPerfs.add(str);
            //System.out.println(str);
        }
        
        long greatest = 0;
        for (String s : strPerfs) {
            if (s.length() != 16)
                continue;
            long temp = Long.parseLong(s);
            if (temp > greatest)
                greatest = temp;
        }
        System.out.println(greatest);
        
    }
    
    public static void printPent(int[] pent) {
        for (int i = 0; i < pent.length; i++) {
            if ( i % 3 == 0 && i != 0)
                System.out.print("| ");
            System.out.print(pent[i] + " ");
        }
        System.out.println();
    }
    
    public static void printPent(Integer[] pent) {
        for (int i = 0; i < pent.length; i++) {
            if ( i % 3 == 0 && i != 0)
                System.out.print("| ");
            System.out.print(pent[i] + " ");
        }
        System.out.println();
    }
    
    
    public static void buildPent(int[] pent, int[] nums) {
        pent[0] = nums[0];
        pent[1] = nums[1];
        pent[2] = nums[2];
        pent[3] = nums[3];
        pent[4] = nums[2];
        pent[5] = nums[4];
        pent[6] = nums[5];
        pent[7] = nums[4];
        pent[8] = nums[6];
        pent[9] = nums[7];
        pent[10] = nums[6];
        pent[11] = nums[8];
        pent[12] = nums[9];
        pent[13] = nums[8];
        pent[14] = nums[1];
    }
    
    
    public static boolean permute (int[] numbers, int lowest, int highest) {
        int fd = numbers.length;        //first dip from right
        for (int i = numbers.length-1; i > 0; i--) {
            if (numbers[i] > numbers[i-1]) {
                fd = i-1;
                break;
            }
        }
        if (fd == numbers.length)
            return false;
        
        boolean incUnique = false;  //incremented number unique to the left of fd
        while(!incUnique) {
            numbers[fd] += 1;
            incUnique = true;   //assume we incremented to a unique number (to the left) first
            for(int i = 0; i < fd; i++)
                if (numbers[i] == numbers[fd])
                    incUnique = false;
        }
        
        //fill in numbers to the right of fd
        for (int i = fd+1; i < numbers.length; i++) {   //for each index from right of decrease to end
            
            for (int numTry = lowest; numTry <= highest; numTry++) {   //loop over all possible numbers to choose from
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
//answer: 6531031914842725
