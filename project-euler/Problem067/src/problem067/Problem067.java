package problem067;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mathtools.MF;

public class Problem067 {

    public static ArrayList<ArrayList<Integer>> triangle;
    public static int maxRow = 0;
    public static int greatestPath = 0;
    public static ArrayList<Integer> gPath;
    public static ArrayList<MF.Coordinate> gPathCoords;
    public static int currentPath = 0;
    public static ArrayList<Integer> cPath;
    public static ArrayList<MF.Coordinate> cPathCoords;
    public static int numberOfPaths = 0;
    public static int endX = 0;
    public static int endY = 0;
            
    public static void main(String[] args) {
        Scanner reader = null;
        File curDir = new File("");
        List<String> lines = new ArrayList<>();

        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem067/triangle.txt"));
            while (reader.hasNextLine())
                lines.add(reader.nextLine());
        
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        triangle = new ArrayList<ArrayList<Integer>>();
        
        for (int i = 0; i < lines.size(); i++) {
            triangle.add(new ArrayList<Integer>());
            String[] nums = lines.get(i).split(" ");
            for (String n : nums)
                triangle.get(i).add(Integer.parseInt(n));
        }
        
        
        
        printTriangle(triangle);
        System.out.println("\n\n");
        
        //test how long it takes to get greatest path of different levels
        for (int maxRows = 1; maxRows <= 25; maxRows++) {
 
            MF.startTimer();
            
            maximumPath(0, 0, maxRows);
            
            double time = MF.getElapsedSeconds();
            
            System.out.println("Rows:\t" + maxRow);         //actual
            System.out.println("Paths:\t" + numberOfPaths);
            System.out.print("Greatest path:\t");
            for (int i : gPath)
                System.out.print(i + "  ");
            System.out.println("\nSum gp:\t\t" + greatestPath);
            for (MF.Coordinate cord : gPathCoords) {
                System.out.print(cord.toString() + " ");
            }
            System.out.println();
            
            List<Integer> att1Path = att1(maxRow);          //att1
            int att1Sum = 0;
            for (int i : att1Path)
                att1Sum += i;
            System.out.print("att1:\t\t");
            for (int i : att1Path)
                System.out.print(i + "  ");
            System.out.println("\natt1 sum:\t" + att1Sum);
            
            List<Integer> att2Path = att2(maxRow, 6);       //att2
            int att2Sum = 0;
            for (int i : att2Path)
                att2Sum += i;
            System.out.print("att2:\t\t");
            for (int i : att2Path)
                System.out.print(i + "  ");
            System.out.println("\natt2 sum:\t" + att2Sum);
            
            List<Integer> att3Path = att3(maxRow, 6);       //att3
            int att3Sum = 0;
            for (int i : att3Path)
                att3Sum += i;
            System.out.print("att3:\t\t");
            for (int i : att3Path)
                System.out.print(i + "  ");
            System.out.println("\natt3 sum:\t" + att3Sum);
            
            
            //System.out.println("\nTime:\t" + time);
            //System.out.println("time/row:\t" + (time / maxRows));
            printTriangle(triangle, maxRows);
            System.out.println("\n\n");
        }
        
        
        //trying to get close to answer
        /*
        System.out.println("Whole triangle:\n");
        //
        List<Integer> att1Path = att1(triangle.size()); //att1
        int att1Sum = 0;
        for (int i : att1Path)
            att1Sum += i;
        System.out.print("att1:\t\t");
        for (int i : att1Path)
            System.out.print(i + "  ");
        System.out.println("\natt1 sum:\t" + att1Sum);
        System.out.println("att1 size: " + att1Path.size());
            
        System.out.println("\n\n");     //att2
        List<Integer> att2Path = att2(triangle.size(), 6);
        int att2Sum = 0;
        for (int i : att2Path)
            att2Sum += i;
        System.out.print("att2:\t\t");
        for (int i : att2Path)
            System.out.print(i + "  ");
        System.out.println("\natt2 sum:\t" + att2Sum);
        System.out.println("att2 size: " + att2Path.size());
        */
        
        
        System.out.println("\n\n");
        
        
        for (int step = 2; step <= 25; step++) {
            System.out.println("Step: " + step);
            List<Integer> path = att3(triangle.size(), step);
            int sum = 0;
            for (int i : path)
                sum += i;
            System.out.print("att3:\t\t");
            for (int i : path)
                System.out.print(i + "  ");
            System.out.println("\natt3 sum:\t" + sum);
            System.out.println("att3 size: " + path.size());
            System.out.println();
        }
        
    }
    
    public static void maximumPath(int startY, int startX, int searchDepth) {
        maxRow = startY + searchDepth;
        gPath = new ArrayList<>();
        gPathCoords = new ArrayList<>();
        cPath = new ArrayList<>();
        cPathCoords = new ArrayList<>();
        greatestPath = 0;
        currentPath = 0;
        numberOfPaths = 0;
        
        maximumPath(startY, startX);
    }
    
