package problem208;

public class Problem208 {
    
    /*
    A robot moves in a series of one-fifth circular arcs (72°), with a free choice of a clockwise or an anticlockwise arc for each step, but no turning on the spot.

    One of 70932 possible closed paths of 25 arcs starting northward is

    Given that the robot starts facing North, how many journeys of 70 arcs in length can it take that return it, after the final arc, to its starting position?
    (Any arc may be traversed multiple times.)
    */
    public static void main(String[] args) {
        int length = 25;
        long start = 0;
        long stop = (long) Math.pow(2, length);
        
        long numCompleteWalks = 0;
        
        long num = start;
        while (num < stop) {
            
            String turns = leftPad('0', Long.toString(num, 2), length);
            int numZeroes = 0;
            int numOnes = 0;
            for (char c : turns.toCharArray()) {
                if (c == '0')
                    numZeroes++;
                else
                    numOnes++;
            }
            
            if (Math.abs(numZeroes - numOnes) % 5 == 0)
                numCompleteWalks++;
            
            num++;
        }
        
        System.out.println("total: " + numCompleteWalks);
        
    }
    
    public static String leftPad(char padChar, String orig, int length) {
        if (orig.length() >= length)
            return orig;
        
        while (orig.length() < length)
            orig = padChar + orig;
        
        return orig;
        
    }
}