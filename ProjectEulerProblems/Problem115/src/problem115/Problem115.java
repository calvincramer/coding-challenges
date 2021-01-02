package problem115;
public class Problem115 {

    public static final int SIZE = 200;
    public static long[] memo = new long[SIZE];
    public static boolean[] filledIn = new boolean[SIZE];
    
    public static void main(String[] args) {
        //memo[0] = 1;    //for when there is no rightmost blocks
        //memo[1] = 1;
        //memo[2] = 1;
        //memo[3] = 2;
        //memo[4] = 4;
        //memo[5] = 7;
        //memo[6] = 11;
        //memo[7] = 17;   //know for sure
        //memo[8] = 27;   //probably
        
        //for (int i = 0; i <= 3; i++)
        //    filledIn[i] = true;
        
        boolean foundMill = false;
        
        for (int i = 0; i < SIZE; i++) {
            long ans = F(50,i);
            System.out.println("length: " + i + "\t: " + ans);
            
            if (foundMill == false && ans > 1000000) {
                System.out.println("FOUND MILL HERE");
                foundMill = true;
            }
        }
    }
    
    public static long blockComb(int length) {
        if (length < 0 || length >= SIZE)
            return 0;
        if (filledIn[length] == true)
            return memo[length];
        
        //start from red blocks of size 3 to maximum size
        //fill in left to right (xxx000, 0xxx00, 00xxx0, 000xxx)
        //recurse the rightmost part
        
        long numCombs = 1;  //count no red blocks as one combination
        
        for (int rl = 3; rl <= length; rl++) {   //red block length
            for (int offset = 0; offset <= length - rl; offset++) {
                
                if (offset + rl > length)
                    break;
                
                int rightLeftover = length - rl - offset - 1;   //minus one because there will be a gap between the blocks
                if (rightLeftover < 0)
                    numCombs += 1;
                else if (filledIn[rightLeftover] == true)
                    numCombs += memo[rightLeftover];
                else
                    numCombs += blockComb(rightLeftover);
            }
        }
        
        //work
        
        memo[length] = numCombs;
        filledIn[length] = true;
        return numCombs;
    }
    
    //generalized!
    public static long F(int m, int n) {
        int length = n;
        int minLength = m;
        
        if (length < 0 || length >= SIZE)
            return 0;
        if (filledIn[length] == true)
            return memo[length];
        
        //start from red blocks of size 3 to maximum size
        //fill in left to right (xxx000, 0xxx00, 00xxx0, 000xxx)
        //recurse the rightmost part
        
        long numCombs = 1;  //count no red blocks as one combination
        
        for (int rl = minLength; rl <= length; rl++) {   //red block length
            for (int offset = 0; offset <= length - rl; offset++) {
                
                if (offset + rl > length)
                    break;
                
                int rightLeftover = length - rl - offset - 1;   //minus one because there will be a gap between the blocks
                if (rightLeftover < 0)
                    numCombs += 1;
                else if (filledIn[rightLeftover] == true)
                    numCombs += memo[rightLeftover];
                else
                    numCombs += blockComb(rightLeftover);
            }
        }
        
        //work
        
        memo[length] = numCombs;
        filledIn[length] = true;
        return numCombs;
    }
    

    
}