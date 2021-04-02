package problem038;

public class Problem038 {

    /*
    Take the number 192 and multiply it by each of 1, 2, and 3:

    192 × 1 = 192
    192 × 2 = 384
    192 × 3 = 576
    By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)

    The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, 
    giving the pandigital, 918273645, which is the concatenated product of 9 and (1,2,3,4,5).

    What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
    */
    public static void main(String[] args) {
        
        long largestPan = 123456789;    //smallest pandigital
        int largestPanNumber;
        
        for (int number = 2; number < 10000000; number++) {
            //multiply number by 1,2,3,4,5... until 9 or more digits
            String multStr = "";
            int times = 1;
            while (multStr.length() < 9) {
                multStr = multStr.concat("" + (number * times));
                times++;
            }
            
            //check if pandigital
            if (isPandigital(multStr))
                System.out.println(multStr + "\tPAN" + "\tn=" + number);
        }

    }
    //answer: 932718654	PAN	n=9327
    
    public static boolean isPandigital(String str) {
        if (str.length() != 9) 
            return false;
        for (int i = 0; i < str.length(); i++)  //no zeroes allowed
            if (str.charAt(i) == '0')
                return false;
        
        boolean[] numOccur = new boolean[10];   //index of 1 = number 1 occurred
        for (int i = 0; i < str.length(); i++) {
            int num = Character.getNumericValue(str.charAt(i));
            if (numOccur[num] == true)  //if already found the number
                return false;
            else
                numOccur[num] = true;
        }
        
        return true;
    }

}