    public static void maximumPath(int y, int x) {
        if (y >= triangle.size() || y >= maxRow) return;
        if (x >= triangle.get(y).size()) return;
        
        numberOfPaths++;
        int current = triangle.get(y).get(x);   //visit current location
        cPath.add(current);
        cPathCoords.add(new MF.Coordinate(y, x));
        
        currentPath += current;

        //if current path is greater than greatest path
        if (currentPath > greatestPath) {
            greatestPath = currentPath;
            ArrayList<Integer> copiedPath = new ArrayList<>(cPath);     //copy the data of current path
            gPath = copiedPath;
            ArrayList<MF.Coordinate> copiedCoords = new ArrayList<>(cPathCoords);
            gPathCoords = copiedCoords;
            endY = y;
            endX = x;
        }
        
        //if current path is on bottom row, pop this off, return
        if (y == triangle.size() - 1) {
            cPath.remove(cPath.size() - 1);
            cPathCoords.remove(cPathCoords.size()-1);
            currentPath -= current;
            return;
        }
        
        maximumPath(y+1, x);
        maximumPath(y+1, x+1);
        
        //now we've traverse as much as we can on this path, so we're going up one level
        cPath.remove(cPath.size() - 1);
        cPathCoords.remove(cPathCoords.size()-1);
        currentPath -= current;
    }
    
    public static void printTriangle(ArrayList<ArrayList<Integer>> arr) {
        for (int y = 0; y < arr.size(); y++) {
            String initPad = "";
            for (int i = 0; i < arr.size()-1-y; i++)
                initPad += "  ";
            System.out.print(initPad);
            
            for (int x = 0; x < arr.get(y).size(); x++) {
                System.out.printf("%2s", arr.get(y).get(x));
                System.out.print("  ");
            }
            
            System.out.println();
        }
    }
    
    public static void printTriangle(ArrayList<ArrayList<Integer>> arr, int rows) {
        for (int y = 0; y < rows; y++) {
            String initPad = "";
            for (int i = 0; i < rows-1-y; i++)
                initPad += "  ";
            System.out.print(initPad);
            
            for (int x = 0; x < arr.get(y).size(); x++) {
                System.out.printf("%2s", arr.get(y).get(x));
                System.out.print("  ");
            }
            
            System.out.println();
        }
    }
    
    /**
     * Does not always get the right answer, but gets close to it (a bit under)
     * @param mr
     * @return 
     */
    public static List<Integer> att1(int mr) {
        /*
        List greatestPath
        For each node on bottom row
             Select greater node above
                  Repeat until at top
                   If currentPath > greatestPath
                       Store greatestPath
        */
        
        List<Integer> bestPath = new ArrayList<>();
        int sumBestPath = 0;
        
        for (int startX = 0; startX < triangle.get(mr-1).size(); startX++) {
            int y = mr-1;
            int x = startX;
            List<Integer> curPath = new ArrayList<>();
            curPath.add(triangle.get(y).get(x));
            int sumCurPath = triangle.get(y).get(x);
            
            while (y > 0) {
                //three cases:
                //only have right node
                //have both nodes
                //only have left node
                boolean hasLeft = x > 0;
                boolean hasRight = x < triangle.get(y).size()-1;
                
                if (hasLeft && !hasRight) { //go left
                    y--;
                    x--;
                }
                else if (!hasLeft && hasRight)   //go right
                    y--;
                else {
                    y--;
                    //choose which way to go
                    if (triangle.get(y).get(x-1) > triangle.get(y).get(x) )
                        x--;
                    //else x stays
                }
                curPath.add(triangle.get(y).get(x));
                sumCurPath += triangle.get(y).get(x);
                
            }
            
            if (sumCurPath > sumBestPath) {
                sumBestPath = sumCurPath;
                ArrayList<Integer> copiedPath = new ArrayList<>(curPath);     //copy the data of current path
                bestPath = copiedPath;
            }
            
        }
        
        List<Integer> reversedList = new ArrayList<>();
        for (int i = 0; i < bestPath.size(); i++)
            reversedList.add(bestPath.get(bestPath.size()-1-i));
        return reversedList;
    }

    
    public static List<Integer> att2(int mr, int step) {
        if (step < 1) 
            step = 1;
        endY = 0;
        endX = 0;
        List<Integer> bp = new ArrayList<Integer>();
        while (endY < mr - step) { 
            maximumPath(endY, endX, step);
            //add gPath to bp
            for (int n : gPath) {
                bp.add(n);
            }
        }
        int rest = mr - endY;
        maximumPath(endY, endX, rest);
        for (int n : gPath) {
            bp.add(n);
        }
        //remove duplicates
        for (int i = step; i < bp.size(); i += step - 1)
            bp.remove(i);
        return bp;
    }

    
    public static List<Integer> att3(int mr, int step) {
        List<Integer> bp = new ArrayList<Integer>();
        bp.add(triangle.get(0).get(0));
        int y = 0;
        int x = 0;
        
        while (y < mr - step) {
            maximumPath(y, x, step);
            bp.add(gPath.get(1));
            y = gPathCoords.get(1).y;
            x = gPathCoords.get(1).x;
        }
        //add rest
        maximumPath(y, x, mr - y);
        for (int i = 1; i < gPath.size(); i++)
            bp.add(gPath.get(i));
        
        
        return bp;
    }
}
//answer: 7273
