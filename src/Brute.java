/**
 * This file is created by @Muffin_C on 2/10/15 20:58.
 * This file is part of Project algs4partI.
 */
public class Brute {
    public static void main(String[] args) {
        Point[] points;
        int quantity;

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.001);
        StdDraw.setPenColor(StdDraw.BLUE);

        // read in the input
        In in = new In(args[0]);
        quantity = in.readInt();
        points = new Point[quantity];
        for (int i = 0; i < quantity; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }

        for (int a = 0; a < quantity; a++) {
            for (int b = 0; b < quantity; b++) {
                for (int c = 0; c < quantity; c++) {
                    for (int d = 0; d < quantity; d++) {
                        boolean sameDirection;

                        sameDirection = points[a].compareTo(points[b]) > 0
                                && points[b].compareTo(points[c]) > 0
                                && points[c].compareTo(points[d]) > 0;

                        if (sameDirection) {
                            if (points[a].SLOPE_ORDER.compare(points[b], points[c]) == 0
                                    && points[c].SLOPE_ORDER.compare(points[b], points[d]) == 0) {
                                points[a].drawTo(points[d]);
                                StdOut.println("" + points[a] + " -> "
                                        + points[b] + " -> " + points[c]
                                        + " -> " + points[d]);
                            }
                        }
                    }
                }
            }
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
