/**
 * This file is created by @Muffin_C on 2/10/15 20:58.
 * This file is part of Project algs4partI.
 */
public class Brute {
    public static void main(String[] args) {
        args = new String[] {"grid4x4.txt"};
        Point[] points;
        int quantity;

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(-3000, 32768);
        StdDraw.setYscale(-3000, 32768);
        StdDraw.setPenRadius(0.001);  // make the points a bit larger
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

                        if (points[a].compareTo(points[b]) > 0 &&
                                points[b].compareTo(points[c]) > 0 &&
                                points[c].compareTo(points[d]) > 0) {
                            sameDirection = true;
                        } else {
                            sameDirection = false;
                        }

                        if (sameDirection) {
                            if (points[a].slopeTo(points[b]) == points[b].slopeTo(points[c]) &&
                                    points[b].slopeTo(points[c]) == points[c].slopeTo(points[d])) {
                                points[a].drawTo(points[d]);
                                StdOut.println("" + points[a] + " -> " + points[b] + " -> " + points[c] + " -> " + points[d]);
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
