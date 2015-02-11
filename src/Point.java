import java.util.Comparator;

/**
 * This file is created by @Muffin_C on 2/10/15 20:53.
 * This file is part of Project algs4partI.
 */
public class Point implements Comparable<Point> {
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();        // compare points by slope to this point

    public static class SlopeOrder implements Comparator<Point> {
        @Override
        public int compare(Point point, Point t1) {
            return 0;
        }
    }

    public Point(int x, int y) {

    }                        // construct the point (x, y)

    public   void draw() {

    }                               // draw this point

    public   void drawTo(Point that) {

    }                  // draw the line segment from this point to that point

    @Override
    public String toString() {
        return super.toString();
    }                          // string representation

    public int compareTo(Point that) {
        return 0;
    }               // is this point lexicographically smaller than that point?

    public double slopeTo(Point that) {
        return 1;
    }                 // the slope between this point and that point
}
