package problem086;

import mathtools.MF;

/*
    A spider, S, sits in one corner of a cuboid room, measuring 6 by 5 by 3, and a fly, F, sits in the opposite corner. By travelling on the surfaces of the room the shortest "straight line" distance from S to F is 10 and the path is shown on the diagram.

    However, there are up to three "shortest" path candidates for any given cuboid and the shortest route doesn't always have integer length.

    It can be shown that there are exactly 2060 distinct cuboids, ignoring rotations, with integer dimensions, up to a maximum size of M by M by M, for which the shortest route has integer length when M = 100. This is the least value of M for which the number of solutions first exceeds two thousand; the number of solutions when M = 99 is 1975.

    Find the least value of M such that the number of solutions first exceeds one million.
*/
public class Problem086 {
    
    public static boolean[][][] hasIntegerShortestPathTable;
    public static boolean[][][] filledIn;
    public static final int SIZE = 300;

    public static void main(String[] args) {
        
        hasIntegerShortestPathTable = new boolean[SIZE][SIZE][SIZE];
        filledIn = new boolean[SIZE][SIZE][SIZE];

       for (int m = 1; m < 300; m+=1) 
            System.out.println("m= " + m + "\t" + shortestIntPaths(m));
    }
    
    
    public static long shortestIntPaths(long m) {
        long paths = 0;
        
        for (int a = 1; a <= m; a++) {
            for (int b = 1; b <= a; b++) {
                for (int c = 1; c <= b; c++) {
                    
                    if (hasIntegerShortestPath(a,b,c))
                            paths++;
                    
        }}}
        
        return paths;
    }
    
    public static boolean hasIntegerShortestPath(int a, int b, int c) {
        
        if (a < SIZE && b < SIZE && c < SIZE && filledIn[a][b][c])
            return hasIntegerShortestPathTable[a][b][c];
        
        boolean res = false;
        
        boolean can1 = MF.isPerfectSquare( a*a + (b+c)*(b+c) );
        boolean can2 = MF.isPerfectSquare( b*b + (a+c)*(a+c) );
        boolean can3 = MF.isPerfectSquare( c*c + (a+b)*(a+b) );
        long path1 = (int) Math.sqrt( a*a + (b+c)*(b+c) );
        long path2 = (int) Math.sqrt( b*b + (a+c)*(a+c) );
        long path3 = (int) Math.sqrt( c*c + (a+b)*(a+b) );

        long minPath = MF.min(path1, path2, path3);
        if (path1 == minPath && can1)
            res = true;
        else if (path2 == minPath && can2)
            res = true;
        else if (path3 == minPath && can3)
            res = true;
        
        if (a < SIZE && b < SIZE && c < SIZE) {
            hasIntegerShortestPathTable[a][b][c] = res;
            filledIn[a][b][c] = true;
        }
        
        return res;
    }

    
}
//answer: 1818
/*
m= 1815	998665
m= 1816	999460
m= 1817	999850
m= 1818	1000457
m= 1819	1000457
*/