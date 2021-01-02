package problem085;

public class Problem085 {

    /*
    By counting carefully it can be seen that a rectangular grid measuring 3 by 2 contains eighteen rectangles:

    Although there exists no rectangular grid that contains exactly two million rectangles, find the area of the grid with the nearest solution.
    */
    public static void main(String[] args) {
        
        int goal = 2000000;
        
        int closestWidth = 0;
        int closestHeight = 0;
        long closestNum = 0;
        
        final int MAX = 200;
        
        for (int width = 1;  width <= MAX; width++) {
            for (int height = 1; height <= MAX; height++) {
                
                long numRects = countRectangles(width, height);
                
                if (Math.abs(numRects - goal) < Math.abs(closestNum - goal)) {
                    closestNum = numRects;
                    closestWidth = width;
                    closestHeight = height;
                    System.out.println(width + "x" + height + ":\t" + numRects);
                }
            }
        }
        System.out.println("done");
        System.out.println(closestWidth + "x" + closestHeight + ":\t" + closestNum);
        System.out.println("area: " + (closestWidth * closestHeight));
    }
    
    public static long countRectangles(int width, int height) {
        
        long numPos = 0;
        //find all types of rectangles
        //one corner top left
        //other corner thru all verticies
        
        for (int y = 1; y <= height; y++) {
            for (int x = 1; x <= width; x++) {
                
                int numW = width - x + 1;
                int numH = height - y + 1;
                numPos += numW * numH;
            }
        }
        
        return numPos;
    }

}
/*
36x77:	1999998
area: 2772
*/