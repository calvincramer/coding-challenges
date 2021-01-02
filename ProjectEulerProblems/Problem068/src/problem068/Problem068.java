package problem068;

public class Problem068 {

    static long max = 0;
    
    public static void main(String[] args) {
        int[][] nGon = new int[5][3];
        
        for (int e0 = 1; e0 <= 10; e0++) {
        for (int e1 = 1; e1 <= 10; e1++) {
        for (int e2 = 1; e2 <= 10; e2++) {
            nGon[0][0] = e0;
            nGon[0][1] = e1;
            nGon[0][2] = e2;
            int mustEqual = e0 + e1 + e2;
            evaluateNGon(nGon, mustEqual, 1);
        }}}
        System.out.println("max:\t" + max);
        
    }

    public static void evaluateNGon(int[][] nGon, int rowMustEqual, int curRow) {
        
        if (curRow < nGon.length - 1) {
            nGon[curRow][1] = nGon[curRow - 1][2];
            for (int e0 = 1; e0 <= 10; e0++) {
            for (int e2 = 1; e2 <= 10; e2++) {
                if (e0 + e2 + nGon[curRow][1] != rowMustEqual)
                    continue;
                nGon[curRow][0] = e0;
                nGon[curRow][2] = e2;
                evaluateNGon(nGon, rowMustEqual, curRow+1);
            }}
        }
        else if (curRow == nGon.length - 1) {
            nGon[curRow][1] = nGon[curRow-1][2];
            nGon[curRow][2] = nGon[0][1];
            nGon[curRow][0] = rowMustEqual - nGon[curRow][1] - nGon[curRow][2];
            if (testNGon(nGon))
                printNGon(nGon, rowMustEqual);
        }
        
    }
    
    public static boolean testNGon(int[][] nGon) {
        int[] numbersFound = new int[10+1];
        for (int y = 0; y < nGon.length; y++) {
            for (int x = 0; x < nGon[y].length; x++) {
                if (nGon[y][x] < 1 || nGon[y][x] > 10)
                    return false;
                int n = nGon[y][x];
                if (numbersFound[n] >= 2)
                    return false;
                else
                    numbersFound[n]++;
            }
        }
        
        String digitString = "";
        int minRow = 0;
        for (int i = 0; i < nGon.length; i++)
            if (nGon[i][0] > nGon[minRow][0])
                minRow = i;
        
        
        for (int y = minRow; y < nGon.length; y++)
            for (int x = 0; x < nGon[y].length; x++)
                digitString += nGon[y][x];
        for (int y = 0; y < minRow; y++)
            for (int x = 0; x < nGon[y].length; x++)
                digitString += nGon[y][x];
 
        if (digitString.length() == 16) {
            long n = Long.parseLong(digitString);
            if (n > max)
                max = n;
        } 
                
        return true;
    }
    
    public static void printNGon(int[][] nGon, int total) {
        System.out.print("Total:\t" + total + "\t");
        for (int y = 0; y < nGon.length; y++) {
            
            for (int x = 0; x < nGon[y].length; x++) {
                System.out.print(nGon[y][x]);
                if (x < nGon[y].length - 1)
                    System.out.print(",");
            }
            
            if (y < nGon.length - 1)
                System.out.print("; ");
        }
        System.out.println();
        
    }
}
