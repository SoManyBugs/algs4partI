import java.util.Iterator;

/**
 * This file is created by @Muffin_C on 3/5/15 14:54.
 * This file is part of Project algs4partI.
 */
public class KdTree{

    private static class Node{
        private Point2D p;
        private boolean direction;
        private Node leftDown;
        private Node rightUp;
        private Node parent;

        public Node(Point2D p) {
            this.p = p;
        }

        public Node(Point2D p, boolean direction) {
            this.p = p;
            this.direction = direction;
        }
    }

    private Node head;
    private int size;

    public KdTree() {
        size = 0;
        head = null;
    }                              // construct an empty set of points

    public boolean isEmpty() {
        return head == null;
    }                     // is the set empty?

    public int size() {
        return size;
    }                        // number of points in the set

    private void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    public void insert(Point2D p) {
        checkNull(p);

        if (isEmpty()) {
            head = new Node(p);
            head.direction = true;
        }

        if (!contains(p)) {
            insert(p, head, head.direction);
            size++;
        }

    }              // add the point to the set (if it is not already in the set)

    private Node insert(Point2D p, Node root, boolean rootDirection) {

        if (root == null) {
            root = new Node(p, !rootDirection);
        } else {
            if (root.direction) {
                if (p.x() < root.p.x()) {
                    root.leftDown = insert(p, root.leftDown, root.direction);
                } else {
                    root.rightUp = insert(p, root.rightUp, root.direction);
                }
            } else {
                if (p.y() < root.p.y()) {
                    root.leftDown = insert(p, root.leftDown, root.direction);
                } else {
                    root.rightUp = insert(p, root.rightUp, root.direction);
                }
            }
        }

        return root;
    }

    public boolean contains(Point2D p) {
        checkNull(p);

        if (isEmpty()) {
            return false;
        } else {
            return find(p, head) == null;
        }

    }          // does the set contain point p?

    private Node find(Point2D p, Node root) {
        if (root == null) {
            return null;
        }

        if (root.p.compareTo(p) == 0) {
            return root;
        } else {
            if (root.direction) {
                if (p.x() < root.p.x()) {
                    return find(p, root.leftDown);
                } else {
                    return find(p, root.rightUp);
                }
            } else {
                if (p.y() < root.p.y()) {
                    return find(p, root.leftDown);
                } else {
                    return find(p, root.rightUp);
                }
            }
        }
    }

    public void draw() {
        if (!isEmpty()) {
            visit(head);
        }
    }                       // draw all points to standard draw

    private void visit(Node root) {
        if (root == null) {
            return;
        }

        visit(root.leftDown);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        root.p.draw();
        StdDraw.setPenRadius();
        if (root.direction) {
            StdDraw.setPenColor(StdDraw.RED);
            RectHV rect = new RectHV();
        } else {

        }
        visit(root.rightUp);
    }

    public Iterable<Point2D> range(RectHV rect) {
        checkNull(rect);

        LinkedQueue<Point2D> queue = new LinkedQueue<Point2D>();



    }            // all points that are inside the rectangle

    public Point2D nearest(Point2D p) {
        checkNull(p);
        return new Point2D(0,0);
    }            // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {

    }

}
