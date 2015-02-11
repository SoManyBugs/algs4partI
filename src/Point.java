/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();       // YOUR DEFINITION HERE

    private final class SlopeOrder implements Comparator<Point> {
        @Override
        @SuppressWarnings("unchecked")
        public int compare(Point point, Point t1) {
            return ((Comparable) slopeTo(point)).compareTo(slopeTo(t1));
        }
    }

    private int x;                              // x coordinate
    private int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (x == that.x && y == that.y) {
            return Double.NEGATIVE_INFINITY;
        } else if (x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else if (y == that.y) {
            return 0;
        } else {
            return ((double) (that.y - y)) / (that.x - x);
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    @SuppressWarnings("unchecked")
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (y != that.y) {
            return ((Comparable) y).compareTo(that.y);
        } else {
            return ((Comparable) x).compareTo(that.x);
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point a = new Point(0,0);
        Point big = new Point(1,1);
        Point small = new Point(2,2);
        System.out.println(big.compareTo(small));

        int result = a.SLOPE_ORDER.compare(a, a);
        System.out.println(result);

        Point[] p = new Point[20];
        Point[] q = new Point[20];
        for (int i = 0; i < 10; i++) {
            p[i] = new Point(StdRandom.uniform(20), StdRandom.uniform(20));
            q[i] = new Point(p[i].x, p[i].y);
            p[i+10] = p[i];
            q[i+10] = q[i];
        }

        Arrays.sort(q, p[0].SLOPE_ORDER);
        System.out.println(Arrays.toString(q));

        Arrays.sort(q, p[2].SLOPE_ORDER);
        System.out.println(Arrays.toString(q));


        System.out.println(a.slopeTo(big) == big.slopeTo(small));


    }
}
