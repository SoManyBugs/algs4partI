import java.util.TreeSet;

/**
 * This file is created by @Muffin_C on 3/5/15 14:47.
 * This file is part of Project algs4partI.
 */
public class PointSET {
    private TreeSet<Point2D> tree;

    public PointSET() {
        tree = new TreeSet<Point2D>();
    }                              // construct an empty set of points

    public boolean isEmpty() {
        return tree.isEmpty();
    }                     // is the set empty?

    public int size() {
        return tree.size();
    }                        // number of points in the set

    public void insert(Point2D p) {
        tree.add(p);
    }              // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        return tree.contains(p);
    }          // does the set contain point p?

    public void draw() {
        for (Point2D p : tree) {
            p.draw();
        }
    }                       // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        LinkedQueue<Point2D> queue = new LinkedQueue<Point2D>();
        for (Point2D p : tree) {
            if (rect.contains(p)) {
                queue.enqueue(p);
            }
        }
        return queue;
    }            // all points that are inside the rectangle

    public Point2D nearest(Point2D p) {
        Point2D nearestPoint = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Point2D point : tree) {
            if (p.distanceTo(point) < shortestDistance) {
                nearestPoint = point;
                shortestDistance = p.distanceTo(point);
            }
        }

        return nearestPoint;
    }            // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {

        PointSET foo = new PointSET();

        System.out.println(foo.nearest(new Point2D(0,0)));
    }
}
