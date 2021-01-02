package problem066;

import java.math.BigInteger;
import mathtools.MF;

public class Try2 {
    
    public static void main(String[] args) {
        
        /*
        int width = 50;
        long[][] grid = new long[width][width];

        for (long y = 1; y < width; y++) {  //avoid trivial case of 1^2-0==1 where y = 0
            for (long x = 0; x < width; x++) {
                long res = x*x - 61*y*y;
                grid[(int)y][(int)x] = res;
            }
        }
        System.out.println("d="+61);
        printGrid(grid);
        System.out.println();
                */
        
        int length = 1000;
        long gD = 0;
        BigInteger gX = BigInteger.ZERO;
        BigInteger gY = BigInteger.ZERO;
        
        for (long d = 0; d <= length; d++) {
            //find minimal solution to x^2 - Dy^2 = 1
            if (MF.isPerfectSquare(d))
                continue;
            
            
            BigInteger x = BigInteger.ONE;
            BigInteger y = BigInteger.ONE;
            BigInteger dee = new BigInteger(""+d);
            BigInteger big = new BigInteger("1000000");
            MF.startTimer();
            boolean found = false;
            while (!found) {
                BigInteger res = x.pow(2).subtract(dee.multiply(y.pow(2)));
                if (res.compareTo(BigInteger.ONE) < 0)
                    x = x.add(BigInteger.ONE);
                else if (res.compareTo(BigInteger.ONE) > 0) {
                    y = y.add(BigInteger.ONE);
                    //System.out.println("\t" + res);
                }
                else if (res.compareTo(BigInteger.ONE) == 0)
                    found = true;
                
            }
            
            System.out.println("d: " + fixedWidthString(d+"", 5) + 
                        "\tx: " + fixedWidthString(x+"", 12) +
                        "\ty: " + fixedWidthString(y+"", 12) + 
                        "\t\ttime: " + MF.getElapsedSeconds(4));
            
            if (x.compareTo(gX) > 0) {
                gD = d;
                gX = x;
                gY = y;
            }
            
        }
        
        System.out.println("\nGreatest:");
        System.out.println("d: " + fixedWidthString(gD+"", 5) + 
                        "\tx: " + fixedWidthString(gX+"", 12) +
                        "\ty: " + fixedWidthString(gY+"", 12));
            
    }
    
    public static void printGrid(long[][] grid) {
        int maxLength = 0;
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[0].length; x++)
                if ((grid[y][x]+"").length() > maxLength)
                    maxLength = (grid[y][x]+"").length();
            
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                System.out.print(fixedWidthString(grid[y][x]+"", maxLength) + " ");
            } 
            System.out.println("");
        }
    }
    
    public static String fixedWidthString(String string, int length) {
        return String.format("%1$-" + length + "s", string);
    }
    
    
}
