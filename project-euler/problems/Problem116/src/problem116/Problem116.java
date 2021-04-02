package problem116;
public class Problem116 {

    public static final int SIZE = 200;
    public static final int MAX_COLOR_LENGTH = 4;
    
    public static long[] memoRed = new long[SIZE];
    public static boolean[] filledInRed = new boolean[SIZE];
    //public static long[] memoGreen = new long[SIZE];
    //public static boolean[] filledInGreen = new boolean[SIZE];
    //public static long[] memoBlue = new long[SIZE];
    //public static boolean[] filledInBlue = new boolean[SIZE];
    
    public static long[][] memo = new long[MAX_COLOR_LENGTH + 1][SIZE];
    public static boolean[][] filledIn = new boolean[MAX_COLOR_LENGTH + 1][SIZE];
    
    
    public static void main(String[] args) {
        long ans = generic(50, 2) + generic(50, 3) + generic(50, 4);
        System.out.println(ans);
        
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x <= MAX_COLOR_LENGTH; x++) {
                System.out.print(memo[x][y] + "\t");
            }
            System.out.println("");
        }
    }
    
    //red blocks of length 2
    public static long red(int length) {
        if (length < 0 || length >= SIZE)
            return 0;
        
        if (filledInRed[length]) return memoRed[length];
        
        long count = 0;
        for (int offset = 0; offset <= length - 2; offset++) {
            count++;    //for current config
            count += red(length - offset - 2);  //rest of configs possible from unassigned right side
        }
        
        memoRed[length] = count;
        filledInRed[length] = true;
        return count;
    }
    
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

}
/*
3	: 2
4	: 4
5	: 7
6	: 12
7	: 20
8	: 33
9	: 54
*/