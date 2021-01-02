package problem102;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mathtools.MF;

public class Problem102 {

    /*
    Three distinct points are plotted at random on a Cartesian plane, for which -1000 ≤ x, y ≤ 1000, such that a triangle is formed.

    Consider the following two triangles:

    A(-340,495), B(-153,-910), C(835,-947)
    X(-175,41), Y(-421,-714), Z(574,-645)

    It can be verified that triangle ABC contains the origin, whereas triangle XYZ does not.
    Using triangles.txt (right click and 'Save Link/Target As...'), a 27K text file containing the co-ordinates of one thousand "random" triangles, find the number of triangles for which the interior contains the origin.

    NOTE: The first two examples in the file represent the triangles in the example given above.
    */
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
        
        
        List<Triangle> triangles = new ArrayList<>();       //build triangles
        
        for (String line : lines) {
            String[] nums = line.split(",");
            int[] numbers = new int[nums.length];
            for (int i = 0; i < nums.length; i++)
                numbers[i] = Integer.parseInt(nums[i]);
            triangles.add(new Triangle( numbers[0], numbers[1], numbers[2], numbers[3], numbers[4], numbers[5]) );
        }
        
        //do work
        int numOrigins = 0;
        
        for (Triangle tri : triangles) {
            boolean cont = containsOrigin(tri);
            if (cont)
                numOrigins++;
            
            System.out.println(tri.toString() + "\t\t" + cont);
        }
        
        System.out.println("Num triangles: " + numOrigins);
    }
    
    /**
     * Determines if a triangle contains (0,0) in its area
     * @param tri
     * @return 
     */
    public static boolean containsOrigin(Triangle tri) {
        return triangleContainsPoint(tri.p1.x, tri.p1.y, tri.p2.x, tri.p2.y, tri.p3.x, tri.p3.y, 0,0);
    }
    
    public static boolean triangleContainsPoint(int x1, int y1, int x2, int y2, int x3, int y3, int testX, int testY) {
        //test x1, y1
        double theta1, theta2, thetaTest;
        
        theta1 = angleTo(x1, y1, x2, y2);
        theta2 = angleTo(x1, y1, x3, y3);
        thetaTest = angleTo(x1, y1, testX, testY);
        if (!sweepsOut(theta1, theta2, thetaTest))
            return false;
        
        theta1 = angleTo(x2, y2, x1, y1);
        theta2 = angleTo(x2, y2, x3, y3);
        thetaTest = angleTo(x2, y2, testX, testY);
        if (!sweepsOut(theta1, theta2, thetaTest))
            return false;
        
        theta1 = angleTo(x3, y3, x1, y1);
        theta2 = angleTo(x3, y3, x2, y2);
        thetaTest = angleTo(x3, y3, testX, testY);
        if (!sweepsOut(theta1, theta2, thetaTest))
            return false;
        
        return true;
    }
    
    public static double angleTo(int x1, int y1, int x2, int y2) {
        double a = Math.atan2(y2-y1, x2-x1);
        if (a < 0)
            return a + (Math.PI * 2);
        return a;
    }
    
    public static boolean sweepsOut(double theta1, double theta2, double testAngle) {
        double dt = (Math.PI * 2) - MF.max(theta1, theta2) + MF.min(theta1, theta2);
        
        double dt1 = Math.abs(testAngle - theta1);
        double dt2 = Math.abs(testAngle - theta2);
        
        return dt1 <= dt && dt2 <= dt;
    }
    
    public static class Triangle {
        public Point p1;
        public Point p2;
        public Point p3;
        
        public Triangle(Point p1, Point p2, Point p3) {
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;            
        }
        
        public Triangle(int p1x, int p1y, int p2x, int p2y, int p3x, int p3y) {
            this.p1 = new Point(p1x, p1y);
            this.p2 = new Point(p2x, p2y);
            this.p3 = new Point(p3x, p3y);
        }
        
        @Override
        public String toString() {
            return p1.toString() + ", " + p2.toString() + ", " + p3.toString();
        }
    }
    
    public static class Point {
        public int x;
        public int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
