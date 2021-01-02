package problem102;

import mathtools.MF;


public class Testing {
    
    public static void main(String[] args) {
        /*
        //intersect
        System.out.println("1:");
        System.out.println(lineSegmentsIntersect2(0,0, 2,2, 0,2, 2,0));
        //intersect outside segments
        System.out.println("2:");
        System.out.println(lineSegmentsIntersect2(0,0, 1,1, 0,3, 1,2));
        //parallel non intersecting
        System.out.println("3:");
        System.out.println(lineSegmentsIntersect2(0,0, 1,1, 0,1, 1,2));
        //parallel ontop of eachother
        System.out.println("4:");
        System.out.println(lineSegmentsIntersect2(0,0, 1,1, -1,-1, 2,2));
        //intersect at (-0.0833, -8.5833)
        System.out.println("5:");
        System.out.println(lineSegmentsIntersect2(1,-1, 2,6, 1,-14, 2, -19));
        */
        
        System.out.println("(0,2): " + triangleContainsPoint(0,1, 1,-1, -1,-1,  0,2));
        System.out.println("(1,1): " + triangleContainsPoint(0,1, 1,-1, -1,-1,  1,1));
        System.out.println("(2,-2): " + triangleContainsPoint(0,1, 1,-1, -1,-1,  2,-2));
        System.out.println("(0,-2): " + triangleContainsPoint(0,1, 1,-1, -1,-1,  0,-2));
        System.out.println("(-1,1): " + triangleContainsPoint(0,1, 1,-1, -1,-1,  -1,1));
        System.out.println("(0,0): " + triangleContainsPoint(0,1, 1,-1, -1,-1,  0,0));
        System.out.println("(0,-1): " + triangleContainsPoint(0,1, 1,-1, -1,-1,  0,-1));
    }
    
    /*
    public static boolean lineSegmentsIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int lhs = (x2-x1)*(y3-y4);
        lhs -= (x4-x3)*(y1-y2);
        int rhs = (y1 + (x1*y1) - (x1*y2))*(y3-y4);
        rhs -= (y3 + (x3*y3) - (x3*y4))*(y1-y2);
        
        if (lhs == 0) {
            System.out.println("parallel, dont intersect");
            return false;
        }
        
        double y = rhs*1.0/lhs;
        
        double x = (y-y1)*( (x2-x1)*1.0/(y2-y1) ) + x1;
        
        System.out.println("intersect at: (" + x+ ", " + y + ")");
        
        return true;
        
    }
    */
    
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
        return Math.atan2(y2-y1, x2-x1);
    }
    
    public static boolean sweepsOut(double theta1, double theta2, double testAngle) {
        if (testAngle > MF.max(theta1, theta2))
            return false;
        if (testAngle < MF.min(theta1, theta2))
            return false;
        
        return true;
    }
    
    public static boolean lineSegmentsIntersect2(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int denom = (x2-x1)*(y4-y3);
        denom -= (x4-x3)*(y2-y1);

        
        if (denom == 0) {
            System.out.println("parallel, dont intersect");
            return false;
        }
        
        int ytop = ((x2*y1)-(x1*y2))*(y4-y3);
        ytop -= ((x4*y3)-(x3*y4))*(y2-y1);
        
        double y = ytop *1.0 / denom;
        double x = (y-y1)*( (x2-x1)*1.0/(y2-y1) ) + x1;
        
        System.out.println("intersect at: (" + x+ ", " + y + ")");
        
        if (x > MF.max(x1,x2))
            return false;
        if (x < MF.min(x1,x2))
            return false;
        if (x > MF.max(x3,x4))
            return false;
        if (x < MF.min(x3,x4))
            return false;
        
        if (y > MF.max(y1,y2))
            return false;
        if (y < MF.min(y1,y2))
            return false;
        if (y > MF.max(y3,y4))
            return false;
        if (y < MF.min(y3,y4))
            return false;
 
        return true;
        
    }
}

