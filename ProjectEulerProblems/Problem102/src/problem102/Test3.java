package problem102;

import java.awt.Point;
import java.awt.Polygon;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Test3 {
    
    public static void main(String[] args) {
        Scanner reader = null;              //read file
        File curDir = new File("");
        List<String> lines = new ArrayList<>();
        try {
            reader = new Scanner(new File(curDir.getAbsolutePath() + "/src/problem102/triangles.txt"));
            while (reader.hasNextLine())
                lines.add(reader.nextLine());
        } 
        catch (Exception e) { e.printStackTrace(); }
        
        
        List<Point[]> triangles = new ArrayList<>();      //build triangles
        
        for (String line : lines) {
            String[] nums = line.split(",");
            int[] numbers = new int[nums.length];
            for (int i = 0; i < nums.length; i++)
                numbers[i] = Integer.parseInt(nums[i]);
            
            Point[] pnts = new Point[3];
            pnts[0] = new Point(numbers[0], numbers[1]);
            pnts[1] = new Point(numbers[2], numbers[3]);
            pnts[2] = new Point(numbers[4], numbers[5]);
            
            triangles.add(pnts);
        }
        
        int numContainOrigin = 0;
        Point origin = new Point(0,0);
        double eps = 1E-6;
        
        for (Point[] tri : triangles) {
            double areaT12 = triangleArea(tri[0], tri[1], origin);
            double areaT23 = triangleArea(tri[1], tri[2], origin);
            double areaT31 = triangleArea(tri[2], tri[0], origin);
            double areaTotal = triangleArea(tri[0], tri[1], tri[2]);
            
            if (areaT12 + areaT23 + areaT31 - areaTotal <= eps) {
                numContainOrigin++;
            }
        }
        
        System.out.println("Total: " + numContainOrigin);
        
        
    }
    
    public static double triangleArea(Point p1, Point p2, Point p3) {
        
        double a = distance(p1, p2);
        double b = distance(p2, p3);
        double c = distance(p3, p1);
        
        double s = (a+b+c)/2;
        
        double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
        
        return area;
    }
    
    public static double distance(Point p1, Point p2) {
        return Math.sqrt( (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y) );
    }
}
