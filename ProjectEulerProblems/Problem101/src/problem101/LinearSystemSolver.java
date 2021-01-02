package problem101;

import mathtools.MF;

public class LinearSystemSolver {
    public static double[] solveSystem(double[][] matrix) {
        
        //ref
        for (int i = 0; i < matrix.length && i < matrix[0].length; i++) {
            
            //make row 1
            multiplyRow(matrix, i, 1.0 / matrix[i][i]);
            
            //subtract down
            for (int y = i + 1; y < matrix.length; y++) 
                addToRow(matrix, i, y, -1.0 * matrix[y][i]);
            
        }
        
        //back up
        int start = MF.min(matrix.length, matrix[0].length) - 1;
        for (int i = start; i > 0; i--) {
            //matrix[i][i] is already 1
            //subtract up
            for (int y = i - 1; y >= 0; y--)
                addToRow(matrix, i, y, matrix[y][i] * -1.0);
        }
        
        
        //rightmost column
        double[] col = new double[matrix.length];
        for(int y = 0; y < matrix.length; y++)
            col[y] = matrix[y][matrix[y].length-1];
        return col;
    }
    
    public static void addToRow(double[][] matrix, int from, int to, double multiplierFrom) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[to][i] += matrix[from][i] * multiplierFrom;
        }
    }
    
    public static void multiplyRow(double[][] matrix, int row, double multiplier) {
        for (int i = 0; i < matrix[row].length; i++)
            matrix[row][i] *= multiplier;
    }
    
    public static void main(String[] args) {
        double[][] test = new double[][] {{1,1,1,1,1},{1,2,4,8,8},{1,3,9,27,27},{1,4,16,64,64}};
        MF.printGrid(test);
        System.out.println();
        double[] ans = LinearSystemSolver.solveSystem(test);
        
        MF.printGrid(test);
        System.out.println("");
        MF.printList(ans);
    }
}
