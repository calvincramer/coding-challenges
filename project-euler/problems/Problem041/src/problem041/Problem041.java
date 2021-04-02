package problem041;

public class Problem041 {

    /*
    We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. 
    For example, 2143 is a 4-digit pandigital and is also prime.

    What is the largest n-digit pandigital prime that exists?
    */
    public static void main(String[] args) {
        
        for (int n = 1; n < 1000000000; n++) {
            if (isPandigital(n+"")) {
                if (isPrime(n))
                    System.out.println(n);
            }

        }
        System.out.println("done");
    }
    //answer: 7652413

    /*
    Pandigital if the numbers 1,2,3,4...str.length (up to 9) occur exactly once
    */
    public static boolean isPandigital(String str) {
        //if (str.length() != 9) 
        //    return false;
        for (int i = 0; i < str.length(); i++)  //no zeroes allowed
            if (str.charAt(i) == '0')
                return false;
        
        boolean[] numOccur = new boolean[str.length()+1];   //index of 1 = number 1 occurred
        for (int i = 0; i < str.length(); i++) {
            int num = Character.getNumericValue(str.charAt(i));
            if (num >= numOccur.length)
                return false;
            
            if (numOccur[num] == true)  //if already found the number
                return false;
            else
                numOccur[num] = true;
        }
        
        return true;
    }
    
    public static boolean isPrime(int num) {
        if (num < 2) 
            return false;
        for (int n = 2; n < (num / 2) + 1; n++) {
            if (num % n == 0) return false;
        }
        return true;
    }
}
