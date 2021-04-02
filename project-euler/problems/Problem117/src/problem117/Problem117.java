package problem117;
public class Problem117 {

    public static final int SIZE = 200;

    public static long[] memo = new long[SIZE];
    public static boolean[] filledIn = new boolean[SIZE];
    
    
    public static void main(String[] args) {
        for (int length = 2; length <= 50; length++) {
            System.out.println(length + "\t : " + allColors(length));
        }
        

    }
    
    public static long allColors(int length) {
        return 1 + allColorsHelper(length); //because the actual function does not count the combination of no blocks.
    }
    
    public static long allColorsHelper(int length) {
        if (length < 0 || length >= SIZE)
            return 0;
        
        if (filledIn[length]) return memo[length];
        
        long count = 0;
        
        for (int blockLength = 2; blockLength <= 4; blockLength++) {
            for (int offset = 0; offset <= length - blockLength; offset++) {
                count++;    //for current config
                count += allColorsHelper(length - offset - blockLength);  //rest of configs possible from unassigned right side
            }
        }

        memo[length] = count;
        filledIn[length] = true;
        return count;
    }
    
    /*
    public static long generic(int length, int blockLength) {
        if (length < 0 || length >= SIZE || blockLength < 0 || blockLength > MAX_COLOR_LENGTH)
            return 0;
        
        if (filledIn[blockLength][length]) return memo[blockLength][length];
        
        long count = 0;
        for (int offset = 0; offset <= length - blockLength; offset++) {
            count++;    //for current config
            count += generic(length - offset - blockLength, blockLength);  //rest of configs possible from unassigned right side
        }
        
        memo[blockLength][length] = count;
        filledIn[blockLength][length] = true;
        return count;
    }
    */

}