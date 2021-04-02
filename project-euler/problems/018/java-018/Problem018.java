package problem018;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem018 {

    /*
    
    By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.
        3
       7 4
      2 4 6
     8 5 9 3
    That is, 3 + 7 + 4 + 9 = 23.
    Find the maximum total from top to bottom of the triangle below:
                            75
                          95 64
                        17 47 82
                      18 35 87 10
                    20 04 82 47 65
                  19 01 23 75 03 34
                88 02 77 73 07 63 67
              99 65 04 28 06 16 70 92
            41 41 26 56 83 40 80 70 33
          41 48 72 33 47 32 37 16 94 29
        53 71 44 65 25 43 91 52 97 51 14
      70 11 33 28 77 73 17 78 39 68 17 57
    91 71 52 38 17 14 91 43 58 50 27 29 48
  63 66 04 68 89 53 67 30 73 16 69 87 40 31
04 62 98 27 23 09 70 98 73 93 38 53 60 04 23
    */
    
    public static ArrayList<ArrayList<Integer>> triangle;
    public static int greatestPath = 0;
    public static ArrayList<Integer> gPath;
    public static int currentPath = 0;
    public static ArrayList<Integer> cPath;
    public static int numberOfPaths = 0;

    public static void main(String[] args) {
        triangle = new ArrayList<ArrayList<Integer>>();
        triangle.add(new ArrayList<Integer>(Arrays.asList(75)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(95, 64)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(17,47,82)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(18,35,87,10)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(20,04,82,47,65)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(19,01,23,75,03,34)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(88,02,77,73,07,63,67)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(99,65,04,28,06,16,70,92)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(41,41,26,56,83,40,80,70,33)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(41,48,72,33,47,32,37,16,94,29)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(53,71,44,65,25,43,91,52,97,51,14)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(70,11,33,28,77,73,17,78,39,68,17,57)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(91,71,52,38,17,14,91,43,58,50,27,29,48)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(63,66,04,68,89,53,67,30,73,16,69,87,40,31)));
        triangle.add(new ArrayList<Integer>(Arrays.asList(04,62,98,27,23, 9,70,98,73,93,38,53,60,04,23)));
        
        gPath = new ArrayList<>();
        cPath = new ArrayList<>();
        
        for (int row = 0; row < triangle.size(); row++) {
            for (int col = 0; col < triangle.get(row).size(); col++) {
                System.out.printf("%2d", triangle.get(row).get(col));
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        traverseTriangle(0, 0);
        System.out.println("Number of paths: " + numberOfPaths);
        System.out.println("Greatest path: " + greatestPath);
        for (int i = 0; i < gPath.size(); i++)
            System.out.print(gPath.get(i) + "  ");
        
    }
    
    private static void traverseTriangle(int y, int x) {
        
        
        if (y >= triangle.size()) return;
        if (x >= triangle.get(y).size()) return;
        
        
        numberOfPaths++;
        int current = triangle.get(y).get(x);   //visit current location
        cPath.add(current);
        currentPath += current;

        
        if (cPath.size() >= triangle.size() ) {  //if path is maximum length
        for (int i = 0; i < cPath.size(); i++)
            System.out.print(cPath.get(i) + "  ");
        if (y == triangle.size() - 1)
            System.out.println("\tEND OF TRIANGLE");
        else
            System.out.println();
        }

        //if current path is greater than greatest path
        if (currentPath > greatestPath) {
            greatestPath = currentPath;
            ArrayList<Integer> copiedPath = new ArrayList<>(cPath);     //copy the data of current path
            gPath = copiedPath;
        }
        
        //if current path is on bottom row, pop this off, return
        if (y == triangle.size() - 1) {
            cPath.remove(cPath.size() - 1);
            currentPath -= current;
            return;
        }
        
        traverseTriangle(y+1, x);
        traverseTriangle(y+1, x+1);
        
        //now we've traverse as much as we can on this path, so we're going up one level
        cPath.remove(cPath.size() - 1);
        currentPath -= current;

    }

    //answer: 1074
    //path: 75  64  82  87  82  75  73  28  83  32  91  78  58  73  93
    //for some reason the number of paths was calculated to be 32767 (about twich as much as reported on the problem)
}
