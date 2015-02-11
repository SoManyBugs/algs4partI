import java.util.Arrays;

/**
 * This file is created by @Muffin_C on 2/10/15 20:58.
 * This file is part of Project algs4partI.
 */
public class Fast {
    public static void main(String[] args) {
        args = new String[] {"grid4x4.txt"};
        Point[] points;
        Point[] pointsCopy;
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
        pointsCopy = new Point[quantity];
        for (int i = 0; i < quantity; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            pointsCopy[i] = new Point(x, y);
            points[i].draw();
        }

        for (int i = 0; i < quantity; i++) {

            Arrays.sort(pointsCopy, points[i].SLOPE_ORDER);

            System.out.println(Arrays.toString(pointsCopy));

//            Point edge1 = pointsCopy[0];
            Point edge2 = pointsCopy[0];
            double slope = pointsCopy[0].slopeTo(pointsCopy[0]);

            System.out.println(slope);

            int sameQuantity = 1;

            for (int j = 1; j < quantity; j++) {
                if (pointsCopy[j].compareTo(pointsCopy[j - 1]) != 0) {
                    if (pointsCopy[j].slopeTo(pointsCopy[0]) == slope) {
                        System.out.println("Same Slope~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("current Number" + sameQuantity);

//                        if (pointsCopy[j].compareTo(edge1) < 0) {
//                            edge1 = pointsCopy[j];
//                        } else if (pointsCopy[j].compareTo(edge2) > 0) {
//                            edge2 = pointsCopy[j];
//                        }
                        if (pointsCopy[j].compareTo(pointsCopy[0]) > 0 && pointsCopy[j - 1].compareTo(pointsCopy[0]) > 0) {
                            sameQuantity++;
                            if (pointsCopy[j].compareTo(edge2) > 0) {
                                edge2 = pointsCopy[j];
                            }
                        }
                    } else {
                        slope = pointsCopy[j].slopeTo(pointsCopy[0]);
                        System.out.println("new Slope" + slope);

                        if (sameQuantity >= 4) {
//                            edge1.drawTo(edge2);
                            pointsCopy[0].drawTo(edge2);
                            System.out.println("Drew!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                            StdDraw.show(0);
                        }

                        sameQuantity = 1;
                    }
                }
            }
            if (sameQuantity >= 4) {
                pointsCopy[0].drawTo(edge2);
//                edge1.drawTo(edge2);
                System.out.println("Drew!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//                StdDraw.show(0);
            }
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
        System.out.println("end");

    }
}
