package problem144;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Problem144 {

    /*
    In laser physics, a "white cell" is a mirror system that acts as a delay line for the laser beam. 
    The beam enters the cell, bounces around on the mirrors, and eventually works its way back out.

    The specific white cell we will be considering is an ellipse with the equation 4x2 + y2 = 100

    The section corresponding to −0.01 ≤ x ≤ +0.01 at the top is missing, allowing the light to enter and exit through the hole.

    The light beam in this problem starts at the point (0.0,10.1) just outside the white cell, and the beam first impacts the mirror at (1.4,-9.6).

    Each time the laser beam hits the surface of the ellipse, it follows the usual law of reflection "angle of incidence equals angle of reflection." 
    That is, both the incident and reflected beams make the same angle with the normal line at the point of incidence.

    In the figure on the left, the red line shows the first two points of contact between the laser beam and the wall of the white cell; 
    the blue line shows the line tangent to the ellipse at the point of incidence of the first bounce.

    The slope m of the tangent line at any point (x,y) of the given ellipse is: m = −4x/y

    The normal line is perpendicular to this tangent line at the point of incidence.

    The animation on the right shows the first 10 reflections of the beam.

    How many times does the beam hit the internal surface of the white cell before exiting?
    */
    
    private static final double eps = 0.000001;
    
    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 10.1));
        points.add(new Point(1.4, -9.6));
        
        while (points.get(points.size() -1).x > 0.01 || points.get(points.size() -1).x < -0.01 || points.get(points.size() -1).y < 0) {
        //for (int n = 1; n <= 5; n++) {
            double angleRadNext = getNextAngle(Math.atan( (points.get(points.size()-1).y - points.get(points.size()-2).y) / (points.get(points.size()-1).x - points.get(points.size()-2).x)), points.get(points.size()-1).x, points.get(points.size()-1).y);
            points.add(getNextPoint(Math.tan(angleRadNext), points.get(points.size()-1).x, points.get(points.size()-1).y));
        }
        
        System.out.println("bounces: " + (points.size() - 2));
        
        Grapher graph = new Grapher();
        graph.addEllipse(new Ellipse2D.Double(-5, -10, 10, 20));
        //graph.addLine(new Line2D.Double(0, 10.1, 1.4, -9.6));
        for (int i = 0; i < points.size() - 1; i++) {
            Line2D.Double toAdd = new Line2D.Double(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
            graph.addLine(toAdd);
            graph.repaint();
            
            try {
                Thread.sleep(100);
            } catch (Exception e) {e.printStackTrace();}
        }
        
       
        
    }
    
    
    public static Point getNextPoint(double m, double xstart, double ystart) {
        double a = m;
        double b = ystart - (m*xstart);
        
        double x1 = ((-1.0 * a * b) + (2 * Math.sqrt( 25 * a * a - (b*b) + 100 )) ) / ( 4 + (a*a));
        double x2 = ((-1.0 * a * b) - (2 * Math.sqrt( 25 * a * a - (b*b) + 100 )) ) / ( 4 + (a*a));
        double y1 = (((4*b)/(a*a)) + (2*Math.sqrt(( (100 - b*b)/(a*a) ) + 25 ))) / ( 4/(a*a) + 1);
        double y2 = (((4*b)/(a*a)) - (2*Math.sqrt(( (100 - b*b)/(a*a) ) + 25 ))) / ( 4/(a*a) + 1);
        
        //assuming that one of points found is the start point
        Point nextPoint = new Point();
        if (Math.abs(xstart - x1) < eps)
            nextPoint.x = x2;
        else
            nextPoint.x = x1;
        
        if (Math.abs(ystart - y1) < eps)
            nextPoint.y = y2;
        else
            nextPoint.y = y1;
        
        
        return nextPoint;
    }

    public static double getNextAngle(double incomingAngle, double contactX, double contactY) {
        double m = -4.0 * contactX / contactY;  //slope of ellipse
        m = Math.atan(m);                       //convert slope to angle
        
        incomingAngle -= m;
        double outGoingAngle = Math.PI - incomingAngle;
        return outGoingAngle + m;
    }
    
    
    private static class Point {
        double x;
        double y;

        public Point() {}
        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
//answer: bounces: 354