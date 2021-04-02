package problem114;
public class Problem114 {
    
    public static final int SIZE = 51;
    public static long[] memo = new long[SIZE];
    public static boolean[] filledIn = new boolean[SIZE];
    
    public static void main(String[] args) {
        memo[0] = 1;    //for when there is no rightmost blocks
        memo[1] = 1;
        memo[2] = 1;
        memo[3] = 2;
        //memo[4] = 4;
        //memo[5] = 7;
        //memo[6] = 11;
        //memo[7] = 17;   //know for sure
        //memo[8] = 27;   //probably
        
        for (int i = 0; i <= 3; i++)
            filledIn[i] = true;
        
        
        for (int i = 4; i <= 50; i++) {
            long ans = blockComb(i);
            System.out.println("length: " + i + "\t: " + ans);
        }
        
        System.out.println("");
        System.out.println("length: 50" + "\t: " + blockComb(50));
        
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
    
    

    
}
//1 : 1
//2 : 1
//3 : 2
//4 : 4
//5 : 7
//6 : 11
//7 : 17
//8 : 27
//9 : 
//10: 

//29: 673135
//30: 1089155

//50: 

//almost follows previous + previousprevious - 1
// if start from 7, 11 for 5,6 lengths, will get 15557484099 for 50 length
// if start from 673135, 1089155 for 29,30 lengths, will get 16475631195 for 50 length


//guess1: 15557484099
//guess2: 16475631195
//real:   16475640049

//guess 2 was very close