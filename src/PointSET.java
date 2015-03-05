import java.util.TreeSet;

/**
 * This file is created by @Muffin_C on 3/5/15 14:47.
 * This file is part of Project algs4partI.
 */
public class PointSET {
    private TreeSet<Point2D> tree;

    public PointSET() {
        tree = new TreeSet<Point2D>();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public void insert(Point2D p) {
        tree.add(p);
    }

    public boolean contains(Point2D p) {
        return tree.contains(p);
    }

    public void draw() {
        for (Point2D p : tree) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        LinkedQueue<Point2D> queue = new LinkedQueue<Point2D>();
        for (Point2D p : tree) {
            if (rect.contains(p)) {
                queue.enqueue(p);
            }
        }
        return queue;
    }

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
    }

    public static void main(String[] args) {}
}
