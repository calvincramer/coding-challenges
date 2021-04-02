package mathtools;

/**
 * Two dimensional point
 * N-dimensional would be good too
 * @author Calvin 
 */
@SuppressWarnings("EqualsAndHashcode")
public class Point {
    double x;
    double y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Point))
            return false;
        Point otherPoint = (Point) other;
        return this.x == otherPoint.x && this.y == otherPoint.y;
    }
    
    @Override 
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
    
    public double distanceTo(Point other) {
        double x1 = this.x;
        double x2 = other.x;
        double y1 = this.y;
        double y2 = other.y;
        double d = Math.sqrt( (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return d;
    }
    
    public double squaredDistanceTo(Point other) {
        double x1 = this.x;
        double x2 = other.x;
        double y1 = this.y;
        double y2 = other.y;
        double d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        return d;
    }
}
