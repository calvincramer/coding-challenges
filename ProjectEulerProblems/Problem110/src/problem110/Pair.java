package problem110;

public class Pair {
    long x;
    long y;

    public Pair(long x, long y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
