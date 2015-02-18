import java.util.Arrays;

/**
 * This file is created by @Muffin_C on 2/10/15 20:58.
 * This file is part of Project algs4partI.
 */
public class Fast {
    private static double slope(int i, int j, Point[] points) {
        return points[i].slopeTo(points[j]);
    }

    public static void main(String[] args) {
        Point[] points;
        Point[] pointsCopy;
        int quantity;

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(-1000, 32768);
        StdDraw.setYscale(-1000, 32768);
        StdDraw.setPenRadius(0.001);  // make the points a bit larger
        StdDraw.setPenColor(StdDraw.BLUE);

        // read in the input
        In in = new In(args[0]);
        quantity = in.readInt();
        points = new Point[quantity];
        pointsCopy = new Point[quantity];
        for (int i = 0; i < quantity; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            pointsCopy[i] = new Point(x, y);
            points[i].draw();
        }

        for (int i = 0; i < quantity; i++) {
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, points[i].SLOPE_ORDER);
            double oldSlope = slope(0, 0, pointsCopy);
            int sameLinePoints = 0;

            Point origin = pointsCopy[0];

            for (int j = 1; j < quantity; j++) {
                double newSlope = slope(0, j, pointsCopy);

                if (newSlope == oldSlope) {
                    sameLinePoints++;

                    if (j == quantity - 1 && sameLinePoints >= 3
                            && pointsCopy[j - sameLinePoints + 1].compareTo(origin) > 0) {
                        pointsCopy[0].drawTo(pointsCopy[j]);
                        StdOut.print(pointsCopy[0]);
                        for (int shift = 1; shift <= sameLinePoints; shift++) {
                            StdOut.print(" -> " + pointsCopy[j - sameLinePoints + shift]);
                        }
                        StdOut.println();
                    }

                } else {
                    if (sameLinePoints >= 3
                            && pointsCopy[j - sameLinePoints].compareTo(origin) > 0) {
                        pointsCopy[0].drawTo(pointsCopy[j - 1]);
                        StdOut.print(pointsCopy[0]);
                        for (int shift = 0; shift < sameLinePoints; shift++) {
                            StdOut.print(" -> " + pointsCopy[j - sameLinePoints + shift]);
                        }
                        StdOut.println();
                    }

                    oldSlope = newSlope;
                    sameLinePoints = 1;
                }
            }
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
